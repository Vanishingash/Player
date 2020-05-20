package com.example.login;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class UserManager extends  SQLiteOpenHelper{
    private static final String TAG = "UserDataManager";
    private static final String DB_NAME = "user_data";
    private static final String TABLE_NAME = "users";
    //public static final String ID = "_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_PWD = "user_pwd";
    private static final int DB_VERSION = 2;
    private Context mContext = null;
    private SQLiteDatabase db = null;

    //创建用户book表

    private static final String DB_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            +  USER_NAME + " varchar,"
            + USER_PWD + " varchar" + ");";

    public UserManager(Context context){
        super(context,DB_NAME,null,1);
        db=getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void createTable(){

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        //db.execSQL(DB_CREATE);
        db.execSQL(DB_CREATE);
    }
    public void insertData(String sql) {

        db.execSQL(sql);
    }

    public int findUserByNameAndPwd(String userName,String pwd){

        int result=0;
        //Cursor mCursor=db.query(TABLE_NAME, null, USER_NAME+"="+userName,
        //        null, null, null, null);
        Cursor mCursor=db.query(TABLE_NAME, null, "user_name=? and user_pwd=?", new String[]{userName,pwd}, null, null, null);
        if(mCursor!=null){
            result=mCursor.getCount();
            mCursor.close();
        }
        return result;
    }


}
