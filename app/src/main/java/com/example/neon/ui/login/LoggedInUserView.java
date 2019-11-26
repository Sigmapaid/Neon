package com.example.neon.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;

    public String getToken() {
        return token;
    }

    private String token;
    //... other data fields that may be accessible to the UI

    LoggedInUserView(String displayName, String token) {
        this.displayName = displayName;
        this.token = token;
    }

    String getDisplayName() {
        return displayName;
    }
}
