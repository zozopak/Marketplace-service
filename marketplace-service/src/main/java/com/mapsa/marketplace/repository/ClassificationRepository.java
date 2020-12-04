package com.mapsa.marketplace.repository;

import com.mapsa.marketplace.model.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification,Long> {
}
