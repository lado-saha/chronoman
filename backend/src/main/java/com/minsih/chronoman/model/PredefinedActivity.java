package com.minsih.chronoman.model;


/**
 * Represents a predefined activity in the construction management system.
 * Each predefined activity has an ID, name, and description.
 */
import lombok.Data;

import jakarta.persistence.*;;

@Data
@Entity
@Table(name = "predefined_activities")
public class PredefinedActivity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  // Constructors, getters, and setters
}
