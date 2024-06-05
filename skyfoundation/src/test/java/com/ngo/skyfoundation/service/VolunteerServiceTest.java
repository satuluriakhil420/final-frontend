package com.ngo.skyfoundation.service;


import com.ngo.skyfoundation.entity.Volunteer;
import com.ngo.skyfoundation.exception.ErrorMessage;
import com.ngo.skyfoundation.repository.VolunteerRespository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VolunteerServiceTest {

    @InjectMocks
    private VolunteerService volunteerService;

    @Mock
    private VolunteerRespository volunteerRespository;

    @Mock
    private EmailSenderService emailSenderService;

    private static final Logger logger = LoggerFactory.getLogger(VolunteerService.class);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveRegister_Success() throws Exception {
        // Given
        Volunteer volunteer = new Volunteer();
        volunteer.setName("akhil");
        volunteer.setEmail("akhil@gmail.com");
        volunteer.setAddress("123 Main St");
        volunteer.setContactNumber(1234567890L);
        volunteer.setWhatInspiredYouToBeAVolunteer("Helping others");

        when(volunteerRespository.findByEmail(volunteer.getEmail())).thenReturn(null);
        when(volunteerRespository.save(any(Volunteer.class))).thenReturn(volunteer);

        // When
        Volunteer result = volunteerService.saveRegister(volunteer);

        // Then
        assertNotNull(result);
        assertEquals("akhil", result.getName());
        assertEquals("akhil@gmail.com", result.getEmail());
        verify(emailSenderService, times(1)).sendMail(
                ("akhil@gmail.com"),
                ("welcome akhil"),
                ("Thank you for registering as a volunteer with us. We're excited to have you on board.")
        );
    }

    @Test
    void testSaveRegister_EmailAlreadyRegistered() {
        // Given
        Volunteer volunteer = new Volunteer();
        volunteer.setName("akhil");
        volunteer.setEmail("akhil@gmail.com");

        when(volunteerRespository.findByEmail(volunteer.getEmail())).thenReturn(volunteer);

        // When
        Exception exception = assertThrows(ErrorMessage.class, () -> {
            volunteerService.saveRegister(volunteer);
        });

        // Then
        assertEquals("Email is Already Registered", exception.getMessage());
        verify(volunteerRespository, times(1)).findByEmail(volunteer.getEmail());
        verify(volunteerRespository, times(0)).save(any(Volunteer.class));
        verify(emailSenderService, times(0)).sendMail(anyString(), anyString(), anyString());
    }
}

