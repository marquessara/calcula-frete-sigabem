package com.sigabem.calculafrete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigabem.calculafrete.model.CalculaFreteLog;

@Repository
public interface CalculaFreteLogRepository extends JpaRepository<CalculaFreteLog, Long> {

}

