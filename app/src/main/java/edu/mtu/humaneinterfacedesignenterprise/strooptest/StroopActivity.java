package edu.mtu.humaneinterfacedesignenterprise.strooptest;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Kosovec
 */
public class StroopActivity extends Activity {
    private static final String TAG = StroopActivity.class.getSimpleName();

    private CardScrollView mCardScroller;

    private boolean autoSwipe;
	
    private int currentCard;
	
    private List<CardBuilder> cards;

    // Visible for testing.
    public CardScrollView getScroller() {
        return mCardScroller;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        autoSwipe = true;

        // keep the display from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        createCards(this);

        if (autoSwipe) {
            autoSwipeTest(7000, 1);
        }

        else {
            manualSwipeTest();
        }
    }

    /**
     * Perform a stroop test, whereby the user
     * must swipe to get from card to card.
     */
    private void manualSwipeTest() {
        if (cards == null) {
            createCards(this);
        }

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardAdapter(cards));

        // hide scrollbar
        mCardScroller.setHorizontalScrollBarEnabled(false);

        setCardScrollerListener();

        setContentView(mCardScroller);
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
        if (!autoSwipe) {
            return;
        }

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
     * Create list of API demo cards.
     */
    private void createCards(Context context) {
        cards = new ArrayList<CardBuilder>();

        // Stroop test images
        cards.add(createCard(context, R.drawable.one));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.two));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.three));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.four));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.five));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.six));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.seven));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.eight));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.nine));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.ten));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.eleven));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.twelve));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.thirteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.fourteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.fifteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.sixteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.seventeen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.eighteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.nineteen));
        cards.add(createCard(context, R.drawable.black));
        cards.add(createCard(context, R.drawable.twenty));
        cards.add(createCard(context, R.drawable.black));
    }

    private static CardBuilder createCard(Context context, int drawable) {
        return new CardBuilder(context, CardBuilder.Layout.TITLE)
                .addImage(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mCardScroller != null) {
            mCardScroller.activate();
        }
    }

    @Override
    protected void onPause() {
	    super.onPause();
        if (mCardScroller != null) {
            mCardScroller.deactivate();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
	    if (mCardScroller != null) {
            mCardScroller.deactivate();
        }
    }

    // Different types of activities can be shown, when tapped on a card.
    private void setCardScrollerListener() {
        if (mCardScroller == null) {
            return;
        }

        mCardScroller.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Clicked view at position " + position + ", row-id " + id);
                int soundEffect = Sounds.SELECTED;

                // Tap to move to next card
                mCardScroller.setSelection(++position);
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(soundEffect);

            }
        });
    }

}
