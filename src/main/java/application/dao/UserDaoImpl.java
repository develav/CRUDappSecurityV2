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
        EntityManager em = null;
        try{
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            em.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> listUsers() {
        EntityManager em = null;
        List<User> userList = null;
        try{
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("SELECT e FROM User e");
            userList = (List<User>) query.getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserById(long id) {
        EntityManager em = null;
        User user = null;
        try{
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("SELECT e FROM User e WHERE e.id = :id");
            query.setParameter("id", id);
            user = (User)query.getSingleResult();
        } catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByName(String name) {
        EntityManager em = null;
        User user = null;
        try{
            em = entityManagerFactory.createEntityManager();
            Query query = em.createQuery("SELECT e FROM User e WHERE e.name ='" + name + "'");
            user = (User)query.getSingleResult();
        } catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void update(long id, User user) {
        EntityManager em = null;
        try{
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("UPDATE User e SET e.name = :newName, e.surname = :newSurname WHERE e.id = :id");
            query.setParameter("newName", user.getUsername());
            query.setParameter("newSurname", user.getSurname());
            query.setParameter("id", user.getId());
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager em = null;
        try{
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM User WHERE id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        EntityManager em = null;
        try{
            em = entityManagerFactory.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM User");
            query.executeUpdate();
            em.getTransaction().commit();
            em.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
