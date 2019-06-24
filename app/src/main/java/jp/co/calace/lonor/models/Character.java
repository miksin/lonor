package jp.co.calace.lonor.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Character {

    private int id;
    private String name;
    private Date birthday;
    private int avatar;
    private ReplyEngine replyEngine;
    public static final DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Character(int id, String name, int avatar, Date birthday, ReplyEngine replyEngine) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.birthday = birthday;
        this.replyEngine = replyEngine;
    }

    public Character(int id, String name, int avatar, String birthday, ReplyEngine replyEngine) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        try {
            this.birthday = iso8601Format.parse(birthday);
        } catch (Exception ignore) {
            this.birthday = new Date();
        }
        this.replyEngine = replyEngine;
    }

    public List<String> reply(List<String> refMsgList) {
        List<String> replyList = replyEngine.reply(refMsgList, this);
        return replyList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getOld() {
        return new Date().getYear() - birthday.getYear();
    }

    public ReplyEngine getReplyEngine() {
        return replyEngine;
    }

    public void setReplyEngine(ReplyEngine replyEngine) {
        this.replyEngine = replyEngine;
    }
}
