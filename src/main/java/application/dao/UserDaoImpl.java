package application.dao;

import application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Override
    public void add(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createNativeQuery("INSERT INTO users (name, surname) VALUES(?, ?)")
          .setParameter(1, user.getName())
          .setParameter(2, user.getSurname())
          .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> listUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT e FROM User e");
        return (List<User>) query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT e FROM User e WHERE e.id = :id");
        query.setParameter("id", id);
        return (User)query.getSingleResult();
    }

    @Override
    public void update(long id, User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("UPDATE User e SET e.name = :newName, e.surname = :newSurname WHERE e.id = :id");
        query.setParameter("newName", user.getName());
        query.setParameter("newSurname", user.getSurname());
        query.setParameter("id", user.getId());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void delete(long id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteAll() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM User");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
