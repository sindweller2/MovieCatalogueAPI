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

public class MovieDetailActivity extends AppCompatActivity {

    public static final String movie = "movie";

    ImageView mdPosterPath;
    TextView mdTitle, mdReleasDate, mdVoteAverage, mdOverview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        progressBar = findViewById(R.id.progressBar);
        mdPosterPath = findViewById(R.id.md_poster_path);
        mdTitle = findViewById(R.id.md_title);
        mdReleasDate = findViewById(R.id.md_release_date);
        mdVoteAverage = findViewById(R.id.md_vote_average);
        mdOverview = findViewById(R.id.md_overview);

        new LoadMovieData().execute();
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

    public class LoadMovieData extends AsyncTask<Void, Void, Void> {
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

            MovieModel movieModel = getIntent().getParcelableExtra(movie);

            Glide.with(getApplicationContext())
                    .load("https://image.tmdb.org/t/p/original/" + movieModel.getPoster_path())
                    .into(mdPosterPath);

            mdTitle.setText(movieModel.getTitle());
            mdReleasDate.setText(movieModel.getRelease_date());
            mdVoteAverage.setText(movieModel.getVote_average().toString());
            mdOverview.setText(movieModel.getOverview());
        }
    }
}
