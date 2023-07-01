package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetEmailHandler implements ViewHandler {

    @Override
    public ViewHandlerResult run(UserVO userVO, List<String> args, String input) {
        userVO.setEmail(input);
        return ViewHandlerResult.builder()
                .nextViewName("Заполнение заявки")
                .textToSend("Замечательно! Теперь заполните остальные данные!")
                .build();
    }
}
