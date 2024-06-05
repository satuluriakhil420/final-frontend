package com.ngo.skyfoundation.repository;

import com.ngo.skyfoundation.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRespository extends JpaRepository<Volunteer,Integer> {
    Volunteer findByEmail(String email);
}
