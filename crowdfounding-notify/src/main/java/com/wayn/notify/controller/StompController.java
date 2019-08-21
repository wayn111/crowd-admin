package com.wayn.notify.controller;

import com.wayn.commom.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Calendar;

@Controller
public class StompController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        instance.clear(Calendar.MINUTE);
        instance.clear(Calendar.SECOND);
        Calendar instance1 = Calendar.getInstance();
        instance1.add(Calendar.HOUR, -1);
        instance1.clear(Calendar.MINUTE);
        instance1.clear(Calendar.SECOND);

        System.out.println(instance.getTime());
        System.out.println(instance1.getTime());
    }

    @MessageMapping("/micro")
    public void micosoft(Response message) {
        System.out.println(message);
    }
}
