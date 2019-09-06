package com.example.scorelegue;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private List<ModelJadwal> s1 = new ArrayList<>();
    CallbackManager callbackManager;

    private static final String TAG = "AndroidClarified";
    private GoogleSignInClient googleSignInClient;
    private SignInButton googleSignInButton;
    private Button fb;
    private LoginButton loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "com.example.scorelegue",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }

        callbackManager = CallbackManager.Factory.create();
         loginButton = (LoginButton)findViewById(R.id.login_button);
        fb = (Button) findViewById(R.id.fb);


        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
//        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//            @Override
//            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//
//            }
//        };
//        ProfileTracker profileTracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//
//            }
//        };
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AccessToken accessToken = loginResult.getAccessToken();
                        useLoginInformation(accessToken);




                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(getApplicationContext(), "Login Cancel", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });



//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Intent intent = new Intent(getApplicationContext(), about_me.class);
//                startActivity(intent);
//                AccessToken accessToken = loginResult.getAccessToken();
//                Profile profile = Profile.getCurrentProfile();
//                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(),
//                        new GraphRequest.GraphJSONObjectCallback() {
//                            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//
//                            @Override
//
//                            public void onCompleted(JSONObject object, GraphResponse response) {
//                                String userDetil = response.getRawResponse();
//
//                                try {
//                                    JSONObject jsonObject = new JSONObject(userDetil);
////                                    fbId = jsonObject.getString("id");
////                                    realName = jsonObject.optString("name", "");
////                                    email = jsonObject.optString("email", "");
////                                    avatar = "https://graph.facebook.com/" + fbId + "/picture?type=large";
////
////                                    Log.d("gambar", "onCompleted: "+avatar);
////                                    Log.d("gambar", "oncompleted" + realName);
////                                    Log.d("gambar", "oncompleted" + email);
////                                    Log.d("gambar", "oncompleted" + fbId);
//
//
//
//                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
////                                    intent.putExtra("data1", realName);
////                                    intent.putExtra("data2", email);
////                                    intent.putExtra("data3", fbId);
////                                    intent.putExtra("data4", avatar);
//                                    startActivity(intent);
//                                    finish();
//
//                                } catch(JSONException ex) {
//                                    ex.printStackTrace();
//                                }
//                            }
//                        });
//                Bundle parameters = new Bundle();
//                parameters.putString("fields", "id,name");
//                request.setParameters(parameters);
//                request.executeAsync();
//            }
//
//            @Override
//            public void onCancel() {
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//            }
//        });

        googleSignInButton = findViewById(R.id.googleSignInButton);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.server_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case 101:
                    try {
                        // The Task returned from this call is always completed, no need to attach
                        // a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String idToken = account.getIdToken();

                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                    }
                    break;
            }
    }
    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.GOOGLE_ACCOUNT, googleSignInAccount);


        startActivity(intent);
        finish();
    }
    public void onStart() {
        super.onStart();

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        if (accessToken != null){
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            useLoginInformation(accessToken);

        }else{
            Log.d(TAG, "Not logged in");

        }

        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            Toast.makeText(this, "Already Logged In", Toast.LENGTH_SHORT).show();
            onLoggedIn(alreadyloggedAccount);
        } else {
            Log.d(TAG, "Not logged in");
        }
    }
    private void useLoginInformation(AccessToken accessToken) {
        /**
         Creating the GraphRequest to fetch user details
         1st Param - AccessToken
         2nd Param - Callback (which will be invoked once the request is successful)
         **/

        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String fbId = object.getString("id");
                    String name = object.optString("name", "");
                    String email = object.optString("email", "");
                    String image = "https://graph.facebook.com/" + fbId + "/picture?type=large";

                    System.out.println(name + " huha ");

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("data1", name);
                    intent.putExtra("data2", email);
                    intent.putExtra("data3", image);
                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        // We set parameters to the GraphRequest using a Bundle.
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(parameters);
        // Initiate the GraphRequest
        request.executeAsync();
    }
    public void onClickFacebookButton(View view) {
        if (view == fb) {
            loginButton.performClick();
        }
    }
}

