package com.example.relationsjpa.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Student> students;

    @ManyToMany(mappedBy = "teams")
    private List<Professor> professors;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", year=" + year +
                ", name='" + name + '\'' +
                '}';
    }
}
