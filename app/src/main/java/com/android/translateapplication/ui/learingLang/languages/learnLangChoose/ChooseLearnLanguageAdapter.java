package com.android.translateapplication.ui.learingLang.languages.learnLangChoose;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.translateapplication.R;
import com.android.translateapplication.data.room.LanguagesEntities;

import java.util.ArrayList;
import java.util.List;

public class ChooseLearnLanguageAdapter extends RecyclerView.Adapter<ChooseLearnLanguageAdapter.LanguageViewHolder> {


    private List<LanguagesEntities> mLanguages;
    private OnLanguageClickListener mClickListener;


    public ChooseLearnLanguageAdapter(List<LanguagesEntities> languages, OnLanguageClickListener listener) {
        this.mLanguages = languages;
        this.mClickListener = listener;
    }

    @NonNull
    @Override
    public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.item_select_language;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);
        return new LanguageViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
        LanguagesEntities language = mLanguages.get(position);

        holder.nameTextView.setText(language.getName()+"  [ " + language.getNativeName() + " ]");

        holder.itemView.setOnClickListener(v -> {
            if (position != RecyclerView.NO_POSITION){
                mClickListener.onClick(language);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLanguages.size();
    }

    public interface OnLanguageClickListener{
        void onClick(LanguagesEntities entities);
    }

    public class LanguageViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public LanguageViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.item_language);
        }
    }

    public void filterList(ArrayList<LanguagesEntities> list){
        mLanguages = list;
        notifyDataSetChanged();
    }
}
