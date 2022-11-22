package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity{
    FirebaseAuth fAuth;
    TextView register;
    Button login;
    EditText email,username,password;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        username= findViewById(R.id.username);
        login= findViewById(R.id.login);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
//        if(fAuth.getCurrentUser()!=null)// if there is user already registered
//        {
//            startActivity(new Intent(this,Home.class));
//            finish();
//        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();
                if(mail.isEmpty()){// TextUtils is used to contain the object in the field
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if(pass.isEmpty()){// check the field content ,either its right or not
                    password.setError("password must be greater than 6 character");
                    password.requestFocus();
                    return;
                }
//                progressBar.setVisibility(View.VISIBLE);
                // checking  the email and password in firebase current user
                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if(fAuth.getCurrentUser().isEmailVerified()) {
                                Toast.makeText(Login.this, "successfully login", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Login.this,Home.class));
                                finish();
                            }
                                else{
                                    Toast.makeText(Login.this,"verify email first",Toast.LENGTH_SHORT).show();
                                    email.setText("");
                                    password.setText("");

                                }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"error"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            email.setText("");
                            password.setText("");
                        }
//                        progressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }
}