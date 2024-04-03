package ait.cohort34.student.service;

import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentAddDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.model.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentAddDto studentAddDto);
    StudentDto findStudent(Integer id);
    StudentDto removeStudent(Integer id);
    StudentAddDto updateStudents(Integer id,StudentUpdateDto studentUpdateDto);
    Boolean addScore(Integer id, ScoreDto scoreDto);
    Iterable<StudentDto> findByName(String name);
    Long getStudentsNamesQuantity(Set<String> name);
    Iterable<StudentDto> findByExamMinScore(String exam,Integer minScore);


}
