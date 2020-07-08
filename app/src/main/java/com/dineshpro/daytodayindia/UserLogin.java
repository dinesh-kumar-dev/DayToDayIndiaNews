package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class UserLogin extends AppCompatActivity {
   TextInputEditText et_userid,et_userpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        et_userid=(TextInputEditText)findViewById(R.id.et_userid);
        et_userpassword=(TextInputEditText)findViewById(R.id.et_userpassword);
    }
    public void userlogin(View view)
    {
        if(et_userid.getText().toString().trim().isEmpty())
        {
            et_userid.requestFocus();
            et_userid.setError("Empty");
        }
        else
        {
            if(et_userpassword.getText().toString().trim().isEmpty())
            {
                et_userpassword.requestFocus();
                et_userpassword.setError("Empty");
            }
            else
            {
                Toast.makeText(this, "User login code successfull", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void createuser(View view)
    {
        Intent I=new Intent(this,CreateUser.class);
        startActivity(I);
    }
}
