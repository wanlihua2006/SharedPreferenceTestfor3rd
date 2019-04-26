package com.qiku.sharedpreferencetest;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView resulte;
    private Button save;
    private Button get;
    private EditText input;
    private Boolean clicked = false;
    private String inputData = null;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());
        resulte = (TextView)findViewById(R.id.result);
        save = (Button)findViewById(R.id.save);
        get = (Button)findViewById(R.id.get);
        input = (EditText)findViewById(R.id.input);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputData = input.getText().toString() ;
                clicked = true;
                if(inputData!=null)
                    savedata(inputData);
            }
        });

        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputData!=null)
                    resulte.setText(getdata());
            }
        });
    }

    private void savedata(String s){
        SharedPreferences sharedPreferences = getSharedPreferences("lihua", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("data",s);
        editor.commit();
    }

    private String getdata(){
        SharedPreferences sharedPreferences = getSharedPreferences("lihua", Context.MODE_PRIVATE);
        String data = sharedPreferences.getString("data","defaultname");
        return  data;
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
