package com.example.samplebot.service.view_handlers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewHandlerParams {
    private Long chatId;
    private String text;
}
