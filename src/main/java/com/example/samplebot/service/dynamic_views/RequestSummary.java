package com.example.samplebot.service.dynamic_views;

import com.example.samplebot.data.UserVO;
import org.springframework.stereotype.Component;

@Component
public class RequestSummary implements DynamicView {

    @Override
    public String buildText(UserVO userVO) {
        String country = userVO.getCountry().description;
        String city = userVO.getCity();

        return country + " – " + city + "\n" +
                "Тарифы:\n" +
                "\uD83D\uDC64 1 заявитель – ..... руб. \uD83D\uDC65 2 заявителя – ..... руб. \uD83E\uDEC2 3 заявителя – ....... руб. \uD83D\uDC64 +1 заявитель – ......... руб.\n" +
                "---\n" +
                "⚠ ПЕРЕД ЗАПОЛНЕНИЕМ ЗАЯВКИ\n" +
                "УБЕДИТЕСЬ, ЧТО:\n" +
                "✅ Создан Личный кабинет на\n" +
                "необходимую страну в системе «Official U.\n" +
                "S. Department of State Visa Appointment\n" +
                "Service» [ссылка]\n" +
                "✅ Оплачен консульский сбор в размере $\n" +
                "160/190 в зависимости от типа визы";
    }
}
