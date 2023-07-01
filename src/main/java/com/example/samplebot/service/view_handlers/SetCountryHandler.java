package com.example.samplebot.service.view_handlers;

import com.example.samplebot.data.Country;
import com.example.samplebot.data.UserVO;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SetCountryHandler implements ViewHandler {

    @Data
    @Builder
    public static class Params {
        Long chatId;
        String countryDescription;
    }

    @Override
    public void run(UserVO userVO, List<String> args) {
        Country country = Arrays.stream(Country.values())
                .filter(v -> v.description.equals(args.get(0)))
                .findAny().get();
        userVO.setCountry(country);
        userVO.setCity(args.get(1));
    }
}
