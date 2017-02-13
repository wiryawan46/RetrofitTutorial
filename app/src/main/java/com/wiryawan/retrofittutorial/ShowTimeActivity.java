package com.wiryawan.retrofittutorial;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.MenuItem;

import com.wiryawan.retrofittutorial.adapter.ShowtimeListAdapter;
import com.wiryawan.retrofittutorial.model.MovieData;

/**
 * Created by wiryawan on 1/13/17.
 */

public class ShowTimeActivity extends AppCompatActivity {


    private RecyclerView rvShowTime;

    private LinearLayoutManager linearLayoutManager;
    private ShowtimeListAdapter showtimeListAdapter;

    private MovieData movieData;
    private String date;

    public static void start(Context context, MovieData movieData, String date)
    {
        Intent intent = new Intent(context, ShowTimeActivity.class);
        intent.putExtra(ShowTimeActivity.class.getSimpleName() + ".movie", movieData);
        intent.putExtra(ShowTimeActivity.class.getSimpleName() + ".date", date);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showtime);

        movieData = getIntent().getParcelableExtra(ShowTimeActivity.class.getSimpleName() + ".movie");
        date = getIntent().getParcelableExtra(ShowTimeActivity.class.getSimpleName() + ".date");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(Html.fromHtml(movieData.getMovie()));
        actionBar.setSubtitle(date);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rvShowTime = (RecyclerView) findViewById(R.id.rv_showtime);

        linearLayoutManager = new LinearLayoutManager(this);
        showtimeListAdapter = new ShowtimeListAdapter(this);

        rvShowTime.setLayoutManager(linearLayoutManager);
        rvShowTime.setAdapter(showtimeListAdapter);

        showtimeListAdapter.addAll(movieData.getJadwal());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }
}
