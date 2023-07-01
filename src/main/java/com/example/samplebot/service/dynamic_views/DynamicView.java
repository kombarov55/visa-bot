package com.example.samplebot.service.dynamic_views;

import com.example.samplebot.data.UserVO;

public interface DynamicView {

    String buildText(UserVO userVO);

}
