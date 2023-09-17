package com.example.ekyc.repository;

import com.example.ekyc.entity.IndividualApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IndividualApplicationRepository extends JpaRepository<IndividualApplicationEntity, UUID> {
}
