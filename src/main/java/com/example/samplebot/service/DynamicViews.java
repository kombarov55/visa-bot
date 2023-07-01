package com.example.samplebot.service;

import com.example.samplebot.data.UserVO;
import com.example.samplebot.service.dynamic_views.DynamicView;
import com.example.samplebot.service.dynamic_views.RequestSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class DynamicViews {

    private Map<String, DynamicView> views = new HashMap<>();

    @Autowired
    private RequestSummary requestSummary;

    @PostConstruct
    public void init() {
        views.put("request_summary", requestSummary);
    }

    public String buildViewText(UserVO userVO, String name) {
        return views.get(name).buildText(userVO);
    }

}
