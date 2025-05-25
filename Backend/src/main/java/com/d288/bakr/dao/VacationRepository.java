package com.d288.bakr.dao;

import com.d288.bakr.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.stereotype.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@CrossOrigin("http://localhost:4200" )
public interface VacationRepository extends JpaRepository<Vacation, Long> {
}
