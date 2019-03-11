package com.email.sender.emailsender.utils;

import com.email.sender.emailsender.models.EmailSend;

import java.util.Queue;

public class MyContext {

    private static ThreadLocal<Queue<EmailSend>> myContextThreadLocal = new ThreadLocal<Queue<EmailSend>>();

    public static EmailSend get() {
        return myContextThreadLocal.get().poll();
    }

    public static void add(EmailSend emailSend) {
        myContextThreadLocal.get().add(emailSend);
    }

    public static Queue<EmailSend> queue() {
        return myContextThreadLocal.get();
    }

}