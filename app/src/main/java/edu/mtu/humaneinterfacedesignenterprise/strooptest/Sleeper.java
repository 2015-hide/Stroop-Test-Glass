package edu.mtu.humaneinterfacedesignenterprise.strooptest;

import android.os.SystemClock;


public class Sleeper extends Thread {

    @Override
    public void run() {
        SystemClock.sleep(1000);
    }

}
