package principal.ayuntamiento.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.logica.Ciudadano;
import principal.ayuntamiento.logica.Tramite;
import principal.ayuntamiento.logica.Turno;
import principal.ayuntamiento.persistencia.exceptions.NonexistentEntityException;

public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TurnoJpaController() {
        emf = Persistence.createEntityManagerFactory("ayuntamientoPU");
    }

    /**
     * Función para encontrar el turno con el ID más bajo y el estado "En
     * espera" y que no este borrado dicho turno.
     * @return El turno con el ID más bajo y el estado "En espera", o null si no
     * se encuentra.
     */
    public Turno encontrarTurnoConMenorIdYEstadoEnEspera() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery(
                    "SELECT t FROM Turno t WHERE t.estado = :estado AND t.borrado = :borrado ORDER BY t.id ASC", Turno.class);
            query.setParameter("estado", "En espera");
            query.setParameter("borrado", false);
            query.setMaxResults(1); // Limitamos los resultados a uno

            List<Turno> resultados = query.getResultList();
            return resultados.isEmpty() ? null : resultados.get(0);
        } finally {
            em.close();
        }
    }

    /**
     * Función para traer todos los turnos que no esten borrados de la base de
     * datos
     * @return lista de todos los turnos
     */
    public List<Turno> encontrarTurnosNoBorrados() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Turno> query = em.createQuery(
                    "SELECT t FROM Turno t WHERE t.borrado = :borrado", Turno.class);
            query.setParameter("borrado", false);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Turno turno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario funcionario = turno.getFuncionario();
            if (funcionario != null) {
                funcionario = em.getReference(funcionario.getClass(), funcionario.getId());
                turno.setFuncionario(funcionario);
            }
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano = em.getReference(ciudadano.getClass(), ciudadano.getId());
                turno.setCiudadano(ciudadano);
            }
            Tramite tramite = turno.getTramite();
            if (tramite != null) {
                tramite = em.getReference(tramite.getClass(), tramite.getId());
                turno.setTramite(tramite);
            }
            em.persist(turno);
            if (funcionario != null) {
                funcionario.getTurnos().add(turno);
                funcionario = em.merge(funcionario);
            }
            if (ciudadano != null) {
                ciudadano.getTurnos().add(turno);
                ciudadano = em.merge(ciudadano);
            }
            if (tramite != null) {
                tramite.getTurnos().add(turno);
                tramite = em.merge(tramite);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getId());
            Funcionario funcionarioOld = persistentTurno.getFuncionario();
            Funcionario funcionarioNew = turno.getFuncionario();
            Ciudadano ciudadanoOld = persistentTurno.getCiudadano();
            Ciudadano ciudadanoNew = turno.getCiudadano();
            Tramite tramiteOld = persistentTurno.getTramite();
            Tramite tramiteNew = turno.getTramite();
            if (funcionarioNew != null) {
                funcionarioNew = em.getReference(funcionarioNew.getClass(), funcionarioNew.getId());
                turno.setFuncionario(funcionarioNew);
            }
            if (ciudadanoNew != null) {
                ciudadanoNew = em.getReference(ciudadanoNew.getClass(), ciudadanoNew.getId());
                turno.setCiudadano(ciudadanoNew);
            }
            if (tramiteNew != null) {
                tramiteNew = em.getReference(tramiteNew.getClass(), tramiteNew.getId());
                turno.setTramite(tramiteNew);
            }
            turno = em.merge(turno);
            if (funcionarioOld != null && !funcionarioOld.equals(funcionarioNew)) {
                funcionarioOld.getTurnos().remove(turno);
                funcionarioOld = em.merge(funcionarioOld);
            }
            if (funcionarioNew != null && !funcionarioNew.equals(funcionarioOld)) {
                funcionarioNew.getTurnos().add(turno);
                funcionarioNew = em.merge(funcionarioNew);
            }
            if (ciudadanoOld != null && !ciudadanoOld.equals(ciudadanoNew)) {
                ciudadanoOld.getTurnos().remove(turno);
                ciudadanoOld = em.merge(ciudadanoOld);
            }
            if (ciudadanoNew != null && !ciudadanoNew.equals(ciudadanoOld)) {
                ciudadanoNew.getTurnos().add(turno);
                ciudadanoNew = em.merge(ciudadanoNew);
            }
            if (tramiteOld != null && !tramiteOld.equals(tramiteNew)) {
                tramiteOld.getTurnos().remove(turno);
                tramiteOld = em.merge(tramiteOld);
            }
            if (tramiteNew != null && !tramiteNew.equals(tramiteOld)) {
                tramiteNew.getTurnos().add(turno);
                tramiteNew = em.merge(tramiteNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = turno.getId();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            Funcionario funcionario = turno.getFuncionario();
            if (funcionario != null) {
                funcionario.getTurnos().remove(turno);
                funcionario = em.merge(funcionario);
            }
            Ciudadano ciudadano = turno.getCiudadano();
            if (ciudadano != null) {
                ciudadano.getTurnos().remove(turno);
                ciudadano = em.merge(ciudadano);
            }
            Tramite tramite = turno.getTramite();
            if (tramite != null) {
                tramite.getTurnos().remove(turno);
                tramite = em.merge(tramite);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Turno findTurno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
