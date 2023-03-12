package org.msvdev.example.hibernate;

import jakarta.persistence.EntityManager;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class StudentDAO {


    public Student findByID(long id) {
        return executeForEntityManager(
                em -> em.find(Student.class, id)
        );
    }


    public List<Student> findAll() {
        return executeForEntityManager(
                em -> em.createQuery("from Student", Student.class)
                        .getResultList()
        );
    }


    public void insert(Student student) {
        executeForEntityManagerInTransaction(
                em -> em.persist(student)
        );
    }


    public void insert(Collection<Student> students) {
        executeForEntityManagerInTransaction(
                em -> students.forEach(em::persist)
        );
    }


    public void update(Student student) {
        executeForEntityManagerInTransaction(
                em -> em.merge(student)
        );
    }


    public void delete(long id) {
        executeForEntityManagerInTransaction(
                em -> {
                    Student student = em.find(Student.class, id);
                    if (student != null) {
                        em.remove(student);
                    }
                }
        );
    }


    private <R> R executeForEntityManager(Function<EntityManager, R> function) {
        EntityManager em = SessionFactoryGetter.getSessionFactory().createEntityManager();

        try {
            return function.apply(em);

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private void executeForEntityManagerInTransaction(Consumer<EntityManager> consumer) {
        EntityManager em = SessionFactoryGetter.getSessionFactory().createEntityManager();

        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
