package com.wiryawan.retrofittutorial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wiryawan.retrofittutorial.R;
import com.wiryawan.retrofittutorial.listener.RecyclerViewItemClickListener;
import com.wiryawan.retrofittutorial.model.MovieData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiryawan on 1/13/17.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MoviewViewHolder> {

    private List<MovieData> movieDataList;
    private Context context;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDataList = new ArrayList<>();
    }

    private void add(MovieData item) {
        movieDataList.add(item);
        notifyItemInserted(movieDataList.size() - 1);
    }

    public void addAll(List<MovieData> movieDataList) {
        for (MovieData movieData : movieDataList) {
            add(movieData);
        }
    }

    public void remove(MovieData item) {
        int position = movieDataList.indexOf(item);
        if (position > -1) {
            movieDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public MovieData getItem(int position) {
        return movieDataList.get(position);
    }


    @Override
    public MovieListAdapter.MoviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        final MoviewViewHolder moviewViewHolder = new MoviewViewHolder(view);
        moviewViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPos = moviewViewHolder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (recyclerViewItemClickListener != null) {
                        recyclerViewItemClickListener.onItemClick(adapterPos, moviewViewHolder.itemView);
                    }
                }
            }
        });
        return moviewViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviewViewHolder holder, int position) {

        final MovieData movieData = movieDataList.get(position);
        Picasso.with(context).load(movieData.getPoster()).into(holder.poster);
        holder.title.setText(Html.fromHtml(movieData.getMovie()));
        holder.genre.setText(context.getResources().getString(R.string.genre, movieData.getGenre()));
        holder.duration.setText(context.getResources().getString(R.string.duration, movieData.getDuration()));

    }

    @Override
    public int getItemCount() {
        return movieDataList.size();
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    static class MoviewViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title, genre, duration;

        public MoviewViewHolder(View itemView) {
            super(itemView);

            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
            genre = (TextView) itemView.findViewById(R.id.genre);
            duration = (TextView) itemView.findViewById(R.id.duration);
        }

    }
}
