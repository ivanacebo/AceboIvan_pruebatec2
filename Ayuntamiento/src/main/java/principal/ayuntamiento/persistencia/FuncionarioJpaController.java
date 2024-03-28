package principal.ayuntamiento.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import principal.ayuntamiento.logica.Turno;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import principal.ayuntamiento.logica.Funcionario;
import principal.ayuntamiento.persistencia.exceptions.NonexistentEntityException;

public class FuncionarioJpaController implements Serializable {

    public FuncionarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public FuncionarioJpaController() {
        emf = Persistence.createEntityManagerFactory("ayuntamientoPU");
    }

    /**
     * Función que me trae todos los Funcionarios de mi base de datos
     * @return lista de funcionarios
     */
    public List<Funcionario> encontrarFuncionariosNoBorrados() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Funcionario> query = em.createQuery(
                    "SELECT f FROM Funcionario f WHERE f.borrado = :borrado", Funcionario.class);
            query.setParameter("borrado", false);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Funcionario funcionario) {
        if (funcionario.getTurnos() == null) {
            funcionario.setTurnos(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedTurnos = new ArrayList<Turno>();
            for (Turno turnosTurnoToAttach : funcionario.getTurnos()) {
                turnosTurnoToAttach = em.getReference(turnosTurnoToAttach.getClass(), turnosTurnoToAttach.getId());
                attachedTurnos.add(turnosTurnoToAttach);
            }
            funcionario.setTurnos(attachedTurnos);
            em.persist(funcionario);
            for (Turno turnosTurno : funcionario.getTurnos()) {
                Funcionario oldFuncionarioOfTurnosTurno = turnosTurno.getFuncionario();
                turnosTurno.setFuncionario(funcionario);
                turnosTurno = em.merge(turnosTurno);
                if (oldFuncionarioOfTurnosTurno != null) {
                    oldFuncionarioOfTurnosTurno.getTurnos().remove(turnosTurno);
                    oldFuncionarioOfTurnosTurno = em.merge(oldFuncionarioOfTurnosTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Funcionario funcionario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Funcionario persistentFuncionario = em.find(Funcionario.class, funcionario.getId());
            List<Turno> turnosOld = persistentFuncionario.getTurnos();
            List<Turno> turnosNew = funcionario.getTurnos();
            List<Turno> attachedTurnosNew = new ArrayList<Turno>();
            for (Turno turnosNewTurnoToAttach : turnosNew) {
                turnosNewTurnoToAttach = em.getReference(turnosNewTurnoToAttach.getClass(), turnosNewTurnoToAttach.getId());
                attachedTurnosNew.add(turnosNewTurnoToAttach);
            }
            turnosNew = attachedTurnosNew;
            funcionario.setTurnos(turnosNew);
            funcionario = em.merge(funcionario);
            for (Turno turnosOldTurno : turnosOld) {
                if (!turnosNew.contains(turnosOldTurno)) {
                    turnosOldTurno.setFuncionario(null);
                    turnosOldTurno = em.merge(turnosOldTurno);
                }
            }
            for (Turno turnosNewTurno : turnosNew) {
                if (!turnosOld.contains(turnosNewTurno)) {
                    Funcionario oldFuncionarioOfTurnosNewTurno = turnosNewTurno.getFuncionario();
                    turnosNewTurno.setFuncionario(funcionario);
                    turnosNewTurno = em.merge(turnosNewTurno);
                    if (oldFuncionarioOfTurnosNewTurno != null && !oldFuncionarioOfTurnosNewTurno.equals(funcionario)) {
                        oldFuncionarioOfTurnosNewTurno.getTurnos().remove(turnosNewTurno);
                        oldFuncionarioOfTurnosNewTurno = em.merge(oldFuncionarioOfTurnosNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = funcionario.getId();
                if (findFuncionario(id) == null) {
                    throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.");
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
            Funcionario funcionario;
            try {
                funcionario = em.getReference(Funcionario.class, id);
                funcionario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The funcionario with id " + id + " no longer exists.", enfe);
            }
            List<Turno> turnos = funcionario.getTurnos();
            for (Turno turnosTurno : turnos) {
                turnosTurno.setFuncionario(null);
                turnosTurno = em.merge(turnosTurno);
            }
            em.remove(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> findFuncionarioEntities() {
        return findFuncionarioEntities(true, -1, -1);
    }

    public List<Funcionario> findFuncionarioEntities(int maxResults, int firstResult) {
        return findFuncionarioEntities(false, maxResults, firstResult);
    }

    private List<Funcionario> findFuncionarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario findFuncionario(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Funcionario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
