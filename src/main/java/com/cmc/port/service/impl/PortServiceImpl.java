package com.cmc.port.service.impl;


import com.cmc.port.entity.Port;
import com.cmc.port.repository.PortRepository;
import com.cmc.port.service.PortService;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional // to make sure nothing will be saved into the database if an error occurs while parsing the file
public class PortServiceImpl implements PortService {
    private final PortRepository portRepository;
    public static final int BATCH_SIZE = 1000;

    @Override
    public void readAndSavePorts(String filePath) {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jsonFactory = objectMapper.getFactory();

        try (JsonParser jsonParser = jsonFactory.createParser(new File(filePath))) {

            List<Port> portBatch = new ArrayList<>();
            String currentObjectName = null;

            // Loop to read each JSON object individually
            while (jsonParser.nextToken() != null) {
                // Check if the current token is a field name
                if (jsonParser.currentToken() == JsonToken.FIELD_NAME) {
                    // Store the field name (object name)
                    currentObjectName = jsonParser.getCurrentName();
                } else if (jsonParser.currentToken() == JsonToken.START_OBJECT && currentObjectName != null) {
                    Port port = objectMapper.readValue(jsonParser, Port.class);
                    portBatch.add(port);

                    // Save the batch into the database when the batch size is reached
                    if (portBatch.size() / BATCH_SIZE == 0) {
                        portRepository.saveAll(portBatch);
                        portBatch.clear();
                    }
                }
            }
            // Save leftover ports in the last batch
            portRepository.saveAll(portBatch);

        } catch (JsonParseException e) {
            log.error("An error occurred while parsing Json objects: {}", e.getMessage(), e);
        } catch (IOException e) {
            log.error("An error occurred while reading file: {}", e.getMessage(), e);
        }
    }
}