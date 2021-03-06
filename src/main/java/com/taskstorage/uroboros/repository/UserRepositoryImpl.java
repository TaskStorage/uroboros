package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    @Cacheable(value = "users")
    public List<User> selectAll() {
        List<User> users;
        session = sessionFactory.openSession();
        session.beginTransaction();
        users = session.createQuery("from User").list();
        session.close();
        return users;
    }

    @Override
    @Cacheable(value = "users")
    public User selectById(Long id) {
        User user;
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
    @Cacheable(value = "users")
    public User selectByUsername(String username) {
        User user;
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
    public User findByActivationCode(String code) {
        User user;
        session = sessionFactory.openSession();
        session.beginTransaction();
        String queryString = "from User where activationCode = :code";
        Query query = session.createQuery(queryString);
        query.setParameter("code", code);
        user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    //Alternate to @CacheEvict
    //CacheManager manager = CacheManager.getInstance();
    //Ehcache cache = manager.getCache("users");
    //cache.removeAll();
    public void createUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public void updateUser(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
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
