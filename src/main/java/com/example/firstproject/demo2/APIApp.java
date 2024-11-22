package com.example.firstproject.demo2;

import java.util.HashSet;
import java.util.Set;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
@ApplicationPath("/api")
public class APIApp extends Application{
  private Set<Object> singletons;
  public APIApp() {
    singletons = new HashSet<Object>();
    singletons.add(new UserRestService());
  }
  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }
}