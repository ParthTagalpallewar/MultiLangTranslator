package com.android.translateapplication.ui.learingLang.viewAdapter.sentences;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.translateapplication.R;
import com.android.translateapplication.data.room.SentensesEntaties;

import java.util.List;

public class SentencesRecyclerAdapter extends RecyclerView.Adapter<SentencesRecyclerAdapter.WordsViewHolder> {


    private List<SentensesEntaties> mWords;
    private OnSentenceClickListener mClickListener;

    String nativeLanguage;
    String desireLanguage;


    public SentencesRecyclerAdapter(List<SentensesEntaties> mWords, OnSentenceClickListener listener, String nativeLanguage, String desireLanguage) {
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
        SentensesEntaties language = mWords.get(position);

        Log.e("TAG", "onBindViewHolder: "+language.getNativeText() + "   " + language.getDesireText()  );

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

    public interface OnSentenceClickListener {
        void onClick(SentensesEntaties entities);
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
