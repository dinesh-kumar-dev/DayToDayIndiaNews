package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class CreateUser extends AppCompatActivity {
    TextInputEditText create_userid,create_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        create_userid=(TextInputEditText)findViewById(R.id.create_userid);
         create_password=(TextInputEditText)findViewById(R.id.create_password);
    }
    public void userregister(View view)
    {
        if (create_userid.getText().toString().trim().isEmpty())
        {
            create_userid.setError("Empty");
            create_userid.requestFocus();
        }
        else
        {
            if(create_password.getText().toString().trim().isEmpty())
            {
                create_password.setError("Empty");
                create_password.requestFocus();
            }
            else
            {
                String userid=create_userid.getText().toString().trim();
                String userpassword=create_password.getText().toString().trim();
                Toast.makeText(this, userid+","+userpassword, Toast.LENGTH_SHORT).show();
            }
        }
        }
        public void google(View view)
        {
            Toast.makeText(this, "Hi Iam google login", Toast.LENGTH_SHORT).show();
        }
    public void facebook(View view)
    {
        Toast.makeText(this, "Hi Iam Facebook Login", Toast.LENGTH_SHORT).show();
    }
    }

