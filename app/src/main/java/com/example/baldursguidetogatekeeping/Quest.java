package com.example.baldursguidetogatekeeping;

import androidx.annotation.NonNull;

import java.util.Date;

public class Quest {
    private String name;
    private String description;
    private Date dateToDo;
    private boolean primary;

    public Quest() {

    }

    public Quest(String name, String description, Date date, boolean primary) {
        this.name = name;
        this.description = description;
        this.dateToDo = date;
        this.setPrimary(primary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateToDo() {
        return dateToDo;
    }

    public void setDateToDo(Date dateToDo) {
        this.dateToDo = dateToDo;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    @NonNull
    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", dateToDo=" + dateToDo +
                ", primary=" + primary +
                '}';
    }
}
