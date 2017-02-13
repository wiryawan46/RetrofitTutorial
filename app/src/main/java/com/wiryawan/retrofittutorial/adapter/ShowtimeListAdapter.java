package com.wiryawan.retrofittutorial.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wiryawan.retrofittutorial.R;
import com.wiryawan.retrofittutorial.model.ShowTime;
import com.wiryawan.retrofittutorial.util.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wiryawan on 1/13/17.
 */

public class ShowtimeListAdapter extends RecyclerView.Adapter<ShowtimeListAdapter.ShowtimeViewHolder> {

    private List<ShowTime> showTimeList;
    private Context context;

    public ShowtimeListAdapter(Context context) {
        this.context = context;
        showTimeList = new ArrayList<>();
    }

    public void add(ShowTime item) {
        showTimeList.add(item);
        notifyItemInserted(showTimeList.size() - 1);
    }

    public void addAll(List<ShowTime> showTimeList) {
        for (ShowTime showTime : showTimeList) {
            add(showTime);
        }
    }

    public void remove(ShowTime item) {
        int position = showTimeList.indexOf(item);
        if (position > -1) {
            showTimeList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public ShowTime getItem(int position) {
        return showTimeList.get(position);
    }

    @Override
    public ShowtimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_showtime, parent, false);

        return new ShowtimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShowtimeViewHolder holder, int position) {
        final ShowTime showTime = showTimeList.get(position);
        holder.theater.setText(showTime.getBioskop());
        for (int i = 0; i < showTime.getJam().size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.list_item_time, holder.lytime, false);
            TextView time = (TextView) view.findViewById(R.id.time);
            time.setText(showTime.getJam().get(i));
            holder.lytime.addView(view);
        }

        holder.price.setText(showTime.getHarga());

    }

    @Override
    public int getItemCount() {
        return showTimeList.size();
    }

    static class ShowtimeViewHolder extends RecyclerView.ViewHolder {

        TextView theater;
        FlowLayout lytime;
        TextView price;

        public ShowtimeViewHolder(View itemView) {
            super(itemView);

            theater = (TextView) itemView.findViewById(R.id.theater);
            lytime = (FlowLayout) itemView.findViewById(R.id.lyTime);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }

}
