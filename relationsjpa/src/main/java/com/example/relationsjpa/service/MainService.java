package com.example.relationsjpa.service;

import com.example.relationsjpa.command.MentorDto;
import com.example.relationsjpa.command.ProfessorDto;
import com.example.relationsjpa.command.StudentDto;
import com.example.relationsjpa.persistence.entity.Mentor;
import com.example.relationsjpa.persistence.entity.Professor;
import com.example.relationsjpa.persistence.entity.Student;
import com.example.relationsjpa.persistence.entity.Team;
import com.example.relationsjpa.persistence.repository.MentorRepository;
import com.example.relationsjpa.persistence.repository.ProfessorRepository;
import com.example.relationsjpa.persistence.repository.StudentRepository;
import com.example.relationsjpa.persistence.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MainService {

    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;
    private final TeamRepository teamRepository;
    private final MentorRepository mentorRepository;

    public MainService(StudentRepository studentRepository, TeamRepository teamRepository, MentorRepository mentorRepository,
                       ProfessorRepository professorRepository) {
        this.studentRepository = studentRepository;
        this.teamRepository = teamRepository;
        this.mentorRepository = mentorRepository;
        this.professorRepository = professorRepository;
    }

    public Team createTeam(Team teamToCreate) {

        Team createdTeam = teamRepository.save(teamToCreate);
        return createdTeam;
    }

    public StudentDto createStudent(Student studentToCreate, Long teamId) {
        Team team = teamRepository.findById(teamId).orElse(null);

        if (team != null) {
            studentToCreate.setTeam(team);

            Student createdStudent = studentRepository.save(studentToCreate);

            return StudentDto.builder()
                    .id(createdStudent.getId())
                    .name(createdStudent.getName())
                    .age(createdStudent.getAge())
                    .teamId(createdStudent.getTeam().getId())
                    .build();
        }

        return null;
    }

    public MentorDto createMentor(Mentor mentorToCreate, Long studentId) {

        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {

            mentorToCreate.setStudent(student);

            Mentor createdMentor = mentorRepository.save(mentorToCreate);

            return MentorDto.builder()
                    .id(createdMentor.getId())
                    .name(createdMentor.getName())
                    .age(createdMentor.getAge())
                    .studentId(createdMentor.getStudent().getId())
                    .build();
        }

        return null;
    }

    public ProfessorDto createProfessor(Professor professorToCreate, Long teamId) {

        Team team = teamRepository.findById(teamId).orElse(null);
        if (team != null) {

            professorToCreate.setTeams(new ArrayList<Team>());

            professorToCreate.getTeams().add(team);

            Professor createdProfessor = professorRepository.save(professorToCreate);

            return ProfessorDto.builder()
                    .id(createdProfessor.getId())
                    .name(createdProfessor.getName())
                    .age(createdProfessor.getAge())
                    .teamId(team.getId())
                    .build();
        }

        return null;
    }

}
