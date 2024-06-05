package com.ngo.skyfoundation.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Type;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String name;

    private long contactNumber;
    private String  address;

    private String email;

    @Column(name="what_Inspired_You_To_BeAVolunteer")
    private String whatInspiredYouToBeAVolunteer;
}
