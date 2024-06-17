package com.example.aims.repository.admin;

import com.example.aims.constant.UserRole;
import com.example.aims.constant.UserStatus;
import com.example.aims.entity.user.User;
import com.example.aims.exception.user.IncorrectOldPasswordException;
import com.example.aims.repository.AIMSDB;

import javax.persistence.EntityManager;
import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {
    // TODO implement all methods
    @Override
    public List<User> getUsers(int userId) {
        EntityManager em = AIMSDB.getEntityManager();
        List<User> users = em.createQuery("select u from User u where u.id <> "+ userId, User.class).getResultList();
        users.forEach(em::refresh);
        return users;
    }

    /**
     * @param user need to create corresponding User: ProductManager or Admin and pass to method
     */
    @Override
    public void addUser(User user) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void removeUser(User user) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.remove(user);
        em.getTransaction().commit();
    }

    @Override
    public User getUserById(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        User user = em.find(User.class, id);
        em.refresh(user);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        EntityManager em = AIMSDB.getEntityManager();
        User user = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        return user;
    }

    @Override
    public void updateUser(User updatedUser, int userId) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, userId);
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPhone(updatedUser.getPhone());
        user.setRole(updatedUser.getRole());
        user.setUserStatus(updatedUser.getUserStatus());
        user.setAddress(updatedUser.getAddress());
        em.persist(user);
        em.getTransaction().commit();
    }

    @Override
    public void blockUser(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("update User u set u.userStatus= :userStatus where u.id=" + id)
                .setParameter("userStatus", UserStatus.BLOCKED)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void unblockUser(int id) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("update User u set u.userStatus=:userStatus where u.id=" + id)
                .setParameter("userStatus", UserStatus.UNBLOCKED)
                .executeUpdate();
        em.getTransaction().commit();
    }

    @Override
    public void changePassword(int id, String oldPassword, String newPassword) throws IncorrectOldPasswordException {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.refresh(user);
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            em.persist(user);
            em.getTransaction().commit();
        } else {
            em.getTransaction().commit();
            throw new IncorrectOldPasswordException();
        }

    }

    @Override
    public void updateUserRole(int id, UserRole role) {
        EntityManager em = AIMSDB.getEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        user.setRole(role);
        em.merge(user);
        em.getTransaction().commit();
    }
}
