package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.UserVO;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class SetMaxDateHandler implements ViewHandler {

    @Override
    public ViewHandlerResult run(UserVO userVO, List<String> args, String input) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try {
            date  = sdf.parse(input);
        } catch (Exception e) {
            date = new Date(0);
        }

        userVO.setMaxDate(date);

        return ViewHandlerResult.builder()
                .textToSend("Замечательно! Теперь заполните остальные данные!")
                .nextViewName("Заполнение заявки")
                .build();
    }
}
