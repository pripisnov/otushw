package ru.otus.dbapi.model;

import ru.otus.framework.annotation.Column;
import ru.otus.framework.annotation.Id;

public class Account {
    @Id
    private long no;
    @Column(name = "type")
    private String type;
    @Column(name = "rest")
    private int rest;

    public Account() {
    }

    public Account(String type, int rest) {
        this.type = type;
        this.rest = rest;
    }

    public Account(long no, String type, int rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRest() {
        return rest;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{no=" + no + ", type=" + type + ", rest=" + rest + "}";
    }
}
