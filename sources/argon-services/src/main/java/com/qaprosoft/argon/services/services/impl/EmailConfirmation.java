package com.qaprosoft.argon.services.services.impl;


import com.qaprosoft.argon.models.db.Message;
import com.qaprosoft.argon.models.db.User;
import com.qaprosoft.argon.services.services.IEmailMessage;

import java.util.List;

public class EmailConfirmation implements IEmailMessage {

    private static final String TEMPLATE = "email_notification.ftl";

    private User user;
    private String message;
    private String url;
    private String subject;

    public EmailConfirmation(User user, String subject, String message, String url)
    {
        this.subject = subject;
        this.message = message;
        this.user = user;
        this.url = url;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String getSubject()
    {
        return subject;
    }

    @Override
    public String getText()
    {
        return message;
    }

    @Override
    public String getTemplate()
    {
        return TEMPLATE;
    }

    @Override
    public List<Message.Attachment> getAttachments()
    {
        return null;
    }
}
