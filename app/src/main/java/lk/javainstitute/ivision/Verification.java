package lk.javainstitute.ivision;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Verification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_verification);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView img2 = findViewById(R.id.backArrow);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btn = findViewById(R.id.continueButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText otp1 = findViewById(R.id.otpBox1);
                EditText otp2 = findViewById(R.id.otpBox2);
                EditText otp3 = findViewById(R.id.otpBox3);
                EditText otp4 = findViewById(R.id.otpBox4);

                String code1=  otp1.getText().toString();
                String code2=  otp2.getText().toString();
                String code3=  otp3.getText().toString();
                String code4=  otp4.getText().toString();

                if (code1.isEmpty()){
                    new Alert().showAlert(Verification.this,"Opps","Please Enter Your OTP." );
                }else if (code2.isEmpty()){
                    new Alert().showAlert(Verification.this,"Opps","Please Enter Your OTP." );
                }else if (code3.isEmpty()){
                    new Alert().showAlert(Verification.this,"Opps","Please Enter Your OTP." );
                }else if (code4.isEmpty()){
                    new Alert().showAlert(Verification.this,"Opps","Please Enter Your OTP." );
                }else {

                    String finalCode = code1 + code2 + code3 + code4;

                    FirebaseFirestore firestore = FirebaseFirestore.getInstance();

                    firestore.collection("User").where(
                            Filter.and(
                                    Filter.equalTo("vCode", finalCode)
                            )
                    ).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                            if (!queryDocumentSnapshots.isEmpty()){

                                String userId = queryDocumentSnapshots.getDocuments().get(0).getId();
                                firestore.collection("User").document(userId).update("verified",true);

                                Intent intent = new Intent(Verification.this , MainActivity.class);
                                startActivity(intent);
                                finish();

                            }else{
                                new Alert().showAlert(Verification.this,"Opps","Invalid Code." );
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            new Alert().showAlert(Verification.this,"Opps","Somthing Went Wrong." );
                        }
                    });

                }


            }
        });

    }
}