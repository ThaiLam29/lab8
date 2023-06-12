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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class login extends AppCompatActivity {

    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText edtname = findViewById(R.id.username);
        EditText edtpass = findViewById(R.id.password);
        Button btndangnhap = findViewById(R.id.dangnhap);
        Button btndangky = findViewById(R.id.dangky);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString(dangky.KEY_USERNAME);
            password = bundle.getString(dangky.KEY_PASSWORD);
        }
        edtname.setText(username);
        edtpass.setText(password);

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, dangky.class));
            }
        });
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtname.getText().toString();
                String pass = edtpass.getText().toString();

                if (name.trim().isEmpty()) {
                    Toast.makeText(login.this, "Chua nhap tai khoan", Toast.LENGTH_SHORT).show();
                } else if (pass.trim().isEmpty()) {
                    Toast.makeText(login.this, "Chua nhap mat khau", Toast.LENGTH_SHORT).show();
                } else if (!name.equals(username) && !pass.equals(password)) {
                    Toast.makeText(login.this, "Sai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(login.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    public class User implements Serializable{
        private  String username, password;
        public  User (String username, String password){
            this.username= username;
            this.password =password;

        }
        public String getUsername() {
            return username;

        }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    public void writeUser (Context context, String fileName, User user) { List<User> list = new ArrayList<>();
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
    public List<User> readUser (Context context, String fileName) {
        List<User> objectList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectList = (List<User>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) { e.printStackTrace();
        }
        return objectList;
    }
}
