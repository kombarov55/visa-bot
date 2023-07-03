package com.example.samplebot.service;

import com.example.samplebot.data.CallFunction;
import com.example.samplebot.data.UserVO;
import com.example.samplebot.service.view_handlers.*;
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

    @Autowired
    private SetPasswordHandler setPasswordHandler;

    @Autowired
    private SetAmountHandler setAmountHandler;

    @Autowired
    private SetMinDateHandler setMinDateHandler;

    @Autowired
    private SetMaxDateHandler setMaxDateHandler;

    @Autowired
    private SetDaysReserveHandler setDaysReserveHandler;

    private Map<String, ViewHandler> functions = new HashMap<>();

    @PostConstruct
    public void init() {
        functions.put("set_country", setCountryHandler);
        functions.put("set_email", setEmailHandler);
        functions.put("set_password", setPasswordHandler);
        functions.put("set_amount", setAmountHandler);
        functions.put("set_min_date", setMinDateHandler);
        functions.put("set_max_date", setMaxDateHandler);
        functions.put("set_days_reserve", setDaysReserveHandler);
    }

    public ViewHandlerResult execute(UserVO userVO, CallFunction fun, String input) {
        return functions.get(fun.getFunctionName()).run(userVO, fun.getArgs(), input);
    }

}
