package com.rolex.grateful;

import static java.util.logging.Logger.global;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;


public class MainActivity extends AppCompatActivity {
    private CodeScanner mCodeScanner;
    TextView tex;
    Button add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        CodeScannerView scannerView = findViewById(R.id.scanner);
        Button scan= (Button) findViewById(R.id.button3);
        Button add= (Button)findViewById(R.id.button5);
        Button print =(Button)findViewById(R.id.button2);
        Button delete = (Button)findViewById(R.id.button6);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Toast.makeText(MainActivity.this, "Item Added to Inventory", Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                Toast.makeText(MainActivity.this, "Item Deleted from Inventory", Toast.LENGTH_SHORT).show();

            }
        });
        scan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                mCodeScanner.startPreview();
            }
        });

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull com.google.zxing.Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tex = (TextView) findViewById(R.id.text2);
                        tex.setText(result.getText());
                        Button print =(Button)findViewById(R.id.button2);
                        print.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {




                            }
                        });

                    }
                });
            }


        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCodeScanner.startPreview();
            }
        });


       


    }

    @Override
    protected void onResume() {

        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {


        mCodeScanner.releaseResources();
        super.onPause();
    }
}