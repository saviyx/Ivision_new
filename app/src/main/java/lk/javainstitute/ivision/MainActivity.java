package lk.javainstitute.ivision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView txt3 = findViewById(R.id.textView);
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("eee","tttttttttdhfghhhhhhhhhhhhhhhhhhhhhhh");
                Intent i = new Intent(MainActivity.this,Home.class);
                startActivity(i);
            }
        });

        TextView txt2 = findViewById(R.id.tvForgotPassword);
            txt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Loading loading = new Loading();
                    TextInputLayout emailInput = findViewById(R.id.emailInputLayout);
                    TextInputLayout passwordInput = findViewById(R.id.passwordInputLayout);

                    String email = emailInput.getEditText().getText().toString().trim();
                    String password = passwordInput.getEditText().getText().toString().trim();

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                    final int code = (int) (Math.random() * 10000);
                    if (email.trim().isEmpty()) {
                        loading.stop();
                        new Alert().showAlert(MainActivity.this,"Opps..","Please Enter Your Email" );
                    } else if (!new Validations().isEmailValid(email)) {
                        loading.stop();
                        new Alert().showAlert(MainActivity.this,"Opps..","Please Enter Valid Email" );
                    }else {
                        loading.start(MainActivity.this);
                        firestore.collection("User")
                                .where(

                                                Filter.equalTo("email", email)


                                ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        if (task.isSuccessful()) {

                                            if (task.getResult().isEmpty()) {
                                                Log.e("FirestoreError", "no user");


                                                loading.stop();
                                                new Alert().showAlert(MainActivity.this,"Opps..","Invalid User" );
                                            } else {


                                                firestore.collection("User").document(task.getResult().getDocuments().get(0).getId()).get().addOnSuccessListener(documentSnapshot -> {
                                                    if (documentSnapshot.exists()) {
                                                        Log.e("FirestoreError", "Done!");

                                                        loading.stop();

                                                        firestore.collection("User").document(task.getResult().getDocuments().get(0).getId()).update("vCode", Integer.toString(code));

                                                         resetMail(documentSnapshot.getString("email"), code);

                                                        Intent i = new Intent(MainActivity.this,ResetPassword.class);
                                                        startActivity(i);




                                                    } else {
                                                        loading.stop();
                                                        new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
//
                                                    }
                                                });

                                            }

                                        } else {
                                            loading.stop();
                                            // print exeption
                                            Log.e("FirestoreError", "Error fetching user data: " + task.getException());
                                            new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
                                        }

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loading.stop();

                                        // print exeption
                                        Log.e("FirestoreError", "Error fetching user data: " + e);

                                        // show Alert
                                        new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
                                    }
                                });


                    }

                }
            });

        Button button1 = findViewById(R.id.btnSignIn);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Loading loading = new Loading();
                    TextInputLayout emailInput = findViewById(R.id.emailInputLayout);
                    TextInputLayout passwordInput = findViewById(R.id.passwordInputLayout);

                    String email = emailInput.getEditText().getText().toString().trim();
                    String password = passwordInput.getEditText().getText().toString().trim();

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                    final int code = (int) (Math.random() * 10000);


                    loading.start(MainActivity.this);





                    if (email.trim().isEmpty()) {
                        loading.stop();
                         new Alert().showAlert(MainActivity.this,"Opps..","Please Enter Your Email" );
                    } else if (!new Validations().isEmailValid(email)) {
                        loading.stop();
                        new Alert().showAlert(MainActivity.this,"Opps..","Please Enter Valid Email" );
                    } else if (password.trim().isEmpty()) {
                        loading.stop();
                        new Alert().showAlert(MainActivity.this,"Opps..","Please Enter Your Password" );
                    } else {

                        firestore.collection("User")
                                .where(
                                        Filter.and(
                                                Filter.equalTo("email", email),
                                                Filter.equalTo("password", password)
                                        )
                                ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                        if (task.isSuccessful()) {

                                            if (task.getResult().isEmpty()) {
                                                Log.e("FirestoreError", "no user");
                                                Log.e("FirestoreError", email);
                                                Log.e("FirestoreError", password);

                                                loading.stop();
                                                new Alert().showAlert(MainActivity.this,"Opps..","Invalid Mobile Number or Password" );
                                            } else {


                                                firestore.collection("User").document(task.getResult().getDocuments().get(0).getId()).get().addOnSuccessListener(documentSnapshot -> {
                                                    if (documentSnapshot.exists()) {
                                                        Log.e("FirestoreError", "Done!");

                                                        boolean verified = documentSnapshot.getBoolean("verified");

                                                        if (verified) {

//                                                            SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
//                                                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                                                            editor.putString("Logged_userId", task.getResult().getDocuments().get(0).getId());
//                                                            editor.apply();

                                                            loading.stop();
                                                            new Alert().showAlert(MainActivity.this,"Done!","Succesfully logged" );
//                                                            Intent intent = new Intent(SignIn.this, Dashboard.class);
//                                                            startActivity(intent);
//                                                            finish();

                                                        } else {
                                                            loading.stop();


                                                            firestore.collection("User").document(task.getResult().getDocuments().get(0).getId()).update("vCode", Integer.toString(code));

                                                            boolean status = sendEmail(documentSnapshot.getString("email"), code);

                                                            Intent intent = new Intent(MainActivity.this, Verification.class);
                                                            intent.putExtra("User_Id", task.getResult().getDocuments().get(0).getId());


                                                            new Alert().showAlert(MainActivity.this,"Opps..","Please Verify Your Account" );
                                                            startActivity(intent);

                                                        }
                                                    } else {
                                                        loading.stop();
                                                        new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
//
                                                    }
                                                });

                                            }

                                        } else {
                                           loading.stop();
                                            // print exeption
                                            Log.e("FirestoreError", "Error fetching user data: " + task.getException());
                                            new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
                                        }

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        loading.stop();

                                        // print exeption
                                        Log.e("FirestoreError", "Error fetching user data: " + e);

                                        // show Alert
                                        new Alert().showAlert(MainActivity.this,"Opps..","Something Went Wrong" );
                                    }
                                });
                    }

                }
            });




            TextView signupTxt = findViewById(R.id.textView2);
            signupTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this,SignUp.class);
                    startActivity(i);
                }
            });
    }

    private boolean sendEmail(String email, int code) {
        AtomicBoolean status = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1); // Wait until request completes

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();

            String json = "{ \"email\": \"" + email + "\", \"code\": \"" + code + "\" }";
            RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url("https://4aad-175-157-23-35.ngrok-free.app/Ivision/emailSend")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseText = response.body().string();
                    Log.i("Email Request", "Email Response: " + responseText);
                    status.set(true);
                }
            } catch (Exception e) {
                Log.e("Email Request", "Email request failed", e);
            } finally {
                latch.countDown(); // Signal completion
            }
        }).start();

        try {
            latch.await(); // Wait for the request to finish
        } catch (InterruptedException e) {
            Log.e("Email Request", "Thread interrupted", e);
        }

        return status.get();
    }

    private boolean resetMail(String email, int code) {
        AtomicBoolean status = new AtomicBoolean(false);
        CountDownLatch latch = new CountDownLatch(1); // Wait until request completes

        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();

            String json = "{ \"email\": \"" + email + "\", \"code\": \"" + code + "\" }";
            RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

            Request request = new Request.Builder()
                    .url("https://4aad-175-157-23-35.ngrok-free.app/Ivision/resetPawssword")
                    .post(body)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                if (response.body() != null) {
                    String responseText = response.body().string();
                    Log.i("Email Request", "Email Response: " + responseText);
                    status.set(true);
                }
            } catch (Exception e) {
                Log.e("Email Request", "Email request failed", e);
            } finally {
                latch.countDown(); // Signal completion
            }
        }).start();

        try {
            latch.await(); // Wait for the request to finish
        } catch (InterruptedException e) {
            Log.e("Email Request", "Thread interrupted", e);
        }

        return status.get();
    }
}

//
//                }
//            });
//
//            TextView txt1 = findViewById(R.id.textView2);
//            txt1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent i = new Intent(MainActivity.this,SignUp.class);
//                    startActivity(i);
//                }
//            });
//
//
//
//    }
//}