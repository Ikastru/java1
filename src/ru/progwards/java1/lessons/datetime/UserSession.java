package ru.progwards.java1.lessons.datetime;

/**
 * 3.1 Реализовать класс UserSession, структура данных следующая:
 * private int sessionHandle;
 * private String userName;
 * private <дата-время> lastAccess;
 *
 * Все свойства приватные, сделать для них методы-геттеры (getSessionHandle(), getUserName(),
 * getLastAccess()) соответствующих типов,
 *
 * метод updateLastAccess() -  обновляет время доступа к сессии,
 *
 * а также конструктор
 *
 * public UserSession(String userName) - создать сессию пользователя. Внутри автоматически сгенерировать
 * sessionHanle, для примера использовать просто случайное число через класс Random,
 * а так же установить текущее время доступа.
 */

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Random;

public class UserSession {
    private int sessionHandle;
    private String userName;
    private ZonedDateTime lastAccess;

    public int getSessionHandle() {
        return sessionHandle;
    }

    public String getUserName() {
        return userName;
    }

    public ZonedDateTime getLastAccess() {
        return lastAccess;
    }

    public void newLastAccess() {
        this.lastAccess = Instant.now().atZone(ZoneId.systemDefault());
    }

    public void setLastAccess(ZonedDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public void updateLastAccess(){
       this.lastAccess = Instant.now().atZone(ZoneId.systemDefault());
    }

    public UserSession(String userName){
        this.userName = userName;
        Random random = new Random();
        this.sessionHandle = random.nextInt(1000);
        this.lastAccess = Instant.now().atZone(ZoneId.systemDefault());
    }
}
