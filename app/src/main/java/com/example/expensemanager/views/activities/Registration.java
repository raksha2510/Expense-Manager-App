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

public class Registration extends AppCompatActivity {
    private EditText rEmail;
    private EditText rPass;
    private Button btnReg;
    private TextView rSignin ;
    private ProgressDialog rdialog;
    private FirebaseAuth rAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        rAuth=FirebaseAuth.getInstance();
        rdialog=new ProgressDialog(this);
        registration();
    }
    private void registration(){
        rEmail=findViewById(R.id.Email_reg);
        rPass=findViewById(R.id.Password_reg);
        btnReg=findViewById(R.id.button_reg);
        rSignin=findViewById(R.id.si_reg);

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=rEmail.getText().toString().trim();
                String pass=rPass.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    rEmail.setError("Email Required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    rPass.setError("Password Required");
                }
                rdialog.setMessage("Processing");
                rAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            rdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }else{
                            rdialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        rSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}