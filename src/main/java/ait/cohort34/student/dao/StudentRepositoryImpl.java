package ait.cohort34.student.dao;

import ait.cohort34.student.dto.ScoreDto;
import ait.cohort34.student.dto.StudentDto;
import ait.cohort34.student.dto.StudentUpdateDto;
import ait.cohort34.student.dto.exception.StudentNotFoundException;
import ait.cohort34.student.model.Student;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StudentRepositoryImpl implements StudentRepository{
    private Map<Integer,Student> students=new HashMap<>();

    @Override
    public Student save(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public boolean remove(int id) {
        if (students.get(id)!=null){
            students.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Student updateStudents(Integer id, StudentUpdateDto studentUpdateDto) {
        Student student=findById(id).orElseThrow(StudentNotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        return student;
    }

    @Override
    public Boolean addScore(Integer id, ScoreDto scoreDto) {
        Student student=findById(id).orElseThrow(StudentNotFoundException::new);
        Map<String, Integer> scores = student.getScores();

        if (scores.containsKey(scoreDto.getExamName())) {
            return false;
        }
            student.addScore(scoreDto.getExamName(), scoreDto.getScore());
            return true;
        }

    @Override
    public Iterable<Student> findByName(String name) {
        List<Student> student =new ArrayList<>();
        for (Student stud:students.values()){
            if (stud.getName().equalsIgnoreCase(name)){
            student.add(stud);
            }
        }
        return student;
    }

    @Override
    public Optional<Student> findById(int id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public Long getStudentsNamesQuantity(Set<String> name) {
        Long count = 0L;
        for (Student student : students.values()) {
            if (name.contains(student.getName())) {
                count++;
            }
        }
        return count;
    }

    @Override
    public Iterable<Student> findByExamMinScore(String exam, Integer minScore) {
        Set<Student> studentes=new HashSet<>();
        for (Student student:students.values()){
            if (student.getScores().get(exam)>minScore){
                studentes.add(student);
            }
        }
        return studentes;
    }

    @Override
    public Collection<Student> getAll() {
        return students.values();
    }
}
