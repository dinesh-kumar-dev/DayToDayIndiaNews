package com.dineshpro.daytodayindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText et_emailaddress,et_password;
    Button btnLogin,forgotten_password,btnRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_emailaddress=(EditText)findViewById(R.id.et_emailaddress);
        et_password=(EditText)findViewById(R.id.et_password);
        auth=FirebaseAuth.getInstance();
        SharedPreferences sp=getSharedPreferences("admin",MODE_PRIVATE);
        int status=sp.getInt("id",0);
        if(status==1)
        {
            Intent i=new Intent(LoginActivity.this,AdminLoginActivity.class);
            startActivity(i);
            finish();
        }
    }
    public void login(View view)
    {
        if(et_emailaddress.getText().toString().trim().isEmpty())
        {
            et_emailaddress.setError("Empty");
            et_emailaddress.requestFocus();
        }
        else
        {
            if(et_password.getText().toString().trim().isEmpty())
            {
                et_password.setError("Empty");
                et_password.requestFocus();

            }
            else
            {
                String email=et_emailaddress.getText().toString().trim();
                String password=et_password.getText().toString().trim();
                auth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               if(task.isSuccessful())
                               {
                                   Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                                   Log.e("Error","Hai errore");
                                   SharedPreferences sp=getSharedPreferences("admin",MODE_PRIVATE);
                                   SharedPreferences.Editor Ed=sp.edit();
                                   Ed.putInt("id",1);
                                   Ed.commit();


                                   Intent intent=new Intent(LoginActivity.this,AdminLoginActivity.class);
                                   startActivity(intent);
                                   finish();
                               }
                               else
                               {
                                   Toast.makeText(LoginActivity.this, "Invalid Admin", Toast.LENGTH_SHORT).show();
                               }
                            }
                        });

            }
        }

    }
}
