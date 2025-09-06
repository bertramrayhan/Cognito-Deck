package com.example.cognitodeck.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cognitodeck.R;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.Themes;
import com.example.cognitodeck.database.entity.TopicDisplayItem;

import java.util.List;

public class LibraryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<LibraryListItem> libraryItemList;

    private static final int TYPE_THEME = 0;
    private static final int TYPE_TOPIC = 1;

    public LibraryAdapter(List<LibraryListItem> libraryItemList) {
        this.libraryItemList = libraryItemList;
    }

    public void setLibraryItemList(List<LibraryListItem> libraryItemList) {
        this.libraryItemList.clear();
        this.libraryItemList.addAll(libraryItemList);
        Log.d("LibraryAdapter", "Data set. New size: " + this.libraryItemList.size());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        LibraryListItem item = libraryItemList.get(position);
        if(item instanceof Themes){
            return TYPE_THEME;
        }else {
            return TYPE_TOPIC;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_THEME) {
            View view = inflater.inflate(R.layout.item_theme_parent, parent, false);
            return new ThemeViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_theme_child, parent, false);
            return new TopicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_THEME){
            Themes item = (Themes) libraryItemList.get(position);
            ((ThemeViewHolder) holder).bind(item);
        }else {
            TopicDisplayItem item = (TopicDisplayItem) libraryItemList.get(position);
            ((TopicViewHolder) holder).bind(item);

            holder.itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        Log.d("LibraryAdapter", "get item count: " + this.libraryItemList.size());
        return libraryItemList.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        private TextView themeText;

        public ThemeViewHolder(@NonNull View itemView) {
            super(itemView);

            themeText = itemView.findViewById(R.id.themeText);
        }

        public void bind(Themes theme) {
            themeText.setText(theme.getThemeName());
        }
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        private TextView topicText;
        private View itemView;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);

            topicText = itemView.findViewById(R.id.topicText);
            this.itemView = itemView;
        }

        public void bind(TopicDisplayItem topicItem) {
            topicText.setText(topicItem.getTopic().getTopicName());

            int backgroundResource = R.drawable.bg_item_theme_single_child;
            switch(topicItem.getPosition()){
                case FIRST:
                    backgroundResource = R.drawable.bg_item_theme_first_child;
                    break;
                case MIDDLE:
                    backgroundResource = R.drawable.bg_item_theme_middle_child;
                    break;
                case LAST:
                    backgroundResource = R.drawable.bg_item_theme_last_child;
                    break;
                case SINGLE:
                    backgroundResource = R.drawable.bg_item_theme_single_child;
                    break;
            }

            itemView.setBackgroundResource(backgroundResource);
        }
    }
}