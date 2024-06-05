package com.ngo.skyfoundation.controller;

import com.ngo.skyfoundation.entity.Volunteer;
import com.ngo.skyfoundation.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("volunteer")
public class Volunteercontroller {

    private final VolunteerService volunteerService;


    public Volunteercontroller(VolunteerService volunteerService) {
        this.volunteerService = volunteerService;
    }

    @PostMapping("/RegisterVolunteer")
    public ResponseEntity<Volunteer> saveData(@RequestBody Volunteer volunteer) throws Exception {
        return new ResponseEntity<>(volunteerService.saveRegister(volunteer), HttpStatus.OK);
    }

}
