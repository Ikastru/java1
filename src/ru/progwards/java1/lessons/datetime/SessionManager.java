package ru.progwards.java1.lessons.datetime;

/**
 * Реализовать класс для хранения пользовательских сессий для сервера, который проверяет
 * аутентификацию пользователей. Менеджер работает по следующему принципу: при логине
 * (считаем что проверка логин-пароль уже прошла) данные о сессии пользователя заносятся в
 * список и возвращается хэндл сессии. Затем пользователи запрашивают информацию используя хэндл,
 * авторизация идет по хендлу сессии, который валиден определенное время, с момента крайнего запроса.
 * Проверка сессии по хендлу должна работать максимально быстро. У каждого пользователя может быть
 * только одна сессия.
 *
 * 3.2 Реализовать класс SessionManager, структура данных следующая:
 *
 * private <коллекция> sessions;
 * private int sessionValid;
 *
 * 3.3 реализовать конструктор public SessionManager(int sessionValid) -
 * создает экземпляр SessionManager и сохраняет sessionValid - период валидности сессии в секундах.
 *
 * Реализовать методы:
 *
 * 3.4 public void add(UserSession userSession) - добавляет новую сессию пользователя
 *
 * 3.5 public UserSession find(String userName) - проверяет наличие существующей сессии по userName.
 * Если срок валидности истек, или такой  сессии нет, возвращает null. В противном случае возвращает сессию,
 * обновив ее дату доступа.
 *
 * 3.6 public UserSession get(int sessionHandle) - проверяет наличие существующей сессии по хендлу.
 * Если срок валидности истек, или такой  сессии нет, возвращает null. В противном случае возвращает сессию,
 * обновив ее дату доступа.
 *
 * 3.7 public void delete(int sessionHandle) - удаляет указанную сессию пользователя
 *
 * 3.8 public deleteExpired() - удаляет все сессии с истекшим сроком годности.
 */

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static Map<Integer, UserSession> sessions = new HashMap<>();
    private static Map<String, UserSession> sessionsName = new HashMap<>();
    private static int sessionValid;

    public SessionManager(int sessionValid){
        this.sessionValid = sessionValid;
    }

    public static void add(UserSession userSession){
        sessions.put(userSession.getSessionHandle(), userSession);
        sessionsName.put(userSession.getUserName(), userSession);
    }

    public static boolean contains(HashMap<Integer, UserSession> list, Integer Handle) {
        for (Integer key : list.keySet()) {
            if (list.containsKey(Handle)) {
                return true;
            }
        }
        return false;
    }

    public static UserSession find(String userName){
        UserSession us1 = null;
        Duration dur = null;
        try {
             dur = Duration.between(sessionsName.get(userName).getLastAccess(), Instant.now().atZone(ZoneId.systemDefault()));
        } catch (NullPointerException e) {
            us1 = null;
        }
        try {
            if (sessionsName.containsKey(userName)) {
                if (dur.compareTo(Duration.ofMillis(sessionValid*1000)) == 1) {
                    us1 = null;
                } else {
                    sessionsName.get(userName).newLastAccess();
                    us1 = sessionsName.get(userName);
                }
            } else {
                us1 = null;
            }
        } catch (NullPointerException e){
            us1 = null;
        }
        return us1;
    }

    public static UserSession get(int sessionHandle){
        UserSession us1 = null;
        Duration dur = null;
        try {
            dur = Duration.between(sessions.get(sessionHandle).getLastAccess(), Instant.now().atZone(ZoneId.systemDefault()));
        } catch (NullPointerException e){
            us1 = null;
        }
        if (sessions.containsKey(sessionHandle)){
            if (dur.compareTo(Duration.ofMillis(sessionValid*1000))==1){
                us1 = null;
            } else {
                sessions.get(sessionHandle).newLastAccess();
                us1 = sessions.get(sessionHandle);
            }
        } else {
            us1 = null;
        }
        return us1;
    }

    public static void delete(int sessionHandle){
        sessionsName.remove(sessions.get(sessionHandle).getUserName());
        sessions.remove(sessionHandle);
    }

    public static void deleteExpired(){
        Map<Integer, UserSession> sessionsLoc = Collections.synchronizedMap(new HashMap<>());
        sessionsLoc.putAll(sessions);
        synchronized (sessionsLoc) {
            for (Integer key : sessionsLoc.keySet()) {
                Duration dur = Duration.between(sessions.get(key).getLastAccess(), Instant.now().atZone(ZoneId.systemDefault()));
                if (dur.compareTo(Duration.ofMillis(sessionValid*1000)) == 1) {
                    sessionsName.remove(sessions.get(key).getUserName());
                    sessions.remove(key);
                }
            }
        }
    }

    public static void main(String[] args) {
        if (find("Vasa")==null){
            UserSession uVasa = new UserSession("Vasa");
            System.out.println(uVasa.getSessionHandle());
            SessionManager sessionManager = new SessionManager(60);
            add(uVasa);
        }
        get(sessionsName.get("Vasa").getSessionHandle());
        get(sessionsName.get("Vasa").getSessionHandle());
        get(sessionsName.get("Vasa").getSessionHandle());
        try {
            Thread.sleep(61000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteExpired();
        UserSession uDima = new UserSession("Dima");
        add(uDima);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        UserSession uFeda = new UserSession("Feda");
        add(uFeda);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deleteExpired();
        for (String key : sessionsName.keySet()){
            System.out.println(" " + sessionsName.get(key).getUserName());
        }
        delete(sessionsName.get("Feda").getSessionHandle());
        for (String key : sessionsName.keySet()){
            System.out.println(" " + sessionsName.get(key).getUserName());
        }
    }
}

