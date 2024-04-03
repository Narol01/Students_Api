package ait.cohort34.student.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAddDto {
    private int id;
    private String name;
    private String password;
}
