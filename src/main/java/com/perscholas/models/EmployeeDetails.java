package com.perscholas.models;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Log4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class EmployeeDetails {

    //fields
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(columnDefinition = "INT(11) UNSIGNED")
    int eDId;
    String eDPhone;
    String eDAddress;

    public EmployeeDetails(String eDPhone, String eDAddress) {
        this.eDPhone = eDPhone;
        this.eDAddress = eDAddress;
    }
}
