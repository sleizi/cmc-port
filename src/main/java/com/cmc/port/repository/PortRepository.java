package com.cmc.port.repository;

import com.cmc.port.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, String> {
}
