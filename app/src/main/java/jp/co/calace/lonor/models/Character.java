package jp.co.calace.lonor.models;

import java.util.Date;

public class Character {

    private int id;
    private String name;
    private Date birthday;
    private int avatar;
    private int favor;

    public Character(int id, String name, int avatar, Date birthday, int favor) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.birthday = birthday;
        this.favor = favor;
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

    public void setFavor(int favor) {
        this.favor = favor;
    }

    public int getFavor() {
        return favor;
    }
}
