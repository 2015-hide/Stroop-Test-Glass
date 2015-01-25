package edu.mtu.humaneinterfacedesignenterprise.strooptest;
import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;

import com.google.android.glass.widget.CardBuilder;
import java.lang.Runnable;
import java.util.ArrayList;

public class StroopCycle implements Runnable {
    private Activity mActivity;
    private ArrayList<CardBuilder> cards = new ArrayList<CardBuilder>();

    public StroopCycle(Activity act){
        mActivity = act;

        cards.add(createCard(mActivity, R.drawable.one));
        cards.add(createCard(mActivity, R.drawable.two));
        cards.add(createCard(mActivity, R.drawable.three));
        cards.add(createCard(mActivity, R.drawable.four));
        cards.add(createCard(mActivity, R.drawable.five));
        cards.add(createCard(mActivity, R.drawable.six));
        cards.add(createCard(mActivity, R.drawable.seven));
        cards.add(createCard(mActivity, R.drawable.eight));
        cards.add(createCard(mActivity, R.drawable.nine));
        cards.add(createCard(mActivity, R.drawable.ten));
        cards.add(createCard(mActivity, R.drawable.eleven));
        cards.add(createCard(mActivity, R.drawable.twelve));
        cards.add(createCard(mActivity, R.drawable.thirteen));
        cards.add(createCard(mActivity, R.drawable.fourteen));
        cards.add(createCard(mActivity, R.drawable.fifteen));
        cards.add(createCard(mActivity, R.drawable.sixteen));
        cards.add(createCard(mActivity, R.drawable.seventeen));
        cards.add(createCard(mActivity, R.drawable.eighteen));
        cards.add(createCard(mActivity, R.drawable.nineteen));
        cards.add(createCard(mActivity, R.drawable.twenty));
    }

    @Override
    public void run() {
        for(int j = 0; j < cards.size(); j++){
            mActivity.setContentView(cards.get(j).getView());
            SystemClock.sleep(1000);
            if (j == cards.size() - 1){
                j = 0;
            }
        }
    }

    private static CardBuilder createCard(Context context, int drawable) {
        return new CardBuilder(context, CardBuilder.Layout.TITLE)
                .addImage(drawable);
    }
}
