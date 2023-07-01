package com.example.samplebot.service;

import com.example.samplebot.data.CallFunction;
import com.example.samplebot.data.UserVO;
import com.example.samplebot.service.view_handlers.SetCountryHandler;
import com.example.samplebot.service.view_handlers.ViewHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ViewHandlerFunctions {

    @Autowired
    private SetCountryHandler setCountryHandler;

    private Map<String, ViewHandler> functions = new HashMap<>();

    @PostConstruct
    public void init() {
        functions.put("set_country", setCountryHandler);
    }

    public void execute(UserVO userVO, CallFunction fun) {
        functions.get(fun.getFunctionName()).run(userVO, fun.getArgs());
    }

}
