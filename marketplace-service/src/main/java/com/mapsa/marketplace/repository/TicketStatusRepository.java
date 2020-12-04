package com.mapsa.marketplace.repository;

import com.mapsa.marketplace.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketStatusRepository extends JpaRepository<TicketStatus,Long> {
}
