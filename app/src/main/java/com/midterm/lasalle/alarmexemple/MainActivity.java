package com.midterm.lasalle.alarmexemple;

import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Switch switchRepeat;
    private SeekBar seekBar;
    private TextView textViewHoras;
    private int numberMax = 0;
    private Button btnConfirm;
    private String message = "";
    private ArrayList<Integer> arrayListDays = new ArrayList<Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        seekBar = findViewById(R.id.seekBar);
        textViewHoras = findViewById(R.id.textViewHoras);
        btnConfirm = findViewById(R.id.btnConfirm);
        switchRepeat = findViewById(R.id.switchRepeat);

        switchRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchRepeat.isChecked()){
                    arrayListDays.add(Calendar.SUNDAY);
                }else{
                    arrayListDays = new ArrayList<>();
                }
            }
        });



        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAlarm(message, numberMax, 0,arrayListDays);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {


                numberMax = (i/4) - 2;

                if(numberMax < 0){
                    numberMax = 0;
                }

                if(numberMax < 13){
                    message = "Good Morning";
                }else if(numberMax > 18){
                    message = "Good Evening";
                }else{
                    message = "good afternoon";
                }
                textViewHoras.setText(Integer.toString(numberMax));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void createAlarm(String message, int hour, int minutes, ArrayList<Integer> arrayListDays) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes)
                .putExtra(AlarmClock.EXTRA_DAYS,arrayListDays)
                ;

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
