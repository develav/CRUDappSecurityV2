package application.dao;

import application.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User");
        return query.getResultList();
    }

    @Override
    public User getUserById(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("FROM User u WHERE u.id = :id");
        return (User) query.uniqueResult();
    }

    @Override
    public void update(int id) {

    }

    @Override
    public void delete(int id) {

    }
}
