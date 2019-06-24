package jp.co.calace.lonor.models;

import java.util.Date;

import jp.co.calace.lonor.models.Character;

public class Message {

    public static final int TYPE_SEND = 0;
    public static final int TYPE_RECV = 1;

    private Character sender;
    private String text;
    private Date time;
    private int type;

    public Message() {
        this.sender = null;
        this.text = "";
        this.time = null;
        this.type = TYPE_SEND;
    }

    public Message(Character sender, String text, Date time, int type) {
        this.sender = sender;
        this.text = text;
        this.time = time;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Character getSender() {
        return sender;
    }

    public void setSender(Character sender) {
        this.sender = sender;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
