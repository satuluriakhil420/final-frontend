package com.ngo.skyfoundation.service;

import com.ngo.skyfoundation.entity.Volunteer;
import com.ngo.skyfoundation.exception.ErrorMessage;
import com.ngo.skyfoundation.repository.VolunteerRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    private static final Logger logger = LoggerFactory.getLogger(VolunteerService.class);


    private final VolunteerRespository volunteerRespository;
    private final EmailSenderService emailSenderService;

    public VolunteerService(VolunteerRespository volunteerRespository, EmailSenderService emailSenderService) {
        this.volunteerRespository = volunteerRespository;
        this.emailSenderService = emailSenderService;
    }
    public Volunteer saveRegister(Volunteer volunteer) throws Exception {
        logger.info("Attempting to register volunteer with email: {}", volunteer.getEmail());

        Volunteer existingVolunteer = volunteerRespository.findByEmail(volunteer.getEmail());
        if (existingVolunteer != null) {
            logger.warn("Email is already registered: {}", volunteer.getEmail());
            throw new ErrorMessage("Email is Already Registered");
        }

        logger.debug("Creating new Volunteer entity with details: {}", volunteer);

        // Create and save the new volunteer
        Volunteer volunteer1 = new Volunteer();
        volunteer1.setName(volunteer.getName());
        volunteer1.setEmail(volunteer.getEmail());
        volunteer1.setAddress(volunteer.getAddress());
        volunteer1.setContactNumber(volunteer.getContactNumber());
        volunteer1.setWhatInspiredYouToBeAVolunteer(volunteer.getWhatInspiredYouToBeAVolunteer());

        logger.info("Saving new volunteer with email: {}", volunteer1.getEmail());

        Volunteer saveVolunteer = volunteerRespository.save(volunteer1);

        emailSenderService.sendMail(saveVolunteer.getEmail(),
                "welcome " + volunteer1.getName(),
                "Thank you for registering as a volunteer with us."
                        + " We're excited to have you on board.");

        return volunteerRespository.save(volunteer1);
    }
}
