package com.example.asifkhan.sqlitesimpletodoapp.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asifkhan.sqlitesimpletodoapp.R;
import com.example.asifkhan.sqlitesimpletodoapp.activities.AllTags;
import com.example.asifkhan.sqlitesimpletodoapp.helpers.TagDBHelper;
import com.example.asifkhan.sqlitesimpletodoapp.models.TagsModel;

import java.util.ArrayList;

/**
 * Created by asifkhan on 12/29/17.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagDataHolder> {
    private ArrayList<TagsModel> tagsModels;
    private Context context;
    private TagDBHelper tagDBHelper;

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
        final TagsModel tagsModel=tagsModels.get(position);
        holder.tag_title.setText(tagsModel.getTagTitle());
        tagDBHelper=new TagDBHelper(context);
        holder.tag_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,view);
                popupMenu.getMenuInflater().inflate(R.menu.tag_edit_del_option,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.edit:
                                return true;
                            case R.id.delete:
                                removeTag(tagsModel.getTagID());
                                return true;
                            default:
                                return false;
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return tagsModels.size();
    }

    public class TagDataHolder extends RecyclerView.ViewHolder{
        TextView tag_title;
        ImageView tag_option;
        public TagDataHolder(View itemView) {
            super(itemView);
            tag_title=(TextView)itemView.findViewById(R.id.tag_title);
            tag_option=(ImageView)itemView.findViewById(R.id.tags_option);
        }
    }

    //remove tag
    private void removeTag(final int tagID){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle(R.string.tag_delete_dialog_title);
        builder.setMessage(R.string.tag_delete_dialog_msg);
        builder.setPositiveButton(R.string.tag_delete_yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(tagDBHelper.removeTag(tagID)){
                    Toast.makeText(context, R.string.tag_deleted_success, Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, AllTags.class));
                }
            }
        }).setNegativeButton(R.string.tag_delete_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, R.string.tag_no_delete, Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, AllTags.class));
            }
        }).create().show();
    }
}
