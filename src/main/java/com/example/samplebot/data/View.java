package com.example.samplebot.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor

public class View {
    private String id;

    private String text;

    private String dynamicViewName;

    private List<Option> options;
}
