package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.UserVO;

import java.util.List;

public interface ViewHandler {

    ViewHandlerResult run(UserVO userVO, List<String> args, String input);

}
