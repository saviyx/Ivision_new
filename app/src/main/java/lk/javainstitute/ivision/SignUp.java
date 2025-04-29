package lk.javainstitute.ivision;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.Firebase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView backImg = findViewById(R.id.back_button);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
            }
        });

        TextView txt2 = findViewById(R.id.signInLink);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
            }
        });

        Button btn1 = findViewById(R.id.signUpButton);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                TextInputLayout nameInput = findViewById(R.id.fullNameInput);
                TextInputLayout mobileNumber = findViewById(R.id.MobileInput);
                TextInputLayout emailInput = findViewById(R.id.EmailInput);
                TextInputLayout passwordInput = findViewById(R.id.passwordInput);
                TextInputLayout ConformpasswordInput = findViewById(R.id.confirmPasswordInput);

                String name = nameInput.getEditText().getText().toString().trim();
                String Mnumber = mobileNumber.getEditText().getText().toString().trim();
                String email = emailInput.getEditText().getText().toString().trim();
                String password = passwordInput.getEditText().getText().toString().trim();
                String conformPassword = ConformpasswordInput.getEditText().getText().toString().trim();

                if (password.equals(conformPassword)){

                    Loading loading = new Loading();
                    loading.start(SignUp.this);

                    int code = (int)(Math.random() * 10000);

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                    HashMap<String, Object> user = new HashMap<>();
                    user.put("Name", name);
                    user.put("email", email);
                    user.put("mobileNumber", Mnumber);
                    user.put("password", password);
                 user.put("verified", false);
                 user.put("vCode", Integer.toString(code));

                    firestore.collection("User")
                            .where(
                                    Filter.and(
                                            Filter.equalTo("email", email),
                                            Filter.equalTo("mobileNumber", Mnumber)
                                    )
                            ).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {

                                    if (task.isSuccessful()) {
                                        if (task.getResult().isEmpty()) {

                                            firestore.collection("User").add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentReference> task) {

                                                     //send email to user
                                                boolean status = sendEmail(email, code);
//
                                                if (status) {
                                                    Intent intent = new Intent(SignUp.this, Verification.class);
                                                    intent.putExtra("User_Id", task.getResult().getId());
                                                    loading.stop();
                                                    new Alert().showAlert(SignUp.this,"Success","Registered Successfully" );
                                                    startActivity(intent);

                                                } else {
                                                    loading.stop();
                                                    new Alert().showAlert(SignUp.this,"Apps","Somthing Went Wrong" );

                                                }

//                                                clearFields();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
//                                                loading.Stop();

                                                    // Log Firestore query error
                                                    Log.e("FirestoreError", "Error adding user: ", e);

                                                    // Show alert
                                                 new Alert().showAlert(SignUp.this,"Opps..","Something Went Wrong" );

//                                                clearFields();

                                                }
                                            });

                                        } else {
                                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                                            loading.stop();

//                                        // User already exists, show alert
                                            new Alert().showAlert(SignUp.this,"Opps..","User Email and Mobile Already Exists" );

//
//                                        clearFields();
                                        }
                                    } else {
                                        loading.stop();

                                        // Log Firestore query error
                                        Log.e("FirestoreError", "Error fetching user data: ", task.getException());

                                        // Show alert
//
                                        new Alert().showAlert(SignUp.this,"Opps..","Something Went Wrong" );
//                                    clearFields();
                                    }

                                }
                            });
                }else {
                    new Alert().showAlert(SignUp.this,"Opps..","Your Passwords are not equal" );

                }

//                Intent i = new Intent(SignUp.this,Verification.class);
//                startActivity(i);
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
                    .url("https://3cca-111-223-178-176.ngrok-free.app/Ivision/emailSend")
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