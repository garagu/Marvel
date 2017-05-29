package com.garagu.marvel.presentation.comic.view.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garagu.marvel.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by garagu.
 */
class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<String> items;

    CharacterAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder viewHolder, int position) {
        String character = items.get(position);
        viewHolder.bind(character);
    }

    class CharacterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name)
        TextView txtName;

        CharacterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(String character) {
            txtName.setText(character);
        }

    }

}