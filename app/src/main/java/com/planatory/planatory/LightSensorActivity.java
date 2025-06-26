package com.planatory.planatory;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LightSensorActivity extends AppCompatActivity {

    private TextView lightText;
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private SensorEventListener lightListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sensor);


        lightText = findViewById(R.id.lightText);

        Button backHomeBtn = findViewById(R.id.backToHomeBtn);
        backHomeBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, Home.class));
            finish();
        });


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        lightSensor   = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        lightListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float lux = event.values[0];
                lightText.setText("Light Level: " + lux + " lx");
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) { }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lightSensor != null) {
            sensorManager.registerListener(
                    lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(lightListener);
    }
}
