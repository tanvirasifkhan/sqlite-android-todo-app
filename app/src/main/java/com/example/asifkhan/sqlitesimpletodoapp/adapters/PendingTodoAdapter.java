package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.PendingTodoModel;

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
        holder.dateTitle.setText(pendingTodoModel.getTodoDate());
        holder.todoTitle.setText(pendingTodoModel.getTodoTitle());
        holder.todoContent.setText(pendingTodoModel.getTodoContent());
        holder.todoTag.setText(pendingTodoModel.getTodoTag());
        holder.todoDate.setText(pendingTodoModel.getTodoDate());
        holder.todoTimeFrom.setText(pendingTodoModel.getTodoTimeFrom());
        holder.todoTimeTo.setText(pendingTodoModel.getToTimeTo());
        holder.todoPriority.setText(pendingTodoModel.getTodoPriority());
        holder.relativeLayout.setBackgroundColor(Color.parseColor(pendingTodoModel.getTodoColor()));
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
        TextView dateTitle,todoTitle,todoContent,todoTag,todoDate,todoTimeFrom,todoTimeTo,todoPriority;
        RelativeLayout relativeLayout;
        ImageView option;
        public PendingDataHolder(View itemView) {
            super(itemView);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.each_pending_todo_section);
            dateTitle=(TextView)itemView.findViewById(R.id.pending_todo_date_title);
            todoTitle=(TextView)itemView.findViewById(R.id.pending_todo_title);
            todoContent=(TextView)itemView.findViewById(R.id.pending_todo_content);
            todoTag=(TextView)itemView.findViewById(R.id.todo_tag);
            todoDate=(TextView)itemView.findViewById(R.id.todo_date);
            todoTimeFrom=(TextView)itemView.findViewById(R.id.todo_time_from);
            todoTimeTo=(TextView)itemView.findViewById(R.id.todo_time_to);
            todoPriority=(TextView)itemView.findViewById(R.id.todo_priority);
            option=(ImageView)itemView.findViewById(R.id.option);
        }
    }
}
