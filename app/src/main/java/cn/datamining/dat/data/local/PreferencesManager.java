package cn.datamining.dat.data.local;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {

    public static final String PREFERENCES_NAME = "androidarchitecture";
    private SharedPreferences sharedPreferences;


    public PreferencesManager(Application application) {
        sharedPreferences = application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveLoginInfo(String username, String password) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
    }


    public String getUserName() {
        return sharedPreferences.getString("username", "");
    }

    public String getPassword() {
        return sharedPreferences.getString("password", "");
    }
}
