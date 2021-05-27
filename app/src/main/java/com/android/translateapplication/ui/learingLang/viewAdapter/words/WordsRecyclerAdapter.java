package com.android.translateapplication.ui.learingLang.viewAdapter.words;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.translateapplication.R;
import com.android.translateapplication.data.room.WordsEntities;

import java.util.List;

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.WordsViewHolder> {


    private List<WordsEntities> mWords;
    private OnWordClickListener mClickListener;

    String nativeLanguage;
    String desireLanguage;


    public WordsRecyclerAdapter(List<WordsEntities> mWords, OnWordClickListener listener,String nativeLanguage,String desireLanguage) {
        this.mWords = mWords;
        this.mClickListener = listener;
        this.nativeLanguage = nativeLanguage;
        this.desireLanguage = desireLanguage;
    }

    @NonNull
    @Override
    public WordsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layout = R.layout.items_words;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, null, false);
        return new WordsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull WordsViewHolder holder, int position) {
        WordsEntities language = mWords.get(position);


        holder.tvNativeText.setText(language.getNativeText());
        holder.tvDesireText.setText(language.getDesireText());
        holder.tvNative.setText(nativeLanguage);
        holder.tvDesire.setText(desireLanguage);

        holder.itemView.setOnClickListener(v -> {
            if (position != RecyclerView.NO_POSITION) {
                mClickListener.onClick(language);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }

    public interface OnWordClickListener {
        void onClick(WordsEntities entities);
    }

    public class WordsViewHolder extends RecyclerView.ViewHolder {
        public TextView  tvNativeText, tvDesireText, tvNative, tvDesire;

        public WordsViewHolder(View itemView) {
            super(itemView);

            tvNativeText = (TextView) itemView.findViewById(R.id.words_native_text);
            tvDesireText = (TextView) itemView.findViewById(R.id.words_desire_text);
            tvNative = (TextView) itemView.findViewById(R.id.words_native);
            tvDesire = (TextView) itemView.findViewById(R.id.words_desire);

        }
    }


}
