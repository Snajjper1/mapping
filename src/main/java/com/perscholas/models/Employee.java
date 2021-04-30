package com.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Employee {
    //fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "INT(11) UNSIGNED")
    int eId;
    String eName;
    String eEmail;
    double eSalary;



    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) @JoinColumn(name="eId")
    @ToString.Exclude
    List<EmployeeDetails> employeeDetails;


    public Employee(String eName, String eEmail, double eSalary) {
        this.eName = eName;
        this.eEmail = eEmail;
        this.eSalary = eSalary;
    }
}

