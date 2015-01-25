package edu.mtu.humaneinterfacedesignenterprise.strooptest;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;


public class StroopActivity extends Activity {

    private static final String TAG = StroopActivity.class.getSimpleName();
    private CardAdapter mAdapter;
    private CardScrollView mCardScroller;
    ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();


    // Visible for testing.
    CardScrollView getScroller() {
        return mCardScroller;
    }
    private StroopCycle str;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        // keep the display from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        mAdapter = new CardAdapter(createCards(this));
        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(mAdapter);

        setContentView(mCardScroller);
        setCardScrollerListener();

        // hide scrollbar
        mCardScroller.setHorizontalScrollBarEnabled(false);
        str = new StroopCycle(this);
    }

    /**
     * Create list of API demo cards.
     */
    private List<CardBuilder> createCards(Context context) {
        ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();

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
        //str.run();
    }

    @Override
    protected void onPause() {
        mCardScroller.deactivate();
        super.onPause();
    }

    /**
     * Different type of activities can be shown, when tapped on a card.
     */
    private void setCardScrollerListener() {
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
