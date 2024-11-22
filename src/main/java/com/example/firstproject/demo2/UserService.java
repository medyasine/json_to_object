package com.example.firstproject.demo2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final EntityManager entityManager;

    public UserService() {
        // Initialize EntityManager
        this.entityManager = EntityManagerProvider.getEntityManager();
    }

    // Create or Update a User
    public User saveUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (user.getId() == null) {
                // If there's no ID, create a new user
                entityManager.persist(user);
            } else {
                // If there is an ID, update the existing user
                entityManager.merge(user);
            }
            transaction.commit();
            return user;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback on error
            }
            throw e;
        }
    }

    // Get a User by ID
    public Optional<User> getUserById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    // Get all Users
    public List<User> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // Delete a User by ID
    public void deleteUser(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                entityManager.remove(user);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback on error
            }
            throw e;
        }
    }

    // Update User (same as save but ensures the user exists)
    public User updateUser(Long id, User userDetails) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            User user = entityManager.find(User.class, id);
            if (user != null) {
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                user = entityManager.merge(user); // Updates existing user
            }
            transaction.commit();
            return user;
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback on error
            }
            throw e;
        }
    }
}
