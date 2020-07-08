package com.dineshpro.daytodayindia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddNewsActivity extends AppCompatActivity {
    AlertDialog alertDialog;
    ImageView opencamera,opengallery,addmyimageview;


    Bitmap bitmap;
    StorageReference mstorage;
    String downloadurl;
    Spinner spinner;
    EditText et_title,et_description;
    ArrayAdapter AD;
    String []cate={"---Select category---","Sport","Education","Business","Political","Technology","International"};
    String sptext;
    String url="http://dtiapp.estudyzone.org/addnews.php";
    RequestQueue requestQueue;
    String title,description,category;
    String imgurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        spinner=(Spinner)findViewById(R.id.spinner);
        et_title=(EditText)findViewById(R.id.et_title);
        et_description=(EditText)findViewById(R.id.et_description);
        addmyimageview=(ImageView)findViewById(R.id.addmyimageview);
        mstorage=FirebaseStorage.getInstance().getReference();
AD=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,cate);
spinner.setAdapter(AD);
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        sptext=AD.getItem(position).toString();
        Toast.makeText(AddNewsActivity.this, sptext+"", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
     }
        public void showDilogBox(View view)
        {
            AlertDialog.Builder dilog=new AlertDialog.Builder(AddNewsActivity.this);
            LayoutInflater inflater=this.getLayoutInflater();
            View dilogview=inflater.inflate(R.layout.customadminlayout,null);
            dilog.setView(dilogview);
            alertDialog=dilog.create();
            alertDialog.show();
            opencamera=alertDialog.findViewById(R.id.camera);
            opengallery=alertDialog.findViewById(R.id.gallery);
            opencamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(MainActivity.this, "hii", Toast.LENGTH_SHORT).s how();
                    Intent takeimage=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takeimage,0);
                }
            });
            opengallery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    Intent picimage=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(picimage,1);
                }
            });
        }

        @Override
         protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==0&&resultCode==RESULT_OK)
        {
            bitmap=(Bitmap) data.getExtras().get("data");
            addmyimageview.setImageBitmap(bitmap);
            alertDialog.dismiss();
            uploadImage();
        }
        else if(requestCode==1&&resultCode==RESULT_OK&&data.getData()!=null)
        {
            Uri uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            addmyimageview.setImageBitmap(bitmap);
            alertDialog.dismiss();
            uploadImage();



        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void uploadImage()
    {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[]finalimage=baos.toByteArray();
        final StorageReference filepath=mstorage.child("News Image").child(finalimage+"jpg");
        final UploadTask uploadTask=filepath.putBytes(finalimage);
        uploadTask.addOnCompleteListener(AddNewsActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
               uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                      filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                          @Override
                          public void onSuccess(Uri uri) {
                           imgurl=String.valueOf(uri);
                              Toast.makeText(AddNewsActivity.this, "Image Upload Successfull", Toast.LENGTH_SHORT).show();
                          }
                      });
                   }
               });
            }
        });
    }
    public void upload(View view) {
        if (et_title.getText().toString().trim().isEmpty()) {
            et_title.setError("Empty");
            et_title.requestFocus();
        } else {
            if (et_description.getText().toString().trim().isEmpty()) {
                et_description.setError("Empty");
                et_description.requestFocus();
            } else
            {
                 title=et_title.getText().toString().trim();
                 description=et_description.getText().toString().trim();
                 final String category=sptext;
                // imgurl=downloadurl;
                final String dt=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                final String tt=new SimpleDateFormat("HH:mm:ss",Locale.getDefault()).format(new Date());
                //Toast.makeText(this, dt+","+tt, Toast.LENGTH_SHORT).show();thre are set time ok
                StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddNewsActivity.this, response+"", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNewsActivity.this, error+"", Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>hm=new HashMap<String,String>();
                    hm.put("title",title);
                    hm.put("description",description);
                    hm.put("dt",dt);
                    hm.put("tt",tt);
                    hm.put("imgurl",imgurl);
                    hm.put("category",sptext+"dinesh");
                        return hm;
                    }
                };
                requestQueue.add(request);
                /*Toast.makeText(this, title+","+desc+","+cate+","+imageurl, Toast.LENGTH_SHORT).show();
                uploadImage();*/

            }
        }
    }
    }

