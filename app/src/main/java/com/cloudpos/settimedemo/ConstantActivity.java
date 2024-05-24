package com.cloudpos.settimedemo;


import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ConstantActivity extends Activity {
    public static final int APPEND_LOG = 0;
    public static final int LOG = 1;
    public static final int SUCCESS_LOG = 2;
    public static final int FAILED_LOG = 3;

    protected TextView log_text;
    protected Handler mHandler = null;

    public void writerInLogAppend(String obj) {
        Message msg = new Message();
        msg.arg1 = APPEND_LOG;
        msg.obj = obj;
        mHandler.sendMessage(msg);
    }

    public void writerInLog(String obj) {
        Message msg = new Message();
        msg.arg1 = APPEND_LOG;
        msg.obj = obj;
        mHandler.sendMessage(msg);
    }

}
