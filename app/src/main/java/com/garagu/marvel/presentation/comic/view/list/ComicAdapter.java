package com.garagu.marvel.presentation.comic.view.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.Comic;
import com.garagu.marvel.presentation.common.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by garagu.
 */
class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {

    private List<Comic> items;
    private OnComicClickListener listener;

    ComicAdapter(@NonNull List<Comic> items, @NonNull OnComicClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ComicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comic, parent, false);
        return new ComicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ComicViewHolder viewHolder, int position) {
        Comic comic = items.get(position);
        viewHolder.bind(comic);
    }

    class ComicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_thumbnail)
        ImageView imgThumbnail;
        @BindView(R.id.txt_title)
        TextView txtTitle;
        @BindView(R.id.txt_series)
        TextView txtSeries;

        private ComicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                Comic comic = items.get(getLayoutPosition());
                listener.onComicClick(comic);
            });
        }

        void bind(Comic comic) {
            ImageLoader.load(itemView.getContext(), imgThumbnail, comic.getUrlThumbnail());
            txtTitle.setText(comic.getTitle());
            txtSeries.setText(comic.getSeriesTitle());
        }

    }

    interface OnComicClickListener {
        void onComicClick(Comic comic);
    }

}