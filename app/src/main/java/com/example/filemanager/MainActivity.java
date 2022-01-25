package com.example.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.filemanager.Adapter.FileRecyclerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnInternal, btnExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInternal = findViewById(R.id.btn_internalStorage);
        btnExternal = findViewById(R.id.btn_externalStorage);

        String internalStorage = System.getenv("EXTERNAL_STORAGE");
        String externalStorage = Environment.getExternalStorageDirectory().getAbsolutePath();

        Log.d("TAG", "onCreate: " + internalStorage);
        Log.d("TAG", "onCreate: " + externalStorage);

        if(externalStorage == null || externalStorage.isEmpty()){
            btnExternal.setEnabled(false);
        }

        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(internalStorage);
            }
        });
        btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFile(externalStorage);
            }
        });



    }
    public void openFile(String path){
        Intent intent = new Intent(MainActivity.this, FolderActivity.class);
        intent.putExtra("path",path);
        startActivity(intent);
    }
}