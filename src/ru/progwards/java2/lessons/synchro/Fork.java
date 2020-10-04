package ru.progwards.java2.lessons.synchro;

public class Fork {
    private boolean availability = true;
    private int id;

    public Fork(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean getAvailability(){
        return availability;
    }

    public void setAvailability(boolean flag){
        availability = flag;
    }
}
