package com.dineshpro.daytodayindia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DashBoard<mGoogleSignInClient, gso> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
GoogleSignInClient mGoogleSignInClient;
  DrawerLayout drawerLayout;
    Button btnSport,btnEducation,btnBusiness,btnPolitical,btnInternational,btnTechnology;
    TextView tv_username,tv_useremailid, user_signin;
    ImageView user_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        btnSport=(Button)findViewById(R.id.btnSport);
        btnEducation=(Button)findViewById(R.id.btnEducation);
        btnInternational=(Button)findViewById(R.id.btnInternational);
        btnBusiness=(Button)findViewById(R.id.btnBusiness);
        btnPolitical=(Button)findViewById(R.id.btnPolitical);
        btnTechnology=(Button)findViewById(R.id.btnTechnoloy);

        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","Sport"+"dinesh");

                startActivity(intent);
            }
        });


    btnEducation.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","Education"+"dinesh");

            startActivity(intent);
        }
    });

    btnBusiness.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","Business"+"dinesh");

            startActivity(intent);
        }
    });

    btnPolitical.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","Political"+"dinesh");

            startActivity(intent);
        }
    });
    btnTechnology.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","Technology"+"dinesh");

            startActivity(intent);
        }
    });
    btnInternational.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(DashBoard.this,NewsList.class);
            intent.putExtra("category","International"+"dinesh");

            startActivity(intent);
        }
    });

        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(DashBoard.this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        toggle.syncState();
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(DashBoard.this);

        View view=navigationView.getHeaderView(0);
        user_imageView=view.findViewById(R.id.user_imageView);
        tv_username=view.findViewById(R.id.tv_username);
        tv_useremailid=view.findViewById(R.id.user_emailid);
        user_signin=view.findViewById(R.id.user_signin);
        ///Code for google authentication
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient=GoogleSignIn.getClient(this, gso);

        user_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(this, "Google Login", Toast.LENGTH_SHORT).show();
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 1);

                }
        });
        user_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(DashBoard.this);
                if(account==null)
                {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 1);
                }
                else
                {
                    final ProgressDialog pd=new ProgressDialog(DashBoard.this);
                    pd.setMessage("Sign out...");
                    pd.show();
                    pd.setCanceledOnTouchOutside(false);
                    mGoogleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                pd.dismiss();
                                tv_username.setText("username");
                                tv_useremailid.setText("email@gmail.com");
                                user_signin.setText("SIGN IN");
                            }
                        }
                    });
                }

            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }

    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.

            GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
           // Toast.makeText(this, ""+ acc.getDisplayName()+acc, Toast.LENGTH_SHORT).show();
            tv_username.setText(acc.getDisplayName());
            tv_useremailid.setText(acc.getEmail());
            user_signin.setText(Html.fromHtml("<u>LOGOUT</u>"));
            String img=String.valueOf(acc.getPhotoUrl());
            if(img==null||img.isEmpty())
            {
                img="dinesh";
            }
            else {
                Picasso.get().load(acc.getPhotoUrl()).into(user_imageView);
            }


        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            //Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id=menuItem.getItemId();
        if(id==R.id.nav_home)
        {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onBackPressed() {
       if(drawerLayout.isDrawerOpen(GravityCompat.START))
       {
           drawerLayout.closeDrawer(GravityCompat.START);
       }
       else
       {
           super.onBackPressed();
       }
    }

}
