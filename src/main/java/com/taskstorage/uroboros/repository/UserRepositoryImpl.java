package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    public List<User> selectAll() {
        List<User> users = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("from User").list();
        session.close();
        return users;
    }

    @Override
    public User selectById(Long id) {
        User user = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        // Select with query
        String queryString = "from User where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", id);

        // Select with built-in method
        // user = session.get(User.class, id);

        user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User selectByUsername(String username) {
        User user = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        String queryString = "from User where username = :username";
        Query query = session.createQuery(queryString);
        query.setParameter("username", username);
        user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void createUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUser(Long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        User user = new User();
        user.setId(id);
        session.delete(user);

        session.getTransaction().commit();
        session.close();
    }
}
