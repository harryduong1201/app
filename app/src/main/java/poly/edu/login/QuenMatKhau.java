package poly.edu.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class QuenMatKhau extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    private Button btnLogin;
    private EditText Email;
    private Button btquenpass;
    private FirebaseAuth firebaseAuth ;
    CheckBox checkBox ;
    GoogleApiActivity googleApiActivity;
    GoogleApiClient googleApiClient ;
    String sitekey = "6LdVuNIZAAAAAHsZrX7OnyVvXMm3SUuHqDdj_pVm" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);
        btnLogin = (Button) findViewById(R.id.btLogin) ;

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuenMatKhau.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Email = (EditText) findViewById(R.id.tvEmail) ;
        btquenpass = (Button) findViewById(R.id.btquenpass) ;
        firebaseAuth = FirebaseAuth.getInstance();

        btquenpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String useremail = Email.getText().toString().trim();
               if(useremail.equals("")){
                   Toast.makeText (QuenMatKhau.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
               }else {
                   firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText (QuenMatKhau.this, "Mật khẩu được gửi", Toast.LENGTH_SHORT).show();
                               finish();
                               startActivity(new Intent(QuenMatKhau.this , MainActivity.class));
                           }else {
                               Toast.makeText (QuenMatKhau.this, "Ồ Lỗi Cmnr", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
            }
        });



            checkBox = findViewById(R.id.ckcheckBox);
            //them google api client
            googleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(SafetyNet.API)
                    .addConnectionCallbacks(QuenMatKhau.this)
                    .build();
            googleApiClient.connect();

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,sitekey)
                                .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                    @Override
                                    public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                        Status status = recaptchaTokenResult.getStatus();
                                        if((status!= null)&& status.isSuccess()){
                                            Toast.makeText(getApplicationContext()
                                                    ,"Bạn không phải là người máy"
                                                    ,Toast.LENGTH_SHORT).show();
                                            checkBox.setTextColor(Color.GREEN);
                                        }
                                    }
                                });
                    }else{
                        checkBox.setTextColor(Color.BLACK);
                    }
                }
            });

        }





    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}