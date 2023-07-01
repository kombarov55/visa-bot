package com.example.samplebot.service;

import com.example.samplebot.data.CallFunction;
import com.example.samplebot.data.UserVO;
import com.example.samplebot.service.view_handlers.SetCountryHandler;
import com.example.samplebot.service.view_handlers.SetEmailHandler;
import com.example.samplebot.service.view_handlers.ViewHandler;
import com.example.samplebot.service.view_handlers.ViewHandlerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ViewHandlerFunctions {

    @Autowired
    private SetCountryHandler setCountryHandler;

    @Autowired
    private SetEmailHandler setEmailHandler;

    private Map<String, ViewHandler> functions = new HashMap<>();

    @PostConstruct
    public void init() {
        functions.put("set_country", setCountryHandler);
        functions.put("set_email", setEmailHandler);
    }

    public ViewHandlerResult execute(UserVO userVO, CallFunction fun, String input) {
        return functions.get(fun.getFunctionName()).run(userVO, fun.getArgs(), input);
    }

}
