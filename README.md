# AnimateTextAndImageView
A library to animate through an array of texts or images with fade or slide animation


	
	compile 'com.github.anupamchugh:AnimateTextAndImageView:1.0'



# Demo
![](https://github.com/anupamchugh/AnimateTextAndImageView/blob/master/demo.gif) 
# Cool Feature
To animate the ImageView with different images, you can pass the drawables as well as image urls inside the same object array in the method `setImageArray`. The library uses Picasso to fetch and display the images from URL.

# Usage

To Animate TextViews you can set a string array of the placeholders in the xml itself using 
`app:textArray= "@string/sample_array"`

Another way is to set them is by calling the method `setTextArray` on the AnimatedTextView in the MainActivity.
To set the Images call the method `setImageArray(new Object[]{R.drawable.sample,R.drawable.sample_2})`

To shuffle the order in the TextView or ImageView set as :
`app:shuffleTexts="true"` for TextView 
`app:shuffleImages="true"` for ImageView

To set the time interval between adjacent texts or images use :
`app:intervalText="1000"` for TextView
`app:intervalImage="1000"` for ImageView

To set the animation as slide left to right instead of fadeIn fadeOut(default animation) set as :

`app:fadeInFadeOut="false"` for TextView
`app:imageFadeInFadeOut="false"` for ImageView

# Implementation 



        `AnimateTextView animateTextViewWithShuffle = (AnimateTextView) findViewById(R.id.animateTextViewWithShuffle);
        animateTextViewWithShuffle.setTextArray(new String[]{"We are shuffling","Have Fun"});
        animateTextViewWithShuffle.setInterval(2, TimeUnit.SECONDS);

        AnimateImageView animateImageViewWithShuffle = (AnimateImageView) findViewById(R.id.animateImageViewWithShuffle);
        animateImageViewWithShuffle.setImageArray(new Object[]{R.drawable.image_1, R.drawable.image_2, "https://www.facebook.com/images/fb_icon_325x325.png"});
        animateImageViewWithShuffle.setInterval(3, TimeUnit.SECONDS);`

