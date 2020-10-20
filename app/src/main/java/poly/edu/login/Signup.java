package poly.edu.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signup extends AppCompatActivity {
        private EditText name ;
        private EditText email ;
        private EditText sdt;
        private EditText pass;
        private Button regButton;
        private TextView userLogin ;
        private FirebaseAuth firebaseAuth ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
            setUIView();
            firebaseAuth = FirebaseAuth.getInstance();
            regButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(validate()){
                        //data
                        String user_email = email.getText().toString().trim();
                        String user_password = pass.getText().toString().trim();

                        firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Signup.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Signup.this, MainActivity.class));
                                }
                                else {
                                    Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
            userLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Signup.this , MainActivity.class));
                }
            });
        }

        private void setUIView(){
            name = (EditText) findViewById(R.id.TFname);
            email = (EditText) findViewById(R.id.TFEmail);
            sdt = (EditText) findViewById(R.id.TFsdt);
            pass = (EditText) findViewById(R.id.TFpass1);
            regButton = (Button) findViewById(R.id.btDangki);
            userLogin = (TextView) findViewById(R.id.tvUserLogin);
        }

        private boolean validate(){
            Boolean result = false;
            String Name = name.getText().toString();
            String Email = email.getText().toString();
            String SDT = sdt.getText().toString();
            String Password = pass.getText().toString();

            if(Name.isEmpty() || Email.isEmpty() || SDT.isEmpty() || Password.isEmpty() ){
                Toast.makeText(this, "Please enter all the detail", Toast.LENGTH_SHORT).show();
            }else{
                result = true;
            }
            return result;
        }
}