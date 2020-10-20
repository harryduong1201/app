package poly.edu.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManHinhChao extends AppCompatActivity {
    private Button btn_start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        btn_start = (Button) findViewById(R.id.btn_start) ;

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManHinhChao.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}