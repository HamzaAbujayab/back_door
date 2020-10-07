package com.example.backdoor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.backdoor.SqliteHelperUser.DATABASE_NAME_USER;

public class UserPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        SqliteHelperUser sqliteHelperUser = new SqliteHelperUser(getApplicationContext(), DATABASE_NAME_USER, null, 1);

        //String shaHex= new String(Hex.encodeHex(DigestUtils.sha("textToHash")));

        User user1 = new User("1", "Hamza", "userhamza@gmail.com", "h123");
        User user2 = new User("2", "Ahmad", "userahmad@gmail.com", "a123");
        User user3 = new User("3", "Karam", "userkaram@gmail.com", "k123");
        User user4 = new User("4", "Hamza", "userhamza@gmail.com", "h123");
        User user5 = new User("5", "Ahmad", "userahmad@gmail.com", "a123");
        User user6 = new User("6", "Karam", "userkaram@gmail.com", "k123");
        User user7 = new User("7", "Hamza", "userhamza@gmail.com", "h123");
        User user8 = new User("8", "Ahmad", "userahmad@gmail.com", "a123");
        User user9 = new User("9", "Karam", "userkaram@gmail.com", "k123");
        User user10 = new User("10", "Hamza", "userhamza@gmail.com", "h123");

        sqliteHelperUser.addUser(user1);
        sqliteHelperUser.addUser(user2);
        sqliteHelperUser.addUser(user3);
        sqliteHelperUser.addUser(user4);
        sqliteHelperUser.addUser(user5);
        sqliteHelperUser.addUser(user6);
        sqliteHelperUser.addUser(user7);
        sqliteHelperUser.addUser(user8);
        sqliteHelperUser.addUser(user9);
        sqliteHelperUser.addUser(user10);

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);
        userList.add(user7);
        userList.add(user8);
        userList.add(user9);
        userList.add(user10);

        RecyclerView userRecyclerView = findViewById(R.id.recyclerViewUser);
        UserAdapter userAdapter = new UserAdapter(userList, this);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        userRecyclerView.setAdapter(userAdapter);



    }
}
