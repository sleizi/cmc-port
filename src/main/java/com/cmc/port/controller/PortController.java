package com.cmc.port.controller;

import com.cmc.port.service.PortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ports")
public class PortController {
    private final PortService portService;

    @PostMapping("/import")
    public void importLocations(@RequestParam String filePath) {
        portService.readAndSavePorts(filePath);
    }
}
