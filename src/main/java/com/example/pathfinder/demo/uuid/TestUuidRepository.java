package com.example.pathfinder.demo.uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TestUuidRepository extends JpaRepository<TestUuidEntity, UUID> {
}
