package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
    }
    public void addNews(View view)
    {
        Intent intent=new Intent(this,AddNewsActivity.class);
        startActivity(intent);
    }
    public void updateNews(View view)
    {
        Intent i=new Intent(this,DeleteUpdateDashbord.class);

        startActivity(i);
        finish();
    }
    public void adminLogout(View view)
    {
        SharedPreferences sp=getSharedPreferences("admin",MODE_PRIVATE);
        sp.edit().remove("id").commit();
        Intent i=new Intent(this,DashBoard.class);
        startActivity(i);
        finish();
        Toast.makeText(this, "Hi Iam Going to Logout", Toast.LENGTH_SHORT).show();
        Log.e("Logout","User log Out");
    }
}
