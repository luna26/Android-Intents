package com.example.josemlunagonzalez.demoappnative;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    EditText editPhone, editWeb;
    ImageButton imgBtnPhone, imgBtnWeb, imgBtnCamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editPhone = (EditText) findViewById(R.id.phoneTxt);
        editWeb = (EditText) findViewById(R.id.txtWeb);
        imgBtnPhone = (ImageButton) findViewById(R.id.imgPhone);
        imgBtnWeb = (ImageButton) findViewById(R.id.imgWeb);
        imgBtnCamera = (ImageButton) findViewById(R.id.cameraBtn);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = editPhone.getText().toString();
                if (phoneNumber != null && !phoneNumber.isEmpty()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersion(phoneNumber);
                    }
                }
            }

            private void OlderVersion(String phoneNumber) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentCall);
                } else {
                    Toast.makeText(ThirdActivity.this, "Permissions are necessary", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case PHONE_CALL_CODE:
                String permission = permissions[0];
                int result = grantResults[0];

                if (permission.equals((Manifest.permission.CALL_PHONE))) {
                    //Comprobar si ha sido aceptado o denegada la solicitud
                    if (result == PackageManager.PERMISSION_GRANTED) {
                        //Concedio permiso
                        String phoneNumber = editPhone.getText().toString();
                        Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intentCall);
                        }else{
                            //No lo concedio
                            Toast.makeText(ThirdActivity.this, "Permissions are necessary", Toast.LENGTH_LONG).show();
                        }
                    }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    private boolean checkPermission(String permission){
        int result = this.checkCallingOrSelfPermission(permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
