package ait.cohort34.student.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@EqualsAndHashCode(of = "id")
@Getter
public class Student {
    private int id;
    @Setter
    private String name;
    @Setter
    private String password;

    private Map<String,Integer> scores;

    public Student(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        scores = new HashMap<>();
    }

    public Boolean addScore(String exam,int scor){

        return scores.put(exam,scor) == null;
    }
}
