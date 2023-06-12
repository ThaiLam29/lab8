package com.thailam.lab8;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class dangky extends AppCompatActivity {
    public static String KEY_USERNAME = "username";
    public static String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ky);


        EditText edtname_signup = findViewById(R.id.edtname_Signi);
        EditText edtpass_singup = findViewById(R.id.edtpass_signin);
        EditText edtnhaplai = findViewById(R.id.edtnhaplai_signin);
        Button btndangky = findViewById(R.id.btndangky_signup);


        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtname_signup.getText().toString();
                String password = edtpass_singup.getText().toString();
                String nhaplai = edtnhaplai.getText().toString();
                if (username.trim().isEmpty()) {
                    Toast.makeText(dangky.this, "Vui long nhap username", Toast.LENGTH_SHORT).show();
                } else if (password.trim().isEmpty()) {
                    Toast.makeText(dangky.this, "Vui long nhap password", Toast.LENGTH_SHORT).show();
                } else if (nhaplai.trim().isEmpty()) {
                    Toast.makeText(dangky.this, "Vui long nhap lai password", Toast.LENGTH_SHORT).show();
                } else if (!nhaplai.equals(password)) {
                    Toast.makeText(dangky.this, "Password nhap lai khong khop", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(dangky.this, login.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(KEY_USERNAME, username);
                    bundle.putString(KEY_PASSWORD, password);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        });
    }
    public void writeUser (Context context, String fileName, login.User user) { List<login.User> list = new ArrayList<>();
        try {
            FileOutputStream fileOutputStream = context.openFileOutput (fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            list.add(user);
            objectOutputStream.writeObject(list);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<login.User> readUser (Context context, String fileName) {
        List<login.User> objectList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectList = (List<login.User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace();
        }
        return objectList;
    }
}
