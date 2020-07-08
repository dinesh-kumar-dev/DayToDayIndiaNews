package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class DeleteAndUpdate extends AppCompatActivity {
    EditText da_title,da_description;
    String id;
    String updateurl="http://dtiapp.estudyzone.org/updatenews.php";
    String deleteurl="http://dtiapp.estudyzone.org/deletenews.php";
    RequestQueue requestQueue;
    String description,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_and_update);
        da_title=(EditText)findViewById(R.id.edit_title);
        da_description=(EditText)findViewById(R.id.edit_description);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        da_title.setText(intent.getStringExtra("title"));
        da_description.setText(intent.getStringExtra("description"));

    }
    public void deletepost(View view)
    {
    StringRequest  request=new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Toast.makeText(DeleteAndUpdate.this, ""+response, Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(DeleteAndUpdate.this,DeleteUpdateDashbord.class);
            startActivity(intent);
            finish();
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(DeleteAndUpdate.this, ""+error, Toast.LENGTH_SHORT).show();
        }
    })
    {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String>hm=new HashMap<String,String>();
           hm.put("id",id);
            return hm;
        }
    };
    requestQueue.add(request);
    }





    public void updatepost(View view)
    {
        if(da_title.getText().toString().trim().isEmpty())
        {
           da_title.setError("Empty");
           da_title.requestFocus();
        }
        else
        {
            if(da_description.getText().toString().trim().isEmpty())
            {
              da_description.setError("Empty");
              da_description.requestFocus();
            }
            else
            {
                title=da_title.getText().toString().trim();
                description=da_description.getText().toString().trim();
                StringRequest request=new StringRequest(Request.Method.POST, updateurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(DeleteAndUpdate.this, ""+response, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(DeleteAndUpdate.this,DeleteUpdateDashbord.class);
                        startActivity(intent);
                        finish();
                    }
                },
               new Response.ErrorListener()
               {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DeleteAndUpdate.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String>map=new HashMap<String,String>();
                        map.put("id",id);
                        map.put("title",title);
                        map.put("description",description);
                        return map;
                    }
                };
                requestQueue.add(request);
            }
        }
    }
}
