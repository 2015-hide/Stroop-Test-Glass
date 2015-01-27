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
	
    private CardAdapter mAdapter;
    private CardScrollView mCardScroller;
	
    private Handler mHandler;
	
    private int currentCard;
	
    private List<CardBuilder> cards;

    // Visible for testing.
    CardScrollView getScroller() {
        return mCardScroller;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
		
        // keep the display from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        
	createCards(this);
		
	currentCard = 0;
		
	mAdapter = new CardAdapter(cards);
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(mAdapter);

        // hide scrollbar
        mCardScroller.setHorizontalScrollBarEnabled(false);
        
	mHandler = new Handler();
		
	// To repeat card cycle, multiply by the amount of repeats desired. 
	int limit = cards.size();
		
	// Set up events to run to display the cards in succession after a
	// period of time.
	for (int i = 1; i <= limit; ++i) {
		mHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				updateDisplay();
			}
		}, 2000 * i);
		/* 1000 ms. = 1 sec.
		 * Runs given milliseconds after start. Milliseconds * index gives
		 * appropriate time for later cards. If an under 2 second time is desired,
		 * the first iteration needs a larger delay than the subsequent iterations
		 * (approx. increase by 1 second or more); otherwise, the first card will go 
		 * by faster than the following cards.
		 */
	}
    }
	
    /* Displays the next card, and allows for cycling through cards, if desired. */
    private void updateDisplay() {
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

        /**
         * Stroop test images
         */
        cards.add(createCard(context, R.drawable.one));
        cards.add(createCard(context, R.drawable.two));
        cards.add(createCard(context, R.drawable.three));
        cards.add(createCard(context, R.drawable.four));
        cards.add(createCard(context, R.drawable.five));
        cards.add(createCard(context, R.drawable.six));
        cards.add(createCard(context, R.drawable.seven));
        cards.add(createCard(context, R.drawable.eight));
        cards.add(createCard(context, R.drawable.nine));
        cards.add(createCard(context, R.drawable.ten));
        cards.add(createCard(context, R.drawable.eleven));
        cards.add(createCard(context, R.drawable.twelve));
        cards.add(createCard(context, R.drawable.thirteen));
        cards.add(createCard(context, R.drawable.fourteen));
        cards.add(createCard(context, R.drawable.fifteen));
        cards.add(createCard(context, R.drawable.sixteen));
        cards.add(createCard(context, R.drawable.seventeen));
        cards.add(createCard(context, R.drawable.eighteen));
        cards.add(createCard(context, R.drawable.nineteen));
        cards.add(createCard(context, R.drawable.twenty));

        return cards;
    }

    private static CardBuilder createCard(Context context, int drawable) {
        return new CardBuilder(context, CardBuilder.Layout.TITLE)
                .addImage(drawable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCardScroller.activate();
    }

    @Override
    protected void onPause() {
	super.onPause();
        mCardScroller.deactivate();
    }
	
    @Override
    protected void onStop() {
        super.onStop();
	mCardScroller.deactivate();
    }

    /* Unneeded listener, kept in case needed later on. */
    /*/**
     * Different type of activities can be shown, when tapped on a card.
     */
    /*private void setCardScrollerListener() {
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
      } */

}
