package com.trackin.trail.trailtrackin;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimerTask extends AsyncTask<Void, Long, Void> {
    private Long initialTime=System.currentTimeMillis();
    private Long currentTime=System.currentTimeMillis();
    private TextView timer;

    public TimerTask(TextView timer){
        this.timer=timer;
    }


    @Override
    protected Void doInBackground(Void... voids){
        long duration=0;
        while(duration<5184000) {
            try {
                Thread.sleep(5);
                currentTime = System.currentTimeMillis();
                duration = currentTime - initialTime;
                publishProgress(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Long... val){
        long millis=val[0];
        int hours=(int)TimeUnit.MILLISECONDS.toHours(millis);
        int mins=(int)(TimeUnit.MILLISECONDS.toMinutes(millis)-TimeUnit.HOURS.toMinutes(hours));
        int secs=(int)(TimeUnit.MILLISECONDS.toSeconds(millis)-(TimeUnit.MINUTES.toSeconds(mins)+(TimeUnit.HOURS).toSeconds(hours)));
        long total=TimeUnit.SECONDS.toMillis(secs)+TimeUnit.MINUTES.toMillis(mins)+(TimeUnit.HOURS).toMillis(hours);

        int miltime=(int)(TimeUnit.MILLISECONDS.toMillis(millis)-(TimeUnit.SECONDS.toMillis(secs)+TimeUnit.MINUTES.toMillis(mins)+(TimeUnit.HOURS).toMillis(hours)));

        String time=String.format("%d h, %d min, %d sec, %d millis",
                hours,
                mins,
                secs,
                miltime
        );
        System.out.println(time);
        Date timeFormated= new Date();
        timeFormated.setTime(val[0]);
        timer.setText(time);
        super.onProgressUpdate(val);


    }


}
