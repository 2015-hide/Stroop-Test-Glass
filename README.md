Color-Glass-App
===============

Used to run the [`Stroop test`](http://en.wikipedia.org/wiki/Stroop_effect) on Google Glass.

## Based on

https://github.com/googleglass/gdk-apidemo-sample

## Requirements
    Android Studio (based on IntelliJ IDEA)
    JDK 1.7
    Android SDK 4.4.2 + Glass Development Kit Preview

## Running the sample on Glass

You can use your IDE to compile and install the sample or use
[`adb`](https://developer.android.com/tools/help/adb.html)
on the command line:

    $ adb install -r strooptest.apk

To start the sample, say "ok glass, start stroop test" from the Glass clock
screen or use the touch menu.