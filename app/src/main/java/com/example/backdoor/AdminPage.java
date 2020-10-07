package com.example.backdoor;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.backdoor.SqliteHelperAdmin.DATABASE_NAME_ADMIN;

public class AdminPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        SqliteHelperAdmin sqliteHelperAdmin = new SqliteHelperAdmin(getApplicationContext(), DATABASE_NAME_ADMIN, null, 2);

        Admin admin1 = new Admin("1", "Hamza", "adminhamza@gmail.com", "h123");
        Admin admin2 = new Admin("2", "Ahmad", "adminahmad@gmail.com", "a123");
        Admin admin3 = new Admin("3", "Karam", "adminkaram@gmail.com", "k123");
        Admin admin4 = new Admin("4", "Hamza", "adminkaram@gmail.com", "k123");
        Admin admin5 = new Admin("5", "Ahmad", "adminkaram@gmail.com", "k123");
        Admin admin6 = new Admin("6", "Karam", "adminkaram@gmail.com", "k123");
        Admin admin7 = new Admin("7", "Hamza", "adminkaram@gmail.com", "k123");
        Admin admin8 = new Admin("8", "Ahmad", "adminkaram@gmail.com", "k123");
        Admin admin9 = new Admin("9", "Karam", "adminkaram@gmail.com", "k123");
        Admin admin10 = new Admin("10", "Hamza", "adminkaram@gmail.com", "k123");

        sqliteHelperAdmin.addAdmin(admin1);
        sqliteHelperAdmin.addAdmin(admin2);
        sqliteHelperAdmin.addAdmin(admin3);
        sqliteHelperAdmin.addAdmin(admin4);
        sqliteHelperAdmin.addAdmin(admin5);
        sqliteHelperAdmin.addAdmin(admin6);
        sqliteHelperAdmin.addAdmin(admin7);
        sqliteHelperAdmin.addAdmin(admin8);
        sqliteHelperAdmin.addAdmin(admin9);
        sqliteHelperAdmin.addAdmin(admin10);


        List<Admin> adminList = new ArrayList<>();
        adminList.add(admin1);
        adminList.add(admin2);
        adminList.add(admin3);
        adminList.add(admin4);
        adminList.add(admin5);
        adminList.add(admin6);
        adminList.add(admin7);
        adminList.add(admin8);
        adminList.add(admin9);
        adminList.add(admin10);

        RecyclerView adminRecyclerView = findViewById(R.id.recyclerViewAdmin);
        AdminAdapter adminAdapter = new AdminAdapter(adminList, this);
        adminRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adminRecyclerView.setAdapter(adminAdapter);


    }
}
