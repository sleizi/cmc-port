package com.cmc.port.controller;

import com.cmc.port.service.PortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ports")
public class PortController {
    private final PortService portService;

    @RequestMapping("/import")
    public void importLocations() {
        portService.readAndSavePorts("/app/data/ports.json");
    }
}
