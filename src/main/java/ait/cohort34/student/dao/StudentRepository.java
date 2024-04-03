package ait.cohort34.student.dao;

import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.model.Student;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public interface StudentRepository {
    Student save(Student student);
    boolean remove(int id);
    Student updateStudents(Integer id, StudentUpdateDto studentUpdateDto);
    Boolean addScore(Integer id, ScoreDto scoreDto);
    Iterable<Student> findByName(String name);
    Optional<Student> findById(int id);
    Long getStudentsNamesQuantity(Set<String> name);
    Iterable<Student> findByExamMinScore(String exam, Integer minScore);
    Collection<Student> getAll();
}
