package com.minsih.chronoman.model;

/**
 * Represents a user in the construction management system.
 * Each user has an ID, hashed password, name, role, and timestamps for creation, 
 * last update, and last login.
 */
import lombok.Data;
import jakarta.persistence.*;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String password;

  @Column(length = 255)
  private String name;

  @Column(nullable = false, length = 50)
  private String role;

  @Column(nullable = false, updatable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedAt;

  @Temporal(TemporalType.TIMESTAMP)
  private Date lastLogin;

  // // Hash password before saving to the database
  // public void setPassword(String password) {
  //   BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
  //   this.password = passwordEncoder.encode(password);
  // }

  // Constructors, getters, and setters
}
