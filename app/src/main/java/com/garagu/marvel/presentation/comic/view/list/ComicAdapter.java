package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.Comic;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by garagu.
 */
public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private List<Comic> items;
    private OnComicClickListener listener;

    public ComicAdapter(@NonNull List<Comic> items, @NonNull OnComicClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent);
        ComicViewHolder viewHolder = new ComicViewHolder(view);
        Comic comic = items.get(viewHolder.getAdapterPosition());
        view.setOnClickListener(v -> listener.onComicClick(comic));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComicViewHolder viewHolder, int position) {
        Comic comic = items.get(position);
        viewHolder.bind(comic);
    }

    class ComicViewHolder extends RecyclerView.ViewHolder {

        private ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }

        void bind(Comic comic) {

        }

    }

    interface OnComicClickListener {
        void onComicClick(Comic comic);
    }

}