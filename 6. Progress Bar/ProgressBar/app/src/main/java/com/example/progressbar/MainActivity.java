package com.example.progressbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button press = (Button) findViewById(R.id.press);
        Button reset  = (Button) findViewById(R.id.reset);
        final ProgressBar bar = (ProgressBar) findViewById(R.id.mybar);
        Button sleep  = (Button) findViewById(R.id.sleepbutton);
        final EditText time = (EditText) findViewById(R.id.sleepfor);

//        bar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        bar.getProgressDrawable().setTint(Color.RED);

        press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProgressValue(0,bar);
          }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                bar.setProgress(0);

            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                String sleepTime = time.getText().toString();
                runner.execute(sleepTime);

            }
        });

    }
    private void setProgressValue(final int progress, final ProgressBar bar) {

        // set the progress
        bar.setProgress(progress);
        final int[] p = {0};
        // thread is used to change the progress value
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                try {
                    while(p[0]<=100){
                    Thread.sleep(50);
                    bar.setProgress(p[0]);
                    p[0] += 1;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               // setProgressValue(progress + 10, bar);
                bar.getProgressDrawable().setTint(Color.GREEN);
            }
        });
        thread.start();
    }
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(params[0])*1000;

                Thread.sleep(time);
                resp = "Slept for " + params[0] + " seconds";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();

        }


        @Override
        protected void onPreExecute() {
            final EditText time = (EditText) findViewById(R.id.sleepfor);

            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "Sleep initiated for "+time.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {

        }
    }
    }


