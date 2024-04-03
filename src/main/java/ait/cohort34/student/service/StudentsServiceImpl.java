package ait.cohort34.student.service;

import ait.cohort34.student.dao.StudentRepository;
import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.dto.exception.StudentNotFoundException;
import ait.cohort34.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class StudentsServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Boolean addStudent(StudentAddDto studentAddDto) {
        if (studentRepository.findById(studentAddDto.getId()).isPresent()){
            return false;
        }
        Student student = new Student(studentAddDto.getId(),studentAddDto.getName(),studentAddDto.getPassword());
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudent(Integer id) {
        Student student=studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        return new StudentDto(id,student.getName(),student.getScores());
    }

    @Override
    public StudentDto removeStudent(Integer id) {
        Student student=studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        studentRepository.remove(id);
        return new StudentDto(id,student.getName(),student.getScores());
    }

    @Override
    public StudentAddDto updateStudents(Integer id, StudentUpdateDto studentUpdateDto) {
       Student student= studentRepository.updateStudents(id,studentUpdateDto);
        return new StudentAddDto(student.getId(),student.getName(),student.getPassword());
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        if (scoreDto ==null){
            return false;
        }
        else {
            studentRepository.addScore(id, scoreDto);
            return true;
        }
    }

    @Override
    public Iterable<StudentDto> findByName(String name) {
        Set<StudentDto> studentDto=new HashSet<>();
        Iterable<Student> student=studentRepository.findByName(name);
        for (Student stud:student){
            studentDto.add(new StudentDto(stud.getId(),stud.getName(),stud.getScores()));
        }
        return studentDto;
    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> name) {
        return studentRepository.getStudentsNamesQuantity(name);
    }

    @Override
    public Iterable<StudentDto> findByExamMinScore(String exam, Integer minScore) {
        Set<StudentDto> studentDto=new HashSet<>();
        Iterable<Student> student=studentRepository.findByExamMinScore(exam,minScore);
        for (Student stud:student){
            studentDto.add(new StudentDto(stud.getId(),stud.getName(),stud.getScores()));
        }
        return studentDto;
    }
}
