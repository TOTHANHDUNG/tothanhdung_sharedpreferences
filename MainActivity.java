package com.example.share_prefernce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnXacNhan;
    EditText edtUserName,edtPassword;
    CheckBox cbRemember;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences  = getSharedPreferences("dataLogin",MODE_PRIVATE);

        //lay gia tri sharedpreferences
        edtUserName.setText(sharedPreferences.getString("taikhoan",""));
        edtPassword.setText(sharedPreferences.getString("matkhau",""));
        cbRemember.setChecked(sharedPreferences.getBoolean("checked",false));
        AnhXa();
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtUserName.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if(username.equals("dung")&&password.equals("123")){
                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    //neu co check
                    if(cbRemember.isChecked()){
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("taikhoan",username);
                        editor.putString("matkhau",password);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("taikhoan");
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }else
                    Toast.makeText(MainActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void AnhXa(){
        btnXacNhan  =(Button) findViewById(R.id.buttonConfirm);
        edtUserName = (EditText) findViewById(R.id.editTextUserName);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);
        cbRemember  = (CheckBox) findViewById(R.id.checkBoxRemember);
    }
}