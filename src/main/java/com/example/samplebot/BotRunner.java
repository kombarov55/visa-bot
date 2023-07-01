package com.example.samplebot;

import com.example.samplebot.telegram.SampleBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.annotation.PostConstruct;

@Component
public class BotRunner {

    @Autowired
    private SampleBot sampleBot;

    @PostConstruct
    public void run() throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(sampleBot);
    }

}
