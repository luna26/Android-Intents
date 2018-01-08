package com.example.josemlunagonzalez.demoappnative;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnToast;
    private final String GREETING = "Hello World from Main Activity!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = (Button) findViewById(R.id.asd);

        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Acceder a la segunda Activity y enviamos el String
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("greeter",GREETING);
                startActivity(intent);
            }
        });
    }
}
