package com.example.relationsjpa.service;

import com.example.relationsjpa.command.MentorDto;
import com.example.relationsjpa.command.StudentDto;
import com.example.relationsjpa.persistence.entity.Mentor;
import com.example.relationsjpa.persistence.entity.Student;
import com.example.relationsjpa.persistence.entity.Team;
import com.example.relationsjpa.persistence.repository.MentorRepository;
import com.example.relationsjpa.persistence.repository.StudentRepository;
import com.example.relationsjpa.persistence.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final StudentRepository studentRepository;
    private final TeamRepository teamRepository;
    private final MentorRepository mentorRepository;

    public MainService(StudentRepository studentRepository, TeamRepository teamRepository, MentorRepository mentorRepository) {
        this.studentRepository = studentRepository;
        this.teamRepository = teamRepository;
        this.mentorRepository = mentorRepository;
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
        System.out.println("YEAHHHHHHHHHHHH");
        System.out.println(studentId);

        System.out.println(studentRepository.existsById(studentId));

        Student student = studentRepository.findById(studentId).orElse(null);

        if (student != null) {

            System.out.println(student);

            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            mentorToCreate.setStudent(student);

            Mentor createdMentor = mentorRepository.save(mentorToCreate);

            return MentorDto.builder()
                    .id(createdMentor.getId())
                    .name(createdMentor.getName())
                    .age(createdMentor.getAge())
                    .studentId(createdMentor.getStudent().getId())
                    .build();
        }

        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");

        return null;
    }

}
