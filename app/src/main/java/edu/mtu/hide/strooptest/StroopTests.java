package edu.mtu.humaneinterfacedesignenterprise.strooptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.google.android.glass.widget.CardBuilder;

import java.util.ArrayList;
import java.util.List;

public class StroopTests extends Activity {
    private int currentCard;

    private List<CardBuilder> cards;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        short test;

        Intent intent = getIntent();

        if (intent == null) {
            test = 0;
        }

        else {
            // Gets data sent from the menu. No value given, defaults to 0.
            test = intent.getShortExtra(StroopActivity.class.getName(), (short)0);
        }

        currentCard = 0;

        // keep the display from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        createCards(this);

        if (test == 0) {
            fourtyKMPerHour();
        }

        else {
            sixtyKMPerHour();
        }

        setContentView(createCard(this, R.drawable.transparent).getView());
    }

    private void fourtyKMPerHour() {
        double[] times = new double[12];

        times[0] = 48.115;

        /* Generate times from start stroop cards should appear. */
        for (int inc = 1; inc < times.length; ++inc) {
            if (inc == 6) {
                times[inc] = times[inc - 1] + 14.95;
            }
            else {
                times[inc] = times[inc - 1] + 12.50;
            }
        }

        Handler mHandler = new Handler();

        // Set up events to run to display the cards in succession after a
        // period of time.
        for (int i = 0; i < times.length; ++i) {
            // Stroop card.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateDisplay();
                }
            }, times[i] * 1000);

            // Transparent card
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateDisplay();
                }
            }, (times[i] + 3) * 1000);
        }
    }

    private void sixtyKMPerHour() {
        double[] times = new double[12];

        times[0] = 23.5;

        /* Generate times from start stroop cards should appear. */
        for (int inc = 1; inc < times.length; ++inc) {
            if (inc == 6) {
                times[inc] = times[inc - 1] + 10.5;
            }
            else {
                times[inc] = times[inc - 1] + 9;
            }
        }

        Handler mHandler = new Handler();

        // Set up events to run to display the cards in succession after a
        // period of time.
        for (int i = 0; i < times.length; ++i) {
            // Stroop card.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateDisplay();
                }
            }, (int)(times[i] * 1000.));

            // Transparent card
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateDisplay();
                }
            }, (int)((times[i] + 3) * 1000.));
        }
    }

    /**
     * Create list of API demo cards.
     */
    private void createCards(Context context) {
        cards = new ArrayList<CardBuilder>();

        // Stroop test images
        cards.add(createCard(context, R.drawable.one));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.two));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.three));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.four));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.five));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.six));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.seven));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.eight));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.nine));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.ten));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.eleven));
        cards.add(createCard(context, R.drawable.transparent));
        cards.add(createCard(context, R.drawable.twelve));
        cards.add(createCard(context, R.drawable.transparent));
    }

    /**
     * Perform a stroop test, whereby the user
     * need not swipe through the cards. Instead
     * the cards will change by themselves.
     *
     * @param ms     - Time in between cards in milliseconds.
     * @param cycles - How many times to go through the cards.
     */
    private void autoSwipeTest(int ms, int cycles) {

        if (ms <= 0) {
            ms = 2000;
        }

        if (cycles <= 0) {
            cycles = 1;
        }

        if (cards == null) {
            createCards(this);
        }

        currentCard = 0;

        Handler mHandler = new Handler();

        // Repeats the cards cycles amount of times.
        int limit = cards.size() * cycles;

        // Set up events to run to display the cards in succession after a
        // period of time.
        for (int i = 1; i <= limit; ++i) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateDisplay();
                }
            }, ms * i);
            /* 1000 ms. = 1 sec.
             * Runs given milliseconds after start. Milliseconds * index gives
             * appropriate time for later cards.
             */
        }
    }

    /**
     * Displays the next card and allows for cycling through cards,
     * if desired.
     */
    private void updateDisplay() {
        if (cards == null) {
            return;
        }

        if (currentCard == cards.size()) {
            currentCard = 0;
        }

        setContentView(cards.get(currentCard).getView());
        ++currentCard;
    }
    /**
     * Creates a card for the list of cards
     */
    private static CardBuilder createCard(Context context, int drawable) {
        return new CardBuilder(context, CardBuilder.Layout.TITLE)
                .addImage(drawable);
    }
}
