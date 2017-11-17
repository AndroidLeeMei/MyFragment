package com.example.auser.myfragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentManager;//因為layout.fragment也是用v4要配合一樣版本
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FragmentManager fm;//選v4的
    FragmentTransaction ft;  //選v4的
    Fragment1 frag1;
    Fragment2 frag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //如果裝在手機上則只呈現一個畫面,若是版則呈現兩畫面
//        fm=getFragmentManager();//方法一?
        fm=getSupportFragmentManager();//方法二,若fragment也是有帶support字樣的,可以向下相容/…
        frag1=(Fragment1)fm.findFragmentById(R.id.fragment1);
        frag2=(Fragment2)fm.findFragmentById(R.id.fragment2);
        decideDevice();
//        showFragment1();
    }

    public void showFragment1(){
        ft=fm.beginTransaction();
        ft.hide(frag2).show(frag1).commit();
    }
    public void showFragment2(){
        ft=fm.beginTransaction();
        ft.hide(frag1).show(frag2).commit();

    }
    void decideDevice(){
        switch (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK){ //& 是位元運算
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                Toast.makeText(this,"目前是平",Toast.LENGTH_SHORT).show();
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                showFragment1();
                Toast.makeText(this,"目前是手機",Toast.LENGTH_SHORT).show();
                break;
        }

    }
//    //一開啟會爆,因為還需要實作監聽器,只要有fragment就要有監聽器,還有其它方
    //實作匿名監聽器,也還是會爆
//    Fragment1.OnFragmentInteractionListener listener1= new Fragment1.OnFragmentInteractionListener() {
//        @Override
//        public void onFragmentInteraction(Uri uri) {
//
//        }
//    };
//    //一開啟會爆,因為還需要實作監聽器
//    Fragment2.OnFragmentInteractionListener listener2= new Fragment2.OnFragmentInteractionListener() {
//        @Override
//        public void onFragmentInteraction(Uri uri) {
//
//        }
//    };
}
