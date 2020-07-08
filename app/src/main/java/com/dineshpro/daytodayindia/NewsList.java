package com.dineshpro.daytodayindia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsList extends AppCompatActivity {
    RecyclerView recyclerView;
    List<NewsData> listdata=new ArrayList<NewsData>();
    String url="http://dtiapp.estudyzone.org/newslist.php";
    RequestQueue requestQueue;
    int i;
    String categoryname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        recyclerView=(RecyclerView)findViewById(R.id.mynews_list);
        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(1, LinearLayout.VERTICAL);
      recyclerView.setLayoutManager(layoutManager);
        categoryname = getIntent().getStringExtra("category");
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        //Toast.makeText(this, "" + categoryname, Toast.LENGTH_SHORT).show();
        DataFetching();

    }

    private void DataFetching() {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("newslist");
                    for( i=0;i<jsonArray.length();i++)
                    {
                        JSONObject obj=jsonArray.getJSONObject(i);
                        //Toast.makeText(NewsList.this, ""+obj.getString("title")+" "+" "+obj.getString("description")+" "+obj.getString("dt")+" "+obj.getString("tt")+" "+obj.getString("imgurl"), Toast.LENGTH_SHORT).show();
                        NewsData data=new NewsData(obj.getString("id"),obj.getString("title"),obj.getString("description"),obj.getString("dt"),obj.getString("tt"),obj.getString("imgurl"));
                        listdata.add(data);
                    }
                    NewsListAdapter adapter=new NewsListAdapter(NewsList.this,listdata);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(NewsList.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(NewsList.this, error+"", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>hm=new HashMap<String,String>();
                hm.put("category",categoryname);
                return hm;
            }
        };
        requestQueue.add(request);

    }
}
