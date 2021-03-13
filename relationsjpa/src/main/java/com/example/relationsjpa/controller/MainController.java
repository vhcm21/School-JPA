package com.example.relationsjpa.controller;

import com.example.relationsjpa.command.MentorDto;
import com.example.relationsjpa.command.ProfessorDto;
import com.example.relationsjpa.command.StudentDto;
import com.example.relationsjpa.persistence.entity.Mentor;
import com.example.relationsjpa.persistence.entity.Professor;
import com.example.relationsjpa.persistence.entity.Student;
import com.example.relationsjpa.persistence.entity.Team;
import com.example.relationsjpa.service.MainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/teams")
    public ResponseEntity<Team> createTeam(@RequestBody Team teamToCreate) {
        Team createdTeam = mainService.createTeam(teamToCreate);

        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @PostMapping("teams/{teamId}/students")
    public ResponseEntity<StudentDto> createStudent(@PathVariable Long teamId,
                                                    @RequestBody Student studentToCreate) {
        StudentDto createdStudent = mainService.createStudent(studentToCreate, teamId);

        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PostMapping("students/{studentId}/mentors")
    public ResponseEntity<MentorDto> createMentor(@PathVariable Long studentId,
                                                   @RequestBody Mentor mentorToCreate) {
        MentorDto createdMentor = mainService.createMentor(mentorToCreate, studentId);

        return new ResponseEntity<>(createdMentor, HttpStatus.CREATED);
    }

    @PostMapping("teams/{teamId}/professors")
    public ResponseEntity<ProfessorDto> createProfessor(@PathVariable Long teamId,
                                                        @RequestBody Professor professorToCreate) {
        ProfessorDto createdProfessor = mainService.createProfessor(professorToCreate, teamId);

        return new ResponseEntity<>(createdProfessor, HttpStatus.CREATED);
    }
}
