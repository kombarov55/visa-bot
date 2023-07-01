package com.example.samplebot.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.example.samplebot.data.Option;
import com.example.samplebot.data.View;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ViewService {

    private Map<String, View> idToView;

    @PostConstruct
    public void init() throws IOException {
        idToView = parseViews();
    }
    
    private Map<String, View> parseViews() throws IOException {
        File file = new File("C:\\Users\\komba\\IdeaProjects\\other\\sample-bot\\src\\main\\resources\\Views.json");
        String fileContent = "";
        Map<String, View> result = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            fileContent = br.lines().collect(Collectors.joining("\n"));
        }

        List<View> views = new ObjectMapper().readValue(fileContent, new TypeReference<>(){});
        for (View view : views) {
            result.put(view.getId(), view);
        }
        
        return result;
    }
    
    public Optional<View> findById(String id) {
        return Optional.ofNullable(idToView.get(id));
    }

    public Option findCurrentOption(String currentViewId, String text) {
        View view = idToView.get(currentViewId);
        return view.getOptions().stream()
                .filter(v -> v.getText().equals(text))
                .findAny().get();
    }

    public Optional<View> findNextViewByCurrentIdAndOptionText(String currentViewId, String optionText) {
        log.info("find next view: currentViewId={} optionText={}", currentViewId, optionText);
        View view = idToView.get(currentViewId);
        Option option = view.getOptions().stream()
            .filter(v -> v.getText().equals(optionText))
            .findAny().get();
        
        String nextViewId = option.getNextViewId();
        
        return Optional.ofNullable(idToView.get(nextViewId)); 
    }
}
