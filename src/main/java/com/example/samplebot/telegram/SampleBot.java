package com.example.samplebot.telegram;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.example.samplebot.data.CallFunction;
import com.example.samplebot.data.Option;
import com.example.samplebot.service.DynamicViews;
import com.example.samplebot.service.ViewHandlerFunctions;
import com.example.samplebot.service.dynamic_views.DynamicView;
import com.example.samplebot.service.view_handlers.ViewHandlerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.example.samplebot.data.UserVO;
import com.example.samplebot.data.View;
import com.example.samplebot.repository.UserRepository;
import com.example.samplebot.service.MessageHelperService;
import com.example.samplebot.service.ViewService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SampleBot extends TelegramLongPollingCommandBot {

    @Autowired
    private ViewService viewService;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private MessageHelperService messageHelperService;

    @Autowired
    private ViewHandlerFunctions viewHandlerFunctions;

    @Autowired
    private DynamicViews dynamicViews;

    @PostConstruct
    public void init() {
//        register(new StartCommand("/start", "Старт", viewService, userRepository));
    }

    @Override
    public String getBotUsername() {
        return "visa_assistant__";
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        Message msg = update.getMessage();
        Long chatId = msg.getChatId();
        String username = getUserName(msg);
        String text = msg.getText();

        log.info("chatid={} {}: {}", chatId, username, text);
        
        if (text.equals("/start")) {
            handleStart(chatId);
        } else {
            handle(chatId, text);
        }
    }
    
    @Transactional
    public void handleStart(Long chatId) {
        
        UserVO vo;
        
        if (userRepository.existsByChatId(chatId)) {
            vo = userRepository.findByChatId(chatId).get();
            userRepository.delete(vo);
        }

        vo = UserVO.builder()
                .id(UUID.randomUUID())
                .chatId(chatId)
                .build();
        
        vo.setCurrentViewId("Приветствие");
        
        userRepository.save(vo);
        
        View view = viewService.findById("Приветствие").get();
        sendView(chatId, view);
    }
    
    @Transactional
    public void handle(Long chatId, String text) {
        Optional<UserVO> optional = userRepository.findByChatId(chatId);
        if (optional.isEmpty()) {
            handleStart(chatId);
        }
        
        UserVO userVO = optional.get();

        View currentView = viewService.findById(userVO.getCurrentViewId()).get();

        String nextViewId = "";

        if (currentView.getCallFunction() != null) {
            CallFunction fun = currentView.getCallFunction();
            ViewHandlerResult viewHandlerResult = viewHandlerFunctions.execute(userVO, fun, text);
            if (viewHandlerResult.getNextViewName() != null) {
                nextViewId = viewHandlerResult.getNextViewName();
            }

            if (viewHandlerResult.getTextToSend() != null) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId + "");
                sendMessage.setText(viewHandlerResult.getTextToSend());
                try {
                    execute(sendMessage);
                } catch (Exception e) {
                    log.error("", e);
                }
            }
        } else {

            Option option = viewService.findCurrentOption(userVO.getCurrentViewId(), text);

            nextViewId = option.getNextViewId();

            if (option.getCallFunction() != null) {
                CallFunction fun = option.getCallFunction();
                ViewHandlerResult viewHandlerResult = viewHandlerFunctions.execute(userVO, fun, text);
                if (viewHandlerResult.getNextViewName() != null) {
                    nextViewId = viewHandlerResult.getNextViewName();
                }

                if (viewHandlerResult.getTextToSend() != null) {
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setChatId(chatId + "");
                    sendMessage.setText(viewHandlerResult.getTextToSend());
                    try {
                        execute(sendMessage);
                    } catch (Exception e) {
                        log.error("", e);
                    }
                }
            }
        }

//        Optional<View> optionalView = viewService.findNextViewByCurrentIdAndOptionText(userVO.getCurrentViewId(), text);
        Optional<View> optionalView = viewService.findById(nextViewId);
        
        if (optionalView.isPresent()) {
            View view = optionalView.get();

            if (view.getDynamicViewName() != null) {
                String newText = dynamicViews.buildViewText(userVO, view.getDynamicViewName());
                view = view.toBuilder()
                        .text(newText)
                        .build();
            }

            userVO.setCurrentViewId(view.getId());
            userRepository.save(userVO);
            
            sendView(chatId, view);
        }
    }
    
    public void sendView(Long chatId, View view) {
        SendMessage msg = new SendMessage();
        msg.setChatId("" + chatId);
        msg.setText(view.getText());
        messageHelperService.addButtons(msg, view.getOptions());
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            log.error("", e);
        }
    }

    @Override
    public String getBotToken() {
        return "6222590387:AAHxAAsUZ3WwUhGADkyBiwdY7VsSF0DtQUg";
    }

    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }
}
