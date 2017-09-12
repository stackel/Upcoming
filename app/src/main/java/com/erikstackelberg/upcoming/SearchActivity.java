package com.erikstackelberg.upcoming;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import com.erikstackelberg.upcoming.model.MoviePeopleResponse;
import com.erikstackelberg.upcoming.services.TMDBService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    TMDBService tmdbService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(TMDBService.API_BASE_URL)
                .build();

        tmdbService = retrofit.create(TMDBService.class);

        search("steven");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setIconified(false);

        Observable<String> queryChangedObservable = createQueryChangedObservable(searchView);

        return true;
    }
    private Observable<String> createQueryChangedObservable(SearchView searchView) {
        return Observable.create(emitter -> searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        emitter.onNext(s);
                        return true;
                    }
                }));
    }

    void search(String query) {
        Observable<MoviePeopleResponse> moviePeopleResponseObservable = tmdbService.searchPeople(query);
        moviePeopleResponseObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(moviePeopleResponse -> {
                    System.out.println(moviePeopleResponse.getMoviePeople().get(0));
                });
    }

}
