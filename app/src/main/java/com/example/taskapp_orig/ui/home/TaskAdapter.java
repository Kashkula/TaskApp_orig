package com.example.taskapp_orig.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp_orig.R;
import com.example.taskapp_orig.ui.ITaskListener;
import com.example.taskapp_orig.ui.models.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class TaskAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Task> list;


    public TaskAdapter(ArrayList<Task> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class ViewHolder extends RecyclerView.ViewHolder {

    private TextView textTitle;
    private TextView textTime;
    private ITaskListener listener;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = itemView.findViewById(R.id.textView);
        textTime = itemView.findViewById(R.id.textTime);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onElement(textTitle.getText().toString());
            }
        });
    }

    public void bind(Task task) {
        textTitle.setText(task.getTitle());
        textTime.setText(getDate(task.getCreatedAt()));
    }
    private String getDate(long time) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm zzzz", Locale.getDefault());
        return dateFormat.format(time);
    }
}
