package com.example.filemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.filemanager.Adapter.FileRecyclerAdapter;
import com.example.filemanager.Listener.OnFileClickListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FolderActivity extends AppCompatActivity {

    List<File> files = new ArrayList<>();
    String path;
    FileRecyclerAdapter adapter;

    RecyclerView recyclerView;
    TextView tvEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);

        try {
            String s = getIntent().getStringExtra("path");
            if (s != null) {
                path = s;
            }
        } catch (Exception e) {
            Log.d("exception catch", "Main Activity: no path bundled with intent");
        }

        if(path == null || path.isEmpty()){
            tvEmpty = findViewById(R.id.tv_empty);
            tvEmpty.setVisibility(View.VISIBLE);
        }else{
            File file = new File(path);
            setTitle(file.getName());
            files.addAll(Arrays.asList(file.listFiles()));
            for(File f: files){
                Log.d("TAG", "onCreate: " +f.getName());
            }
            if(files.size() == 0){
                tvEmpty = findViewById(R.id.tv_empty);
                tvEmpty.setVisibility(View.VISIBLE);
            }

            adapter = new FileRecyclerAdapter(files, this, new OnFileClickListener() {
                @Override
                public void onClick(File file, Context context) {
                    if(file.isDirectory()){
                        String path = file.getAbsolutePath();
                        Intent intent = new Intent(context, FolderActivity.class);
                        intent.putExtra("path",path);
                        startActivity(intent);
                    }
                }
            });
            recyclerView = findViewById(R.id.rcy_files);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    }
}