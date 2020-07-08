package com.dineshpro.daytodayindia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.Exampleviewholder> {
    public List<NewsData> mExamplelist;
    public Context mcontext;
    public static class Exampleviewholder extends RecyclerView.ViewHolder
    {
        public ImageView imageView;
        public TextView tv_date,tv_time,tv_description,tv_title;
        public LinearLayout itemclick;
        public Exampleviewholder(View Itemview)
        {
            super(Itemview);
            tv_date=Itemview.findViewById(R.id.l_Date);
            tv_time=Itemview.findViewById(R.id.l_Time);
            tv_description=Itemview.findViewById(R.id.l_Description);
            tv_title=Itemview.findViewById(R.id.l_title);
            imageView=Itemview.findViewById(R.id.l_Image);
            itemclick=Itemview.findViewById(R.id.itemclick);
        }

    }
    public NewsListAdapter(Context context,List<NewsData> examplelist)
    {
        mcontext=context;
        mExamplelist=examplelist;
    }
    @NonNull
    @Override
    public Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_news_layout,parent,false);
        Exampleviewholder exampleviewholder=new Exampleviewholder(view);
        return exampleviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Exampleviewholder holder, int position) {

        final NewsData currentItem=mExamplelist.get(position);
        holder.tv_title.setText(currentItem.getTitle());
        holder.tv_description.setText(currentItem.getDescription());
        holder.tv_date.setText(currentItem.getDate());
        holder.tv_time.setText(currentItem.getTime());
        String url=currentItem.getImgurl();
        //Toast.makeText(mcontext, ""+url, Toast.LENGTH_SHORT).show();
        Picasso.get().load(currentItem.getImgurl()).into(holder.imageView);
        holder.itemclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=mcontext.getSharedPreferences("admin",mcontext.MODE_PRIVATE);
                int delstatus=sharedPreferences.getInt("dd",1);
                if(delstatus==1)
                {
                    Intent i=new Intent(mcontext,NewsDetails.class);
                    i.putExtra("title",currentItem.getTitle());
                    i.putExtra("description",currentItem.getDescription());
                    i.putExtra("date",currentItem.getDate());
                    i.putExtra("time",currentItem.getTime());
                    i.putExtra("imgurl",currentItem.getImgurl());
                    mcontext.startActivity(i);
                }
               else
                {
                    Intent intent=new Intent(mcontext,DeleteAndUpdate.class);
                    intent.putExtra("title",currentItem.getTitle());
                    intent.putExtra("id",currentItem.getId());
                    intent.putExtra("description",currentItem.getDescription());
                    mcontext.startActivity(intent);
                }
            }
        });
    }

    @Override

    public int getItemCount() {
        return mExamplelist.size();
    }



}
