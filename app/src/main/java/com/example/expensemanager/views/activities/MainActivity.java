package com.example.expensemanager.views.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensemanager.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private EditText rEmail;
    private EditText rPass;
    private Button btnLogin;
    private TextView rForgetPassword;
    private TextView rSignupHere;
    private ProgressDialog rdialog;
    private FirebaseAuth rAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rAuth = FirebaseAuth.getInstance();
        if (rAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        rdialog = new ProgressDialog(this);
        loginDetails();
    }

    private void loginDetails() {
        rEmail = findViewById(R.id.Email_login);
        rPass = findViewById(R.id.Password_login);
        btnLogin = findViewById(R.id.button_login);
        rForgetPassword = findViewById(R.id.fp_login);
        rSignupHere = findViewById(R.id.su_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = rEmail.getText().toString().trim();
                String pass = rPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    rEmail.setError("Email Required");
                    return;
                }
                if (TextUtils.isEmpty(pass)) {
                    rPass.setError("Password Required");
                    return;
                }

                rdialog.setMessage("Processing");
                rAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            rdialog.dismiss();
                            Toast.makeText(getApplicationContext(), " Login Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                            finish();
                        } else {
                            rdialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        //Registration Activity
        rSignupHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Registration.class));
            }
        });
        //Reset Activity
        rForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Reset.class));
            }
        });
    }}





