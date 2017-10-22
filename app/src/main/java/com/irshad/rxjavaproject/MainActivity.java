package com.irshad.rxjavaproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;


public class MainActivity extends AppCompatActivity {

    Button button,button2,button3,button4;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button4=(Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoObservablefrom();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoObservableJust();
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoObservableDefer();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                demoObservableInterval();
            }
        });
    }

    private void demoObservableInterval() {

        Observable.interval(2, TimeUnit.SECONDS).subscribe(new Subscriber<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                if(aLong==5)
                    unsubscribe();
                System.out.println("On Next interval===="+String.valueOf(aLong));

            }
        });
    }

    private void demoObservableDefer() {
         movie=new Movie("Movie one");
        Observable<Movie> observablemovie= Observable.defer(new Func0<Observable<Movie>>() {
            @Override
            public Observable<Movie> call() {
                return Observable.just(movie);
            }
        });

        movie=new Movie("Movie two");
        observablemovie.subscribe(new Subscriber<Movie>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Movie movie) {
                System.out.println("On Next Just===="+movie.name);

            }
        });
    }

    private void demoObservableJust() {
        Observable.just(1,2,3).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("On Next Just===="+String.valueOf(integer));
            }
        });
    }

    private void demoObservablefrom() {
        Observable.from(new Integer[] {1,2,3}).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("On Next From===="+String.valueOf(integer));

            }
        });
    }



    class Movie{

        String name;

        public Movie( String name){
            this.name=name;

        }



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
