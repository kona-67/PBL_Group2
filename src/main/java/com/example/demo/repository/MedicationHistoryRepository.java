package com.example.demo.repository;

import com.example.demo.entity.MedicationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationHistoryRepository extends JpaRepository<MedicationHistory, Integer> {
}
