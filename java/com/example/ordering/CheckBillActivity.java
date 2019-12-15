package com.example.ordering;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lenovo on 2017/12/26.
 */

public class CheckBillActivity extends Activity {
    private Button back;
    private TextView food_amount;
    private TextView food_amount2;
    private TextView food_amount3;
    private TextView food_amount4;
    private TextView food_amount5;
    private TextView total_money;
    private TextView time1;
    private String food;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String total;
    private String time;
    static final String FILENAME = "MyOrder.txt"; // 文件名
    FileOutputStream fOutputStream; // 文件输出流
    FileInputStream finputStream;// 文件输入流

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_bill);

        food_amount=(TextView)findViewById(R.id.food);
        food_amount2=(TextView)findViewById(R.id.food2);
        food_amount3=(TextView)findViewById(R.id.food3);
        food_amount4=(TextView)findViewById(R.id.food4);
        food_amount5=(TextView)findViewById(R.id.food5);
        total_money=(TextView)findViewById(R.id.total);
        time1=(TextView)findViewById(R.id.time);
        back=(Button)findViewById(R.id.back) ;

        Intent intent = getIntent();
        Bundle b=intent.getExtras();
        food=b.getString("food_amount");
        food2=b.getString("food_amount2");
        food3=b.getString("food_amount3");
        food4=b.getString("food_amount4");
        food5=b.getString("food_amount5");
        total=b.getString("total");
        time=b.getString("time");
        food_amount.setText(food);
        food_amount2.setText(food2);
        food_amount3.setText(food3);
        food_amount4.setText(food4);
        food_amount5.setText(food5);
        total_money.setText(total);
        time1.setText(time);
        try {
            finputStream = openFileInput(FILENAME);
            ByteArrayOutputStream bou = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = finputStream.read(buffer)) != -1) {
                bou.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    fOutputStream = openFileOutput(FILENAME, Context.MODE_PRIVATE);

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);//显示订单已提交并返回主界面
                startActivity(intent);

            }
        });

    }


}
