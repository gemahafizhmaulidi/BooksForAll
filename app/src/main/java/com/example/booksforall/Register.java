package com.example.booksforall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword;
    private Button btnRegister;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://booksforall-dc4e6-default-rtdb.asia-southeast1.firebasedatabase.app/");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = etUsername.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Data tidak lengkap", Toast.LENGTH_SHORT).show();
                }else{
                    database = FirebaseDatabase.getInstance().getReference("users");
                    database.child(username).child("username").setValue(username);
                    database.child(username).child("email").setValue(email);
                    database.child(username).child("password").setValue(password);

                    Toast.makeText(getApplicationContext(),"Register Berhasil", Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(),Login.class);
                    startActivity(register);
                }
            }
        });
    }
}