package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.SettingsHelper;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.TodoDBHelper;
import com.example.asifkhan.sqlitesimpletodoapp.models.CompletedTodoModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/27/17.
 */

public class CompletedTodoAdapter extends RecyclerView.Adapter<CompletedTodoAdapter.CompletedDataHolder>{
    private ArrayList<CompletedTodoModel> completedTodoModels;
    private Context context;
    private TodoDBHelper todoDBHelper;

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
        todoDBHelper=new TodoDBHelper(context);
        CompletedTodoModel completedTodoModel=completedTodoModels.get(position);
        holder.todoTitle.setPaintFlags(holder.todoTitle.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        holder.todoTitle.setText(completedTodoModel.getTodoTitle());
        SettingsHelper.applyTextColor(holder.todoTitle,context);
        holder.todoContent.setText(completedTodoModel.getTodoContent());
        holder.todoTag.setText(completedTodoModel.getTodoTag());
        holder.todoDate.setText(completedTodoModel.getTodoDate());
        holder.todoTime.setText(completedTodoModel.getTodoTime());
    }

    @Override
    public int getItemCount() {
        return completedTodoModels.size();
    }

    public class CompletedDataHolder extends RecyclerView.ViewHolder {
        TextView todoTitle,todoContent,todoTag,todoDate,todoTime;
        public CompletedDataHolder(View itemView) {
            super(itemView);
            todoTitle=(TextView)itemView.findViewById(R.id.completed_todo_title);
            todoContent=(TextView)itemView.findViewById(R.id.completed_todo_content);
            todoTag=(TextView)itemView.findViewById(R.id.todo_tag);
            todoDate=(TextView)itemView.findViewById(R.id.todo_date);
            todoTime=(TextView)itemView.findViewById(R.id.todo_time);
        }
    }

    //filter the search
    public void filterCompletedTodos(ArrayList<CompletedTodoModel> newCompletedTodoModels){
        completedTodoModels=new ArrayList<>();
        completedTodoModels.addAll(newCompletedTodoModels);
        notifyDataSetChanged();
    }
}
