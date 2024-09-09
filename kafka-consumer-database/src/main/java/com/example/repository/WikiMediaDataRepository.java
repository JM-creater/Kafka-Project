package com.example.repository;

import com.example.entity.WikiMediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikiMediaDataRepository extends JpaRepository<WikiMediaData, Long> {
}
