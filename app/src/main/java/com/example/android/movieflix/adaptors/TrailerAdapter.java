package com.example.android.movieflix.adaptors;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.movieflix.R;
import com.example.android.movieflix.models.Trailer;

import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Trailer> mTrailers;
    private OnTrailerListener onTrailerListener;

    public TrailerAdapter(OnTrailerListener onTrailerListener){
        this.onTrailerListener = onTrailerListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailer_item, parent, false);

        return new TrailerViewHolder(view,onTrailerListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((TrailerViewHolder)holder).textView.setText("Trailer " + (position +1));

    }

    @Override
    public int getItemCount() {
       if (mTrailers != null){
           return mTrailers.size();
       }
       return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmTrailers(List<Trailer> mTrailers) {
        this.mTrailers = mTrailers;
        notifyDataSetChanged();
    }

    public String getTrailerKey(int position){
        if (mTrailers != null){
            if (!mTrailers.isEmpty()){
                return mTrailers.get(position).getKey();
            }
        }
        return null;
    }
}
