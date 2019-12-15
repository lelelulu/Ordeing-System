package com.example.ordering;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by lenovo on 2017/12/25.
 */

public class OnLoadActivity extends Activity {

    private ProgressBar pb;
    private TextView tv;
    private Button bt;
    Handler handler;
    static final int STOP=0X111;
    static final int CONTINUE=0X112;
    int progress ;
    String food_amount;
    String food_amount2;
    String food_amount3;
    String food_amount4;
    String food_amount5;
    String time;
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onload);
        Intent intent = getIntent(); ;
        Bundle b=intent.getExtras();
        food_amount=b.getString("food_amount");
        food_amount2=b.getString("food_amount2");
        food_amount3=b.getString("food_amount3");
        food_amount4=b.getString("food_amount4");
        food_amount5=b.getString("food_amount5");
        time=b.getString("time");
        total=b.getString("total");
        pb = (ProgressBar)findViewById(R.id.progressBar);
        tv =(TextView)findViewById(R.id.textView2);
        tv.setText("订单提交中,待店家接单，请稍等..");
        bt=(Button)findViewById(R.id.returntofirst);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent();
                Bundle b =new Bundle();
                b.putString("food_amount",food_amount);
                b.putString("food_amount2",food_amount2);
                b.putString("food_amount3",food_amount3);
                b.putString("food_amount4",food_amount4);
                b.putString("food_amount5",food_amount5);
                b.putString("total",total);
                b.putString("time",time);
                intent.setClass(getApplicationContext(),CheckBillActivity.class);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        handler = new Handler()
        {
            public void handleMessage(Message msg)
            {
                switch (msg.what){
                    case STOP:
                        pb.setVisibility(View.GONE);
                        Thread.currentThread().interrupt();
                       // Toast.makeText(getApplicationContext(),"店家已接单！",Toast.LENGTH_LONG).show();
                        bt.setText("查看订单详情");
                        tv.setText("店家已接单！");
                    case CONTINUE:
                        // pb.setProgress(progress);

                }
            }
        };


        Thread thread = new Thread(new Runnable()
        {
            public void run(){
                for(int i=0;i<20;i++)
                {
                    try {
                        progress = (i + 1) * 5;
                        Thread.sleep(1000);
                        if (i == 19) {
                            Message msg = new Message();
                            msg.what = STOP;
                            handler.sendMessage(msg);

                        } else {
                            Message msg = new Message();
                            msg.what = CONTINUE;
                            handler.sendMessage(msg);
                            pb.setProgress(progress);
                        }
                    }catch(Exception e)
                    {}
                }

            }});
        thread.start();


    }
}
