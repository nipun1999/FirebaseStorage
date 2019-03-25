package com.itparkbynipun.firebaseproject;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class details extends RealmObject {
    @Required
    private String title;

    @Required
    private String message;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
