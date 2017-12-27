package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.models.CompletedTodoModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/27/17.
 */

public class CompletedTodoAdapter extends RecyclerView.Adapter<CompletedTodoAdapter.CompletedDataHolder>{
    private ArrayList<CompletedTodoModel> completedTodoModels;
    private Context context;

    public CompletedTodoAdapter(ArrayList<CompletedTodoModel> completedTodoModels, Context context) {
        this.completedTodoModels = completedTodoModels;
        this.context = context;
    }

    @Override
    public CompletedTodoAdapter.CompletedDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_completed_todo_layout,parent,false);
        return new CompletedDataHolder(view);
    }

    @Override
    public void onBindViewHolder(CompletedTodoAdapter.CompletedDataHolder holder, int position) {
        CompletedTodoModel completedTodoModel=completedTodoModels.get(position);
        holder.todoTitle.setText(completedTodoModel.getTodoTitle());
        holder.todoContent.setText(completedTodoModel.getTodoContent());
        holder.todoTag.setText(completedTodoModel.getTodoTag());
        holder.todoDate.setText(completedTodoModel.getTodoDate());
        holder.todoTimeFrom.setText(completedTodoModel.getTodoTimeFrom());
        holder.todoTimeTo.setText(completedTodoModel.getToTimeTo());
        holder.todoPriority.setText(completedTodoModel.getTodoPriority());
        holder.relativeLayout.setBackgroundColor(Color.parseColor(completedTodoModel.getTodoColor()));
    }

    @Override
    public int getItemCount() {
        return completedTodoModels.size();
    }

    public class CompletedDataHolder extends RecyclerView.ViewHolder {
        TextView todoTitle,todoContent,todoTag,todoDate,todoTimeFrom,todoTimeTo,todoPriority;
        RelativeLayout relativeLayout;
        public CompletedDataHolder(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.each_pending_todo_section);
            todoTitle=(TextView)itemView.findViewById(R.id.pending_todo_title);
            todoContent=(TextView)itemView.findViewById(R.id.pending_todo_content);
            todoTag=(TextView)itemView.findViewById(R.id.todo_tag);
            todoDate=(TextView)itemView.findViewById(R.id.todo_date);
            todoTimeFrom=(TextView)itemView.findViewById(R.id.todo_time_from);
            todoTimeTo=(TextView)itemView.findViewById(R.id.todo_time_to);
            todoPriority=(TextView)itemView.findViewById(R.id.todo_priority);
        }
    }
}
