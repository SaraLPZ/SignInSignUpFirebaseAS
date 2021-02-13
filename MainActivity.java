package com.example.pantallaregistroylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText etEmailRegister;
    private EditText etPaswRegister;
    private Button btnRegister;

    private EditText etEmailLogin;
    private EditText etPaswLogin;
    private Button btnLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmailRegister=findViewById(R.id.etEmailRegister);
        etPaswRegister=findViewById(R.id.etPaswRegister);
        btnRegister=findViewById(R.id.btnRegister);

        etEmailLogin=findViewById(R.id.etEmailLogin);
        etPaswLogin=findViewById(R.id.etPaswLogin);
        btnLogin=findViewById(R.id.btnLogin);



        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmailRegister.getText().toString();
                String passwd=etPaswRegister.getText().toString();
                crearUsuario(email,passwd);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmailLogin.getText().toString();
                String passwd = etPaswLogin.getText().toString();
                loginUser(email, passwd);
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void loginUser(String email, String passwd) {
        mAuth.signInWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser authCurrentUser = mAuth.getCurrentUser();

                    if (authCurrentUser == null){
                        return;
                    }
                    Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
                    i.putExtra("email", authCurrentUser.getEmail());
                    MainActivity.this.startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void crearUsuario(String email, String passwd) {
        mAuth.createUserWithEmailAndPassword(email, passwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}