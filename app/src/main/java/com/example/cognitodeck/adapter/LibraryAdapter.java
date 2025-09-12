package com.example.cognitodeck.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cognitodeck.R;
import com.example.cognitodeck.database.entity.LibraryListItem;
import com.example.cognitodeck.database.entity.Themes;
import com.example.cognitodeck.database.entity.TopicDisplayItem;

import java.util.List;

public class LibraryAdapter extends ListAdapter<LibraryListItem, RecyclerView.ViewHolder> {

    private static final int TYPE_THEME = 0;
    private static final int TYPE_TOPIC = 1;

    private final TopicClickListener topicClickListener;

    public LibraryAdapter(TopicClickListener topicClickListener) {
        super(DIFF_CALLBACK);
        this.topicClickListener = topicClickListener;
    }

    private static final DiffUtil.ItemCallback<LibraryListItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<LibraryListItem>() {

        @Override
        public boolean areItemsTheSame(@NonNull LibraryListItem oldItem, @NonNull LibraryListItem newItem) {
            if(oldItem instanceof Themes && newItem instanceof Themes){
                return ((Themes) oldItem).getThemeId() == ((Themes) newItem).getThemeId();
            }
            if(oldItem instanceof TopicDisplayItem && newItem instanceof TopicDisplayItem){
                return ((TopicDisplayItem) oldItem).getTopic().getTopicId() == ((TopicDisplayItem) newItem).getTopic().getTopicId();
            }

            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull LibraryListItem oldItem, @NonNull LibraryListItem newItem) {
            return oldItem.equals(newItem);

//            if (oldItem.getClass() != newItem.getClass()) {
//                return false;
//            }
//
//            if (oldItem instanceof Themes) {
//                return ((Themes)oldItem).equals((Themes) newItem);
//            }
//            if (oldItem instanceof TopicDisplayItem) {
//                return ((TopicDisplayItem)oldItem).equals((TopicDisplayItem) newItem);
//            }
//
//            return false;
        }
    };

    @Override
    public int getItemViewType(int position) {
        LibraryListItem item = getItem(position);
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
            Themes item = (Themes) getItem(position);
            ((ThemeViewHolder) holder).bind(item);
        }else {
            TopicDisplayItem item = (TopicDisplayItem) getItem(position);
            ((TopicViewHolder) holder).bind(item);

            holder.itemView.setOnClickListener(v -> topicClickListener.onTopicClick(item));
        }
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

    public interface TopicClickListener {
        void onTopicClick(TopicDisplayItem topicDisplayitem);
    }
}