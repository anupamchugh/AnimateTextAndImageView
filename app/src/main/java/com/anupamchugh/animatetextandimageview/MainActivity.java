package com.anupamchugh.animatetextandimageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anupamchugh.fadeslidetextandimageview.AnimateImageView;
import com.anupamchugh.fadeslidetextandimageview.AnimateTextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnimateTextView animateTextViewWithoutShuffle = (AnimateTextView) findViewById(R.id.animateTextViewWithoutShuffle);
        animateTextViewWithoutShuffle.setTextArray(new String[]{"Hi", "This is a demo of", "Animating your TextView", "Set the animType to true if you want the Animation to be", "FadeIn and FadeOut Animation", "Set the animType to false in the xml", "To use slide left and right animation", "You can set shuffle to true once you've read this.", "Note: Timeout is set in milliseconds in xml", "You can set the interval in any TimeUnit programmatically", "Bye"});


        AnimateImageView animateImageViewWithoutShuffle = (AnimateImageView) findViewById(R.id.animateImageViewWithoutShuffle);
        animateImageViewWithoutShuffle.setImageArray(new Object[]{R.drawable.image_1, R.drawable.image_2, "https://www.facebook.com/images/fb_icon_325x325.png", "https://developers.google.com/maps/documentation/android-api/images/intents_directions_s.png"});


        AnimateTextView animateTextViewWithShuffle = (AnimateTextView) findViewById(R.id.animateTextViewWithShuffle);
        animateTextViewWithShuffle.setTextArray(new String[]{"We are shuffling", "TimeUnit is set programmatically to two seconds", "AnimType is set to false to allow Slide In Left Right Animation", "Have Fun"});
        animateTextViewWithShuffle.setInterval(2, TimeUnit.SECONDS);

        AnimateImageView animateImageViewWithShuffle = (AnimateImageView) findViewById(R.id.animateImageViewWithShuffle);
        animateImageViewWithShuffle.setImageArray(new Object[]{R.drawable.image_1, R.drawable.image_2, "https://www.facebook.com/images/fb_icon_325x325.png", "https://developers.google.com/maps/documentation/android-api/images/intents_directions_s.png"});
        animateImageViewWithShuffle.setInterval(3, TimeUnit.SECONDS);
    }
}
