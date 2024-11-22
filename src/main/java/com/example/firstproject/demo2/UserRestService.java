package com.example.firstproject.demo2;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/users")
public class UserRestService {

  UserService userService = new UserService();

  // Endpoint to add a new user
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public boolean addUser(User user) {
    try {
      userService.saveUser(user);
      return true; // Returns true on success
    } catch (Exception e) {
      System.out.println("Error while adding user: " + e.getMessage());
      return false; // Returns false if there was an error
    }
  }

  // Endpoint to get a user by ID
  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public User getUser(@PathParam("id") Long id) {
    Optional<User> user = userService.getUserById(id);
    return user.orElse(null); // Returns null if user is not found
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public User[] getAllUsers() {
    List<User> users = userService.getAllUsers();
    return users.stream().toArray(User[]::new); // Use stream to convert list to array
  }

  // Endpoint to update an existing user
  @PUT
  @Path("/{id}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public User updateUser(@PathParam("id") Long id, User userDetails) {
    try {
      User updatedUser = userService.updateUser(id, userDetails);
      return updatedUser != null ? updatedUser : null; // Returns null if user is not found
    } catch (Exception e) {
      System.out.println("Error while updating user: " + e.getMessage());
      return null; // Returns null if there was an error
    }
  }

  // Endpoint to delete a user by ID
  @DELETE
  @Path("/{id}")
  public boolean deleteUser(@PathParam("id") Long id) {
    try {
      userService.deleteUser(id);
      return true; // Returns true on successful deletion
    } catch (Exception e) {
      System.out.println("Error while deleting user: " + e.getMessage());
      return false; // Returns false if there was an error
    }
  }
}
