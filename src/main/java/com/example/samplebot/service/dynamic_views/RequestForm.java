package com.example.samplebot.service.dynamic_views;

import com.example.samplebot.data.UserVO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RequestForm implements DynamicView {

    private String absent = "\uD83D\uDFE5";
    private String existing = "\uD83D\uDFE9";


    @Override
    public String buildText(UserVO userVO) {
        String email = Optional.ofNullable(userVO.getEmail()).orElse("-");
        String pwd = Optional.ofNullable(userVO.getPassword()).orElse("-");
        Integer amount = Optional.ofNullable(userVO.getAmount()).orElse(1);
        String price = "...";
        String min = Optional.ofNullable(userVO.getMinDate()).map(v -> v.toString()).orElse("-");
        String max = Optional.ofNullable(userVO.getMaxDate()).map(v -> v.toString()).orElse("-");
        String daysReserve = Optional.ofNullable(userVO.getDaysReserve()).orElse(0).toString();

        return "Заявка:  " + userVO.getCountry().description + " – " + userVO.getCity() + "\n" +
                "\uD83D\uDFE5 E-mail: " + email + "\n"
                + "\uD83D\uDFE5 Пароль: " + pwd + "\n"
                + " \uD83D\uDFE9 Кол-во заявителей: " + amount + " чел. (" + price + " руб.)\n" +
                "\uD83D\uDFE5 Период для записи: от " + min + " до " + max + " с\n" +
                "запасом " + daysReserve + " дн. ⬜ Комментарий: –\n" +
                "⁉ Остались вопросы?\n" +
                "Мы с радостью ответим на них или\n" +
                "предоставим необходимую информацию в\n" +
                "чате поддержки\n";
    }
}
