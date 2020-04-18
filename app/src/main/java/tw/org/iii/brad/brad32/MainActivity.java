package tw.org.iii.brad.brad32;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    private Sensor sensor;
    private MyListener myListener;
//    private TextView v1,v2,v3;
    private MyView myView;
    private float viewW, viewH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView);
//        viewW = myView.getW();            //onCreate時物件還沒做好,還只有指標而已
//        viewH = myView.getH();

        viewW = getWindowManager().getDefaultDisplay().getWidth();
        viewH = getWindowManager().getDefaultDisplay().getHeight();


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor : sensors){
            String name = sensor.getName();
            String type = sensor.getStringType();
            String vendor = sensor.getVendor();
            Log.v("brad",name + ":" + type + ":" + vendor);
        }

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);   //三軸加速感應
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);         //光度
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

//        v1 = findViewById(R.id.v1);
//        v2 = findViewById(R.id.v2);
//        v3 = findViewById(R.id.v3);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            Log.v("brad",myView.getWidth() + " x " + myView.getHeight());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        myListener = new MyListener();
        sensorManager.registerListener(myListener, sensor, SensorManager.SENSOR_DELAY_UI);  //三參數為頻率
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(myListener);
    }

    private class MyListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {        //資料在event身上
            float[] values = event.values;                      //values資料是float陣列
//            v1.setText("X : " + String.format("%.1f",values[0]));
//            v2.setText("Y : " + String.format("%.1f",values[1]));
//            v3.setText("Z : " + String.format("%.1f",values[2]));

            changeBall(values[0]*10, values[1]*10);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    private void changeBall(float x, float y){
//        Log.v("brad","x1 = "+ x);
        float xx = x*viewW/200 + viewW/2;
        float yy = y*-1*viewH/200 + viewH/2;
//        Log.v("brad","x2 = "+ xx);
        myView.setBallXY(xx,yy);
    }
}
