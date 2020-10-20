package poly.edu.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {
    private EditText Name;
    private EditText Password;
    private Button Login;
    private Button QuenMatKhau;
    private Button btdangki;
    private FirebaseAuth firebaseAuth ;
    private ProgressDialog progressDialog;
    private int counter=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        QuenMatKhau = (Button) findViewById(R.id.butnQuenMatKhau) ;
        Name = (EditText) findViewById(R.id.TFEmail);
        Password = (EditText) findViewById(R.id.TFPassword);
        Login = (Button) findViewById(R.id.buttonDangNhap);
        QuenMatKhau = (Button) findViewById(R.id.butnQuenMatKhau);
        btdangki= (Button) findViewById(R.id.btSignup) ;

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString() , Password.getText().toString());


            }
        });


        btdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , Signup.class));
            }
        });

        QuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this , QuenMatKhau.class));
            }
        });
}
        private void validate(String userName , String userPassword){
        progressDialog.setMessage("Wellcome To Danagon");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(userName , userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, "Login SuccessFul", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this , Home.class));
                }else{
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });
        }

}
