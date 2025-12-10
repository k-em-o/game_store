// LoggedInUser.java
package com.game_store.services;

import com.game_store.models.User;

// كلاس لتخزين بيانات المستخدم بعد تسجيل الدخول
public class LoggedInUser {

    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}
