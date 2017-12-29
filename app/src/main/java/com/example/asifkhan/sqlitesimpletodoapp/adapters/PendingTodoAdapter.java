package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.models.PendingTodoModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/27/17.
 */

public class PendingTodoAdapter extends RecyclerView.Adapter<PendingTodoAdapter.PendingDataHolder> implements View.OnClickListener{
    private ArrayList<PendingTodoModel> pendingTodoModels;
    private Context context;

    public PendingTodoAdapter(ArrayList<PendingTodoModel> pendingTodoModels, Context context) {
        this.pendingTodoModels = pendingTodoModels;
        this.context = context;
    }

    @Override
    public PendingTodoAdapter.PendingDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_pending_todo_layout,parent,false);
        return new PendingDataHolder(view);
    }

    @Override
    public void onBindViewHolder(PendingTodoAdapter.PendingDataHolder holder, int position) {
        PendingTodoModel pendingTodoModel=pendingTodoModels.get(position);
        holder.todoTitle.setText(pendingTodoModel.getTodoTitle());
        holder.todoContent.setText(pendingTodoModel.getTodoContent());
        holder.todoTag.setText(pendingTodoModel.getTodoTag());
        holder.todoDate.setText(pendingTodoModel.getTodoDate());
        holder.todoTime.setText(pendingTodoModel.getTodoTime());
        holder.option.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return pendingTodoModels.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.option:
                break;
        }
    }

    public class PendingDataHolder extends RecyclerView.ViewHolder {
        TextView todoTitle,todoContent,todoTag,todoDate,todoTime;
        ImageView option;
        public PendingDataHolder(View itemView) {
            super(itemView);
            todoTitle=(TextView)itemView.findViewById(R.id.pending_todo_title);
            todoContent=(TextView)itemView.findViewById(R.id.pending_todo_content);
            todoTag=(TextView)itemView.findViewById(R.id.todo_tag);
            todoDate=(TextView)itemView.findViewById(R.id.todo_date);
            todoTime=(TextView)itemView.findViewById(R.id.todo_time);
            option=(ImageView)itemView.findViewById(R.id.option);
        }
    }
}
