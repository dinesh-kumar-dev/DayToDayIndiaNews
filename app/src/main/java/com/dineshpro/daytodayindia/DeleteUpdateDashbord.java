package com.dineshpro.daytodayindia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.navigation.NavigationView;

public class DeleteUpdateDashbord extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button btnSport,btnEducation,btnBusiness,btnPolitical,btnInternational,btnTechnology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_update_dashbord);
        SharedPreferences sp=getSharedPreferences("admin",MODE_PRIVATE);
        SharedPreferences.Editor Ed=sp.edit();
        Ed.putInt("dd",1);
        Ed.commit();
        btnSport=(Button)findViewById(R.id.btnSport);
        btnEducation=(Button)findViewById(R.id.btnEducation);
        btnInternational=(Button)findViewById(R.id.btnInternational);
        btnBusiness=(Button)findViewById(R.id.btnBusiness);
        btnPolitical=(Button)findViewById(R.id.btnPolitical);
        btnTechnology=(Button)findViewById(R.id.btnTechnoloy);
        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(DashBoard.this, "Sport News", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DeleteUpdateDashbord.this, NewsList.class);
                intent.putExtra("category","Sport"+"dinesh");
                startActivity(intent);
                finish();
            }
        });
//        btnEducation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Toast.makeText(DashBoard.this, "Educational News", Toast.LENGTH_SHORT).show();
//                Intent i=new Intent(DashBoard.this,NewsList.class);
//
//            }
//        });
//        btnTechnology.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DashBoard.this, "Technology News", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnBusiness.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DashBoard.this, "Business News", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnInternational.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DashBoard.this, "International News", Toast.LENGTH_SHORT).show();
//            }
//        });
//        btnPolitical.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DashBoard.this, "Political News", Toast.LENGTH_SHORT).show();
//            }
//        });


}
    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences=getSharedPreferences("admin",MODE_PRIVATE);
        sharedPreferences.edit().remove("dd").commit();
        super.onBackPressed();

    }
}
