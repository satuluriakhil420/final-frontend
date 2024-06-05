package com.ngo.skyfoundation.controller;


import com.ngo.skyfoundation.entity.Volunteer;
import com.ngo.skyfoundation.service.VolunteerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


class VolunteerControllerTest {

    @InjectMocks
    private Volunteercontroller volunteerController;

    @Mock
    private VolunteerService volunteerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveData() throws Exception {
        // Given
        Volunteer volunteer = new Volunteer();
        volunteer.setId(1);
        volunteer.setName("akhil");
        // Set other properties of Volunteer

        // When
        when(volunteerService.saveRegister(volunteer)).thenReturn(volunteer);

        // Then
        ResponseEntity<Volunteer> response = volunteerController.saveData(volunteer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(volunteer, response.getBody());
    }
}

