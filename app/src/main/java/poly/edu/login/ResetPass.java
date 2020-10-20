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
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class ResetPass extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    private Button btnXacnhanpassmoi;
    CheckBox checkBox ;
    GoogleApiActivity googleApiActivity;
    GoogleApiClient googleApiClient ;
    String sitekey = "6LdVuNIZAAAAAHsZrX7OnyVvXMm3SUuHqDdj_pVm" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        btnXacnhanpassmoi = (Button) findViewById(R.id.btnXacnhanpassmoi) ;

        btnXacnhanpassmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPass.this, MainActivity.class);
                startActivity(intent);
            }
        });


        checkBox = findViewById(R.id.ckcheckBox);
        //them google api client
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(ResetPass.this)
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