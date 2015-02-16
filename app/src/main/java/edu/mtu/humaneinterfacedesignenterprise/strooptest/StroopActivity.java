package edu.mtu.humaneinterfacedesignenterprise.strooptest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Picture;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.google.android.glass.media.Sounds;
import com.google.android.glass.widget.CardBuilder;
import com.google.android.glass.widget.CardScrollView;

import java.util.ArrayList;
import java.util.List;

public class StroopActivity extends Activity {
    public static final short FOURTY_KM = 0;
    public static final short SIXTY_KM = 1;

    private static final String TAG = StroopActivity.class.getSimpleName();

    private CardScrollView mCardScroller;
    private List<CardBuilder> cards;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // keep the display from turning off
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        cards = new ArrayList<CardBuilder>();

        // Stroop test images
        cards.add(createCard(this, R.drawable.kmhr40));
        cards.add(createCard(this, R.drawable.kmhr60));

        mCardScroller = new CardScrollView(this);
        mCardScroller.setAdapter(new CardAdapter(cards));

        // hide scrollbar
        mCardScroller.setHorizontalScrollBarEnabled(false);

        setCardScrollerListener();

        setContentView(mCardScroller);
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

    private void newActivity(boolean test1) {
        Intent intent = new Intent(this, StroopTests.class);
        intent.putExtra(StroopActivity.class.getName(), (test1 ? FOURTY_KM : SIXTY_KM));
        startActivity(intent);
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
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                am.playSoundEffect(soundEffect);

                if (position == 1) {
                    newActivity(false);
                }

                else {
                    newActivity(true);
                }
            }
        });
    }

}
