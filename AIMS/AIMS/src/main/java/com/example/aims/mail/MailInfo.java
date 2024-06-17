package com.example.aims.mail;

public class MailInfo {
    private String toMail;
    private String subject;
    private String body;

    public MailInfo(String toMail, String subject, String body) {
        this.toMail = toMail;
        this.subject = subject;
        this.body = body;
    }

    public MailInfo(String toMail, String body) {
        this.subject = "AIMS";
        this.toMail = toMail;
        this.body = body;
    }

    public String getToMail() {
        return toMail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
