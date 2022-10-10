package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button login;
    ButtonHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        DB = new ButtonHelper(this);

        // define input variables
        username = findViewById(R.id.Username);
        password = findViewById(R.id.Password);

        // define button variables
        login = findViewById(R.id.LoginBttn);

        // making new account
        // create listener for the register button
        login.setOnClickListener(view -> {
            // get what the user enters into the box
            String user = username.getText().toString();
            String pass = password.getText().toString();

            // constrain user inputs (if blank)
            if (user.equals("") ||  pass.equals("")) {
                Toast.makeText(LoginActivity.this, "Please fill out entirely", Toast.LENGTH_SHORT).show();
            } else {
                Boolean checkuserpass = DB.checkuserpass(user, pass);
                if (!checkuserpass) {
                    Toast.makeText(LoginActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Welcome in Game Master!", Toast.LENGTH_SHORT).show();
                    // go to home page when log in is complete
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
