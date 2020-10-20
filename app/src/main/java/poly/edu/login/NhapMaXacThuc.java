package poly.edu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NhapMaXacThuc extends AppCompatActivity {
    private Button btnXacnhan;
    private  Button btnhuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_ma_xac_thuc);
        btnXacnhan = (Button) findViewById(R.id.btXacnhan) ;

        btnXacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhapMaXacThuc.this, ResetPass.class);
                startActivity(intent);
            }
        });

        btnhuy = (Button) findViewById(R.id.bthuy) ;

        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });


    }
}