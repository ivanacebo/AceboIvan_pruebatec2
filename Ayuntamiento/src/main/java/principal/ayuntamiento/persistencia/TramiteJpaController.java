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
import principal.ayuntamiento.logica.Tramite;
import principal.ayuntamiento.persistencia.exceptions.NonexistentEntityException;

public class TramiteJpaController implements Serializable {

    public TramiteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public TramiteJpaController() {
        emf = Persistence.createEntityManagerFactory("ayuntamientoPU");
    }

    /**
     * Función que me trae todos los trámites de mi base de datos
     * @return lista de trámites
     */
    public List<Tramite> encontrarTramitesNoBorrados() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Tramite> query = em.createQuery(
                    "SELECT t FROM Tramite t WHERE t.borrado = :borrado", Tramite.class);
            query.setParameter("borrado", false);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void create(Tramite tramite) {
        if (tramite.getTurnos() == null) {
            tramite.setTurnos(new ArrayList<Turno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Turno> attachedTurnos = new ArrayList<Turno>();
            for (Turno turnosTurnoToAttach : tramite.getTurnos()) {
                turnosTurnoToAttach = em.getReference(turnosTurnoToAttach.getClass(), turnosTurnoToAttach.getId());
                attachedTurnos.add(turnosTurnoToAttach);
            }
            tramite.setTurnos(attachedTurnos);
            em.persist(tramite);
            for (Turno turnosTurno : tramite.getTurnos()) {
                Tramite oldTramiteOfTurnosTurno = turnosTurno.getTramite();
                turnosTurno.setTramite(tramite);
                turnosTurno = em.merge(turnosTurno);
                if (oldTramiteOfTurnosTurno != null) {
                    oldTramiteOfTurnosTurno.getTurnos().remove(turnosTurno);
                    oldTramiteOfTurnosTurno = em.merge(oldTramiteOfTurnosTurno);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tramite tramite) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tramite persistentTramite = em.find(Tramite.class, tramite.getId());
            List<Turno> turnosOld = persistentTramite.getTurnos();
            List<Turno> turnosNew = tramite.getTurnos();
            List<Turno> attachedTurnosNew = new ArrayList<Turno>();
            for (Turno turnosNewTurnoToAttach : turnosNew) {
                turnosNewTurnoToAttach = em.getReference(turnosNewTurnoToAttach.getClass(), turnosNewTurnoToAttach.getId());
                attachedTurnosNew.add(turnosNewTurnoToAttach);
            }
            turnosNew = attachedTurnosNew;
            tramite.setTurnos(turnosNew);
            tramite = em.merge(tramite);
            for (Turno turnosOldTurno : turnosOld) {
                if (!turnosNew.contains(turnosOldTurno)) {
                    turnosOldTurno.setTramite(null);
                    turnosOldTurno = em.merge(turnosOldTurno);
                }
            }
            for (Turno turnosNewTurno : turnosNew) {
                if (!turnosOld.contains(turnosNewTurno)) {
                    Tramite oldTramiteOfTurnosNewTurno = turnosNewTurno.getTramite();
                    turnosNewTurno.setTramite(tramite);
                    turnosNewTurno = em.merge(turnosNewTurno);
                    if (oldTramiteOfTurnosNewTurno != null && !oldTramiteOfTurnosNewTurno.equals(tramite)) {
                        oldTramiteOfTurnosNewTurno.getTurnos().remove(turnosNewTurno);
                        oldTramiteOfTurnosNewTurno = em.merge(oldTramiteOfTurnosNewTurno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = tramite.getId();
                if (findTramite(id) == null) {
                    throw new NonexistentEntityException("The tramite with id " + id + " no longer exists.");
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
            Tramite tramite;
            try {
                tramite = em.getReference(Tramite.class, id);
                tramite.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tramite with id " + id + " no longer exists.", enfe);
            }
            List<Turno> turnos = tramite.getTurnos();
            for (Turno turnosTurno : turnos) {
                turnosTurno.setTramite(null);
                turnosTurno = em.merge(turnosTurno);
            }
            em.remove(tramite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tramite> findTramiteEntities() {
        return findTramiteEntities(true, -1, -1);
    }

    public List<Tramite> findTramiteEntities(int maxResults, int firstResult) {
        return findTramiteEntities(false, maxResults, firstResult);
    }

    private List<Tramite> findTramiteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tramite.class));
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

    public Tramite findTramite(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tramite.class, id);
        } finally {
            em.close();
        }
    }

    public int getTramiteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tramite> rt = cq.from(Tramite.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
