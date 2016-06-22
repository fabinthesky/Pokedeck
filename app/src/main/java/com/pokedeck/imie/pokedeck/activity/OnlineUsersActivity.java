package com.pokedeck.imie.pokedeck.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pokedeck.imie.pokedeck.R;
import com.pokedeck.imie.pokedeck.adapter.UserAdapter;
import com.pokedeck.imie.pokedeck.entity.User;

import java.util.ArrayList;

public class OnlineUsersActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // On verifie si le user est authentifie
        loginCheck();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_users);

        listView = (ListView) findViewById(R.id.user_list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = view.getTag();

                if (o != null) {
                    User user = (User) o;
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<User> onlineUsers = User.getOnlineUsers(getApplicationContext());

        UserAdapter userAdapter = new UserAdapter(this, onlineUsers);
        listView.setAdapter(userAdapter);
    }

    private void loginCheck() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.imie.pokedeck.prefs", Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("mEmail")) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            Log.i("MainPageActivity", "loginCheck - User has already logged in");
        }
    }
}
