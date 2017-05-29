package com.garagu.marvel.presentation.comic.view.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garagu.marvel.R;
import com.garagu.marvel.domain.model.ComicCreator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by garagu.
 */
class CreatorAdapter extends RecyclerView.Adapter<CreatorAdapter.CreatorViewHolder> {

    private List<ComicCreator> items;

    CreatorAdapter(List<ComicCreator> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CreatorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_creator, parent, false);
        return new CreatorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CreatorViewHolder viewHolder, int position) {
        ComicCreator creator = items.get(position);
        viewHolder.bind(creator);
    }

    class CreatorViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_role)
        TextView txtRole;
        @BindView(R.id.txt_name)
        TextView txtName;

        CreatorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ComicCreator creator) {
            txtRole.setText(creator.getRole());
            txtName.setText(creator.getName());
        }

    }

}