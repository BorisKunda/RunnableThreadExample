package com.happytrees.myhandler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //I put edit-text field so I can monitor if UI thread is responsive

    int count =0;
    TextView numbers;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


   numbers = (TextView)findViewById(R.id.numbersTV);


//OPEN NEW THREAD THROUGH RUNNABLE INTERFACE
   new Thread(new Runnable() {
       @Override
       public void run() {
           for  (int i =0 ;i<100;i++) {
               count = count + 1;
               Log.e("COUNTER",count + " ");

               //post to UI (main thread) through Main Thread's Handler post method .without post method there will be CalledFromWrongThreadException
             numbers.post(new Runnable() {
                  @Override
                  public void run() {
                       numbers.setText(count + " ");
                   }
              });

               //will stop this thread  every one second (1000 millis)
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
       }
   }).start();








    }
}
