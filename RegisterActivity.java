// Java Android App
// Copyright Carolin
package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email_address, password, repassword;
    Button login, register;
    ButtonHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        DB = new ButtonHelper(this);

        // define input variables
        username = findViewById(R.id.Username);
        email_address = findViewById(R.id.EmailAddress);
        password = findViewById(R.id.Password);
        repassword = findViewById(R.id.RePassword);

        // define button variables
        login = findViewById(R.id.LoginBttn);
        register = findViewById(R.id.RegisterBttn);

        // making new account
        // create listener for the register button
        register.setOnClickListener(view -> {
            // get what the user enters into the box
            String user = username.getText().toString();
            String email = email_address.getText().toString();
            String pass = password.getText().toString();
            String repass = repassword.getText().toString();

            // constrain user inputs (if blank)
            if (user.equals("") || email.equals("") || pass.equals("") || repass.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please fill out entirely", Toast.LENGTH_SHORT).show();

            } else {
                if (pass.equals(repass)) {
                    // check username and email among other usernames and emails in database
                    Boolean checkuser = DB.checkusername(user);
                    Boolean checkemail = DB.checkuseremail(user,email);
                    // see that the username and email are not already in the database
                    if (!checkuser && !checkemail) {
                        Boolean input = DB.insertData(user,email,pass);
                        // insert the username, email, and password into the database
                        if (input) {
                            Toast.makeText(RegisterActivity.this, "Registered Successful", Toast.LENGTH_SHORT).show();
                        // input not true
                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    // the username matched with one in database
                    } else {
                        Toast.makeText(RegisterActivity.this, "Username or email already exists", Toast.LENGTH_SHORT).show();
                    }
                // Password does not match the re entered password
                } else {
                    Toast.makeText(RegisterActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
}