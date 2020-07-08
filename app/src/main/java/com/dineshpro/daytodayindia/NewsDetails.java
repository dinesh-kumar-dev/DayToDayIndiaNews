package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    ImageView facebookimage,whatsappimage,massengerimage,twiterimage,sendcomment;
    public ImageView newsimage;
    EditText white_comment;
   public TextView newstitle,newsdescription,newsdate,newstime;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        facebookimage=(ImageView)findViewById(R.id.facebookimage);
        massengerimage=(ImageView)findViewById(R.id.massengerimage);
        twiterimage=(ImageView)findViewById(R.id.twiterimage);
        whatsappimage=(ImageView)findViewById(R.id.whatsappimage);
        sendcomment=(ImageView)findViewById(R.id.sendcomment);
        white_comment=(EditText)findViewById(R.id.white_comment);
        newsimage=(ImageView)findViewById(R.id.newsimage);
        newstitle=(TextView)findViewById(R.id.newstitle);
        newsdescription=(TextView)findViewById(R.id.newsdescription);
        newsdate=(TextView)findViewById(R.id.newsdate);
        newstime=(TextView)findViewById(R.id.newstime);
        newstitle.setText(getIntent().getStringExtra("title"));
        newsdescription.setText(getIntent().getStringExtra("description"));
        newsdate.setText(getIntent().getStringExtra("date"));
        newstime.setText(getIntent().getStringExtra("time"));
        Picasso.get().load(getIntent().getStringExtra("imgurl")).into(newsimage);
        sendcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(white_comment.getText().toString().trim().isEmpty())
                {
                    sendcomment.setVisibility(View.INVISIBLE);
                }
                else
                    {
                        sendcomment.setVisibility(View.VISIBLE);
                        String comment=white_comment.getText().toString().trim();
                        Toast.makeText(NewsDetails.this, ""+comment, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(NewsDetails.this,DashBoard.class);
                        startActivity(intent);
                        finish();
                    }
            }
        });
        facebookimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewsDetails.this, "I am Facebook", Toast.LENGTH_SHORT).show();
                Intent faceboook = getPackageManager().getLaunchIntentForPackage("com.facebook.katana");
                if (faceboook != null) {
                    startActivity(faceboook);
                }
                else
                {
                    Toast.makeText(NewsDetails.this, "Massenger is Install not Installed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        massengerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewsDetails.this, "I am Massenger", Toast.LENGTH_SHORT).show();
                Intent massenger=getPackageManager().getLaunchIntentForPackage("com.massenger");
                if(massenger !=null) {
                    startActivity(massenger);
                }
                else
                {
                    Toast.makeText(NewsDetails.this, "Massenger is Install not Installed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    whatsappimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(NewsDetails.this, "Hi Iam Whatsapp", Toast.LENGTH_SHORT).show();
            Intent whatsapp = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
            if (whatsapp != null) {
                startActivity(whatsapp);
            }
            else
            {
                Toast.makeText(NewsDetails.this, "Massenger is Install not Installed!", Toast.LENGTH_SHORT).show();
            }
        }
    });
    twiterimage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(NewsDetails.this, "Hi Iam Twiter", Toast.LENGTH_SHORT).show();
            Intent twitter = getPackageManager().getLaunchIntentForPackage("com.twitter");
            if (twitter != null) {
                startActivity(twitter);
            }
            else
            {
                Toast.makeText(NewsDetails.this, "Massenger is Install not Installed!", Toast.LENGTH_SHORT).show();
            }
        }
    });
    }

}
