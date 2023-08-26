package com.mango.repository;

import com.mango.model.Panic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PanicRepository extends JpaRepository<Panic, Integer> {
}
