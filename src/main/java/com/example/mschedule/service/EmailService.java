package com.example.mschedule.service;

import com.example.mschedule.entity.User;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessageWithPassword(User user, String password) throws MessagingException;

}
