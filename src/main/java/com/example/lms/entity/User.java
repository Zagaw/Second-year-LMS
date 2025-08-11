/*package com.example.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_courses", // join table name
            joinColumns = @JoinColumn(name = "user_id"), // FK to User table
            inverseJoinColumns = @JoinColumn(name = "course_id") // FK to Course table
    )
    private Set<Course> courses = new HashSet<>();
}*/

package com.example.lms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    @Column(nullable = false)
    private String role;  // add this: role like "ROLE_TEACHER" or "ROLE_STUDENT"

    @ManyToMany
    @JoinTable(
            name = "user_courses", // join table name
            joinColumns = @JoinColumn(name = "user_id"), // FK to User table
            inverseJoinColumns = @JoinColumn(name = "course_id") // FK to Course table
    )
    @JsonManagedReference  // Allow serializing courses inside user
    @JsonIgnore // prevents it from being deserialized unless explicitly included
    private Set<Course> courses = new HashSet<>();
}




