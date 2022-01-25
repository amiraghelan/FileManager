package com.example.filemanager.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filemanager.Listener.OnFileClickListener;
import com.example.filemanager.R;

import java.io.File;
import java.util.List;

public class FileRecyclerAdapter extends RecyclerView.Adapter<FileRecyclerAdapter.ViewHolder>{
    List<File> files;
    Context context;
    OnFileClickListener listener;

    public FileRecyclerAdapter(List<File> files, Context context, OnFileClickListener listener){
        this.context = context;
        this.files = files;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.file_recycler_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File file = files.get(position);
        holder.tvFileName.setText(file.getName());
        if(file.isDirectory()){
            holder.imgIcon.setImageResource(R.drawable.folder);
        }else{
            holder.imgIcon.setImageResource(R.drawable.file);
        }
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvFileName;
        ImageView imgIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFileName = itemView.findViewById(R.id.tv_fileName);
            imgIcon = itemView.findViewById(R.id.img_icon);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(files.get(getAdapterPosition()),context);
                }
            });
        }
    }
}
