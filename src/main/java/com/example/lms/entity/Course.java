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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id") // DB column name
    private Long courseId;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses") // "courses" refers to the field in User
    private Set<User> users = new HashSet<>();
}*/
package com.example.lms.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id") // DB column name
    private Long courseId;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "courses") // "courses" refers to the field in User
    @JsonIgnore
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Material> materials = new HashSet<>();
}



