package com.kaboutersoft.group.gtsciais.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaboutersoft.group.gtsciais.demo.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Define additional methods if needed
}
