package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    FirebaseAuth  fAuth;
    FirebaseDatabase fd;
    TextView create;
    EditText email,username,password;
    Button login;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        username= findViewById(R.id.username);
        login  = findViewById(R.id.login);
        create = findViewById(R.id.create);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        fAuth = FirebaseAuth.getInstance();// initialize the firebase authentication
        if(fAuth.getCurrentUser()!=null)// if the user is already logged in then finish
        {
            startActivity(new Intent(this,Home.class));
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname= username.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String pass  = password.getText().toString().trim();
                if(mail.isEmpty()){// TextUtils is used to contain the object in the field
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                    email.setError("Invalid email address");
                    email.requestFocus();
                    return;
                }
                if(pass.isEmpty()){// check the field content ,either its right or not
                    password.setError("password required");
                    password.requestFocus();
                    return;
                }
                if(pass.length()<6){
                    password.setError("password must be greater than 6");
                    password.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                // storing the email and password in firebase
                fAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            User user = new User(uname,mail,pass);
//                            FirebaseDatabase.getInstance().getReference("user")
//                                    .child(FirebaseAuth.getInstance().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
                            fAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Register.this, "registered succesfully , please  verify your email", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Register.this, Login.class));
                                   finish();
                                }
                            });
                        }
                            else{
                                Toast.makeText(Register.this,"wrong",Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);

            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}