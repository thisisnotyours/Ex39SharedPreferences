package com.suek.ex39sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etAge;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName= findViewById(R.id.et_name);
        etAge= findViewById(R.id.et_age);
        tv= findViewById(R.id.tv_result);
    }




    public void clickSave(View view) {

        //저장할 데이터를 EditText 에게 얻어오기
        String name= etName.getText().toString();
        String ageStr= etAge.getText().toString();  //EditText 는 문자열만되니..
        int age;
        try{
            age= Integer.parseInt(ageStr);
        }catch (Exception e){
            age= 0;
        }


        //내부/내장 메모리에 Data.xml 이라는 문서에 데이터를 저장해주는 객체 '소환'(객체 생성이 아니라 소환인 것은 이미 운영체제에 내장되어있는 기능이라서)
        SharedPreferences pref= getSharedPreferences("Data", MODE_PRIVATE);  //.xml 는 이미 저장되어있어 Data.xml 라고 안써도됨
                                                                                   //MODE_PRIVATE : ex)프로필사진은 private 이어야하니까-> 바꿔주는 느낌
        SharedPreferences.Editor editor= pref.edit();    //문서저장을 시작합니다..

        editor.putString("Name", name);  //식별자, Value 값
        editor.putInt("Age", age);


        editor.commit();   //작업완료





    }




    //불려들어오기
    public void clickLoad(View view) {
        SharedPreferences pref= getSharedPreferences("Data", MODE_PRIVATE);

        String name= pref.getString("Name", "");  //default 아무것도 저장하지 않았을때
        int age= pref.getInt("Age", 0); //default 아무것도 저장하지 않았을때

        tv.setText(name +"\n"+ age);
    }
}
