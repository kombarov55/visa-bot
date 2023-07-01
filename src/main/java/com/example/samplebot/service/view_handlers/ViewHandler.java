package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.UserVO;

import java.util.List;

public interface ViewHandler {

    void run(UserVO userVO, List<String> args);

}
