package com.example.baldursguidetogatekeeping.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Spell {
    private Long uid;
    private String name;
    private int level;
    private String type;

    public Spell(Long uid, String name, int level, String type) {
        this.uid = uid;
        this.name = name;
        this.level = level;
        this.type = type;
    }

    public Spell() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
