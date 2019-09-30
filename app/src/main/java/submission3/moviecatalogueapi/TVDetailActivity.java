package submission3.moviecatalogueapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class TVDetailActivity extends AppCompatActivity {

    public static final String tv = "tv";

    ImageView tdPosterPath;
    TextView tdName, tdFirstAirDate, tdVoteAverage, tdOverview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        tdPosterPath = findViewById(R.id.td_poster_path);
        tdName = findViewById(R.id.td_name);
        tdFirstAirDate = findViewById(R.id.td_first_air_date);
        tdVoteAverage = findViewById(R.id.td_vote_average);
        tdOverview = findViewById(R.id.td_overview);

        new LoadTVData().execute();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    public class LoadTVData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);

            TVModel tvModel = getIntent().getParcelableExtra(tv);

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/original/" + tvModel.getPoster_path())
                    .into(tdPosterPath);

            tdName.setText(tvModel.getName());
            tdFirstAirDate.setText(tvModel.getFirst_air_date());
            tdVoteAverage.setText(tvModel.getVote_average().toString());
            tdOverview.setText(tvModel.getOverview());
        }
    }
}
