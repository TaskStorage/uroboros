package com.taskstorage.uroboros.repository;

import com.taskstorage.uroboros.model.Task;
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
public class TaskRepositoryImpl implements TaskRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    @Override
    @Cacheable(value = "tasks")
    public List<Task> selectAll() {
        List<Task> tasks = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        tasks = session.createQuery("from Task").list();
        session.close();
        return tasks;
    }

    @Override
    @Cacheable(value = "tasks")
    public List<Task> selectByUser(User user) {
        List<Task> tasks = null;
        session = sessionFactory.openSession();
        session.beginTransaction();

        String queryString = "from Task where user_id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", user.getId());

        tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    @Cacheable(value = "tasks",key = "#id")
    public Task selectById(Long id) {
        Task task = null;
        session = sessionFactory.openSession();
        session.beginTransaction();
        // Select with query
        String queryString = "from Task where id = :id";
        Query query = session.createQuery(queryString);
        query.setParameter("id", id);

        // Select with built-in method
        // user = session.get(Task.class, id);

        task = (Task) query.uniqueResult();

        session.close();
        return task;
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void createTask(Task task) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void updateTask(Task task) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    @CacheEvict(value = "tasks", allEntries = true)
    public void deleteTask(Long id) {
        session = sessionFactory.openSession();
        session.beginTransaction();

        Task task = new Task();
        task.setId(id);
        session.delete(task);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> findByDescriptionContainingOrContentContaining(String searchTag) {
        List<Task> tasks = null;
        session = sessionFactory.openSession();
        session.beginTransaction();

        String queryString = "from Task where description like :searchTag or content like :searchTag";
        Query query = session.createQuery(queryString);
        query.setParameter("searchTag", "%"+searchTag+"%");

        tasks = query.getResultList();
        session.close();
        return tasks;
    }

    @Override
    public List<Task> findByDescriptionContainingAndAuthorOrContentContainingAndAuthor(String searchTag, User user) {
        List<Task> tasks = null;
        session = sessionFactory.openSession();
        session.beginTransaction();

        String queryString = "from Task where description like :searchTag and user_id = :user or content like :searchTag and user_id = :user";
        Query query = session.createQuery(queryString);
        query.setParameter("searchTag", "%"+searchTag+"%");
        query.setParameter("user", user.getId());

        tasks = query.getResultList();
        session.close();
        return tasks;
    }
}
