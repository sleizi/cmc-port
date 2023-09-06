package com.cmc.port.service.impl;

import com.cmc.port.entity.Port;
import com.cmc.port.repository.PortRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PortServiceImplTest {
    @Autowired
    private PortServiceImpl portService;

    @Autowired
    private PortRepository portRepository;

    @BeforeEach
    void setUp() {
        portService.readAndSavePorts("src/test/resources/ports-01.json");
    }

    @Test
    void readAndSavePorts_shouldSavePortsFromJsonFile() {
        // After reading and saving ports from ports-01.json, we have 2 ports saved
        assertEquals(2, portRepository.findAll().size());
        // The port with code 52000 is named "Ajman"
        portRepository.findById("52000").ifPresent(port -> assertEquals("Ajman", port.getName()));
    }

    @Test
    void readAndSavePorts_shouldUpdatePort() {
        portService.readAndSavePorts("src/test/resources/ports-02.json");
        // After reading and saving ports from ports-01.json, we still have 2 ports saved in the database
        assertEquals(2, portRepository.findAll().size());
        // The port with code 52000 has been updated to the name 'Ajman updated'
        portRepository.findById("52000").ifPresent(portUpdated -> assertEquals("Ajman updated", portUpdated.getName()));
    }
}