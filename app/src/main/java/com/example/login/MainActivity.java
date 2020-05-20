package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView3;
    private EditText editText;
    private  EditText editText2;
    private UserManager mUserManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView3=(TextView) findViewById(R.id.textView3);
        editText=(EditText) findViewById(R.id.editText);
        editText2=(EditText) findViewById(R.id.editText2);
    }

    public void click(View v){
        mUserManager = new UserManager(this);  //实例化
        SQLiteDatabase db=null;
        mUserManager.onCreate(db);  //创建库
        mUserManager.createTable();
        mUserManager.onOpen(db);
        mUserManager.insertData("Insert into users(user_name,user_pwd) values('admin','123456')");
        String username = editText.getText().toString().trim();
        String password = editText2.getText().toString().trim();

        int result=mUserManager.findUserByNameAndPwd(username,password);

        if(result==1) {
            Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
            mUserManager.close();
            mUserManager = null;
            Intent intent = new Intent(MainActivity.this,Player.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
            mUserManager.close();
            mUserManager = null;
        }
    }
}
