package com.example.scorelegue;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class about_me extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account";

    private Toolbar toolbar;
    private CircleImageView imageView;
    private TextView nama, email;
    private GoogleSignInClient googleSignInClient;

    private Button metu;
    private LoginButton loginButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        toolbar = (Toolbar) findViewById(R.id.tl_detail);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.imageview);
        nama = findViewById(R.id.name);
        email = findViewById(R.id.email);
        metu = findViewById(R.id.logout);
         loginButton = (LoginButton) findViewById(R.id.logout1);
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (accessToken != null){
            Glide.with(this)
                    .load(getIntent().getStringExtra("data3"))
                    .into(imageView);
            nama.setText("Name = " + getIntent().getStringExtra("data1"));
            email.setText("Email = " + getIntent().getStringExtra("data2"));
        }else{

        }
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {

            Glide.with(this)
                    .load(googleSignInAccount.getPhotoUrl())
                    .into(imageView);
            nama.setText("Name = " + googleSignInAccount.getDisplayName());
            email.setText("Email = " + googleSignInAccount.getEmail());        } else {
        }







        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.server_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);


        metu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*
          Sign-out is initiated by simply calling the googleSignInClient.signOut API. We add a
          listener which will be invoked once the sign out is the successful
           */

                LoginManager.getInstance().logOut();

                AccessToken.setCurrentAccessToken(null);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //On Succesfull signout we navigate the user back to LoginActivity
                        Intent intent = new Intent(about_me.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });


        this.setTitle("About me");

    }

    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
//    public void onClickFacebookLogout1Button(View view){
//        Intent intent = new Intent(about_me.this, LoginActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                        startActivity(intent);
//                        finish();
//    }

    public void onClickFacebookLogoutButton(View view) {
        if (view == metu) {
            loginButton.performClick();}


//            AccessToken accessToken = AccessToken.getCurrentAccessToken();
//
//            GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
//
//
//                @Override
//                public void onCompleted(JSONObject object, GraphResponse response) {
////
//                    try {
//                        String fbId = object.getString("id");
//                        if (fbId.isEmpty()){
//                            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            //On Succesfull signout we navigate the user back to LoginActivity
//                            Intent intent = new Intent(about_me.this, LoginActivity.class);
//                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                        }
//                    }catch (
//                            JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            });
//
//        }
//
//    }
}}
