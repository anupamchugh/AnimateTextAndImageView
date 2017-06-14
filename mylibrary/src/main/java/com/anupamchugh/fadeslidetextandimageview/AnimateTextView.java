package com.anupamchugh.fadeslidetextandimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.ArrayRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;


public class AnimateTextView extends android.support.v7.widget.AppCompatTextView {

    public static final int DEFAULT_INTERVAL = 15000;
    public static final int MILLISECONDS = 1,
            SECONDS = 2,
            MINUTES = 3;

    private Animation firstAnimation, secondAnimation;
    private Handler handler;
    private CharSequence[] textArray;
    private boolean isShown;
    private int position;
    private int interval = DEFAULT_INTERVAL;
    private boolean stopped;
    private boolean shuffle;

    public AnimateTextView(Context context) {
        super(context);
        init();
    }

    public AnimateTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        handleAttrs(attrs);
    }

    public AnimateTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        handleAttrs(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AnimateTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        init();
        handleAttrs(attrs);
    }

    public void resume() {
        isShown = true;
        startAnimation();
    }

    public void pause() {
        isShown = false;
        stopAnimation();
    }


    public void stop() {
        isShown = false;
        stopped = true;
        stopAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        pause();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        resume();
    }

    private void init() {
        handler = new Handler();
        isShown = true;
    }

    private void handleAttrs(AttributeSet attrs) {
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.animateTextView);
        this.textArray = a.getTextArray(R.styleable.animateTextView_textArray);
        boolean animType = a.getBoolean(R.styleable.animateTextView_fadeInFadeOut, true);
        shuffle = a.getBoolean(R.styleable.animateTextView_shuffleTexts, true);
        if (animType) {
            firstAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
            secondAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
        } else {
            firstAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.left_to_right);
            secondAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.right_to_left);
        }

        this.interval = Math.abs(a.getInteger(R.styleable.animateTextView_intervalText, 14500)) + getResources().getInteger(android.R.integer.config_longAnimTime);
        a.recycle();
    }


    public CharSequence[] getTextArray() {
        return textArray;
    }


    public void setTextArray(@NonNull String[] textArray) {
        if (textArray.length < 1)
            throw new IllegalArgumentException("There must be at least one text");
        else {
            this.textArray = textArray;

            if (shuffle) {

                this.textArray = shuffleArray(this.textArray);

                stopAnimation();
                position = 0;
                startAnimation();

            } else {
                stopAnimation();
                position = 0;
                startAnimation();
            }
        }
    }

    public void setTexts(@ArrayRes int texts) {
        if (getResources().getStringArray(texts).length < 1)
            throw new IllegalArgumentException("There must be at least one text");
        else {
            this.textArray = getResources().getStringArray(texts);
            if (shuffle) {

                this.textArray = shuffleArray(this.textArray);

                stopAnimation();
                position = 0;
                startAnimation();

            } else {
                stopAnimation();
                position = 0;
                startAnimation();
            }
        }
    }



    public void setInterval(long interval, java.util.concurrent.TimeUnit timeUnit) {
        if (interval <= 0)
            throw new IllegalArgumentException("Timeout must be longer than 0");
        else {
            this.interval = (int) java.util.concurrent.TimeUnit.MILLISECONDS.convert(interval, timeUnit);
        }
    }

    @Override
    public void startAnimation(Animation animation) {
        if (isShown && !stopped)
            super.startAnimation(animation);
    }

    /**
     * start the animation
     */
    protected void startAnimation() {
        setText(textArray[position]);
        startAnimation(firstAnimation);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAnimation(secondAnimation);
                if (getAnimation() != null)
                    getAnimation().setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            if (isShown) {
                                position = position == textArray.length - 1 ? 0 : position + 1;
                                startAnimation();
                            }
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
            }
        }, interval);
    }

    /**
     * stop active animation
     */
    private void stopAnimation() {
        handler.removeCallbacksAndMessages(null);
        if (getAnimation() != null) getAnimation().cancel();
    }


    @IntDef({MILLISECONDS, SECONDS, MINUTES})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimeUnit {
    }

    public CharSequence[] shuffleArray(CharSequence[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            CharSequence a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }

        return ar;
    }
}
