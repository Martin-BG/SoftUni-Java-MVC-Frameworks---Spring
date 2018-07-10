package com.softuni.residentevil.repositories;

import com.softuni.residentevil.etities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CapitalRepository extends JpaRepository<Capital, Long> {

}
