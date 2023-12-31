package com.example.ekyc.repository;

import com.example.ekyc.entity.OrganizationApplicationEntity;
import com.example.ekyc.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationApplicationRepository extends JpaRepository<OrganizationApplicationEntity, UUID> {
}
