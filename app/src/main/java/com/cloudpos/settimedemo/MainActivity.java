package com.cloudpos.settimedemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends ConstantActivity implements OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_activity_main);

        Button btn_run3 = (Button) this.findViewById(R.id.btn_run3);
        Button btn_run4 = (Button) this.findViewById(R.id.btn_run4);
        Button btn_run5 = (Button) this.findViewById(R.id.btn_run5);
        log_text = (TextView) this.findViewById(R.id.text_result);
        log_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        findViewById(R.id.clean_log).setOnClickListener(this);
        btn_run3.setOnClickListener(this);
        btn_run4.setOnClickListener(this);
        btn_run5.setOnClickListener(this);

        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                if (msg.what == ConstantActivity.APPEND_LOG) {
                    log_text.append("" + msg.obj);
                    return;
                } else if (msg.what == ConstantActivity.LOG) {
                    log_text.append("\n" + msg.obj);
                    return;
                } else if (msg.what == ConstantActivity.SUCCESS_LOG) {

                }
            }
        };
    }

    @Override
    public void onClick(View arg0) {
        int index = arg0.getId();
        if (index == R.id.btn_run3) {
            try {
                writerInLog("\nStart SetTime");
                setDateTime(2021, 12, 13, 14, 15, 16);
                writerInLog("\nSetTime Success");
                writerInLog("\n" + getDateTime());

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else if (index == R.id.btn_run4) {
            writerInLog("\nShow current time");

            writerInLog("\n\r\r" + getDateTime());
        } else if (index == R.id.btn_run5) {
            writerInLog("\nandroid.permission.CLOUDPOS_SETTIME");
        } else if (index == R.id.clean_log) {
            log_text.setText("");
        }
    }

    public static void setDateTime(int year, int month, int day, int hour, int minute, int second) throws IOException, InterruptedException {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);
        long when = c.getTimeInMillis();
        if (when / 1000 < Integer.MAX_VALUE) {
            SystemClock.setCurrentTimeMillis(when);
        }
        long now = Calendar.getInstance().getTimeInMillis();
        //Log.d(TAG, "set tm="+when + ", now tm="+now);
        if (now - when > 1000)
            throw new IOException("failed to set Date.");
    }

    public static String getDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//get current time
        String str = formatter.format(curDate);
        return str;
    }

}
