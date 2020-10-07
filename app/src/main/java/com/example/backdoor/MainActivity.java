package com.example.backdoor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.backdoor.SqliteHelperAdmin.DATABASE_NAME_ADMIN;
import static com.example.backdoor.SqliteHelperUser.DATABASE_NAME_USER;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        final SqliteHelperUser sqliteHelperUser = new SqliteHelperUser(getApplicationContext(), DATABASE_NAME_USER, null, 1);
        final SqliteHelperAdmin sqliteHelperAdmin = new SqliteHelperAdmin(getApplicationContext(), DATABASE_NAME_ADMIN, null, 2);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sqliteHelperUser.isEmailExistsUser(email.getText().toString()) && sqliteHelperUser.isPasswordExistsUser(password.getText().toString())) {
                    Intent intent = new Intent(getApplicationContext(), UserPage.class);
                    startActivity(intent);
                }else if (sqliteHelperAdmin.isEmailExistsAdmin(email.getText().toString()) && sqliteHelperAdmin.isPasswordExistsAdmin(password.getText().toString())){
                    Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                    startActivity(intent);
                }else if (email.getText().toString().equalsIgnoreCase("1") && password.getText().toString().equalsIgnoreCase("1")){
                    Intent intent = new Intent(getApplicationContext(), BackDoor.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Email or Password Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

