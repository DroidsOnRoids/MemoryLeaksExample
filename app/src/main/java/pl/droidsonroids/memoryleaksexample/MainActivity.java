package pl.droidsonroids.memoryleaksexample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    static InnerClass sInstance = null;
    static Activity sActivity = null;

    class InnerClass {
        void doSomething() {
            Toast.makeText(MainActivity.this, "Hello Inner Class!", Toast.LENGTH_SHORT).show();
        }
    }

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // Do something here...
        }
    };

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (sInstance == null) {
            sInstance = new InnerClass();
            sInstance.doSomething();
        }

        if (sActivity == null) {
            sActivity = this;
        }

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor light = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something here...
            }
        }, 10000);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(MainActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(final Sensor sensor, final int accuracy) {

    }
}
