package com.baba.flashlight;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button b1;
    boolean brnenable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        brnenable = false;

        b1=findViewById(R.id.flashlight);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},1);
            }
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (brnenable){
                    flashlightoff();
                    brnenable=false;
                }
                else {
                    flashlighton();
                    brnenable = true;
                }

            }
        });

    }
  @RequiresApi(api = Build.VERSION_CODES.M)
  void flashlighton() {
      CameraManager cameraManager = (CameraManager) getSystemService(CAMERA_SERVICE);
      try {
          String CamaraId = cameraManager.getCameraIdList()[0];
          cameraManager.setTorchMode(CamaraId, true);

      } catch (Exception e) {

      }
  }
      @RequiresApi(api = Build.VERSION_CODES.M)
      void flashlightoff(){
          CameraManager cameraManager = (CameraManager)getSystemService(CAMERA_SERVICE);
          try {
              String CamaraId = cameraManager.getCameraIdList()[0];
              cameraManager.setTorchMode(CamaraId,false);

          }
          catch (Exception e) {

          }

  }




}
