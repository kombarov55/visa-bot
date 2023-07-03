package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SetAmountHandler implements ViewHandler {

    @Override
    public ViewHandlerResult run(UserVO userVO, List<String> args, String input) {
        userVO.setAmount(Integer.parseInt(input));
        return ViewHandlerResult.builder()
                .textToSend("Замечательно! Теперь заполните остальные данные!")
                .nextViewName("Заполнение заявки")
                .build();
    }
}
