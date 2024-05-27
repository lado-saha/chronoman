package com.minsih.chronoman.model;


/**
 * Represents a predefined task in the construction management system.
 * Each predefined task has an ID, name, description, and is associated
 * with a predefined activity.
 */
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "predefined_tasks")
public class PredefinedTask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "activity_id", nullable = false)
  private PredefinedActivity activity;

  @Column(nullable = false, length = 255)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  // Constructors, getters, and setters
}
