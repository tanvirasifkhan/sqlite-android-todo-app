package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.models.TagsModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/29/17.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagDataHolder> implements View.OnClickListener{
    private ArrayList<TagsModel> tagsModels;
    private Context context;

    public TagAdapter(ArrayList<TagsModel> tagsModels, Context context) {
        this.tagsModels = tagsModels;
        this.context = context;
    }

    @Override
    public TagDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_all_tags_layout,parent,false);
        return new TagDataHolder(view);
    }

    @Override
    public void onBindViewHolder(TagDataHolder holder, int position) {
        TagsModel tagsModel=tagsModels.get(position);
        holder.tag_title.setText(tagsModel.getTagTitle());
        holder.tag_option.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return tagsModels.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tags_option:
                break;
        }
    }

    public class TagDataHolder extends RecyclerView.ViewHolder{
        TextView tag_title;
        ImageView tag_option;
        RelativeLayout relativeLayout;
        public TagDataHolder(View itemView) {
            super(itemView);
            tag_title=(TextView)itemView.findViewById(R.id.tag_title);
            tag_option=(ImageView)itemView.findViewById(R.id.tags_option);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.each_tag);
        }
    }
}
