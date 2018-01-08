package com.example.josemlunagonzalez.demoappnative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView title;
    private Button btnThird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        title = (TextView) findViewById(R.id.txtId);
        btnThird = (Button) findViewById(R.id.btnThirdAc);

        //Tomar los datos del intent explicito
        Bundle bundle = getIntent().getExtras();
        String bundleText = bundle.getString("greeter");

        if(bundleText != null || bundleText != ""){
            Toast.makeText(SecondActivity.this, bundleText, Toast.LENGTH_LONG).show();
            title.setText(bundleText);
        }else{
            Toast.makeText(SecondActivity.this,"this is empty", Toast.LENGTH_LONG).show();
        }

        btnThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }
}
