package com.morkout.smartcamera;


import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class ImageViewActivity extends Activity {
	public static String TAG = "ImageViewActivity";
	ImageView mImageview;
	private GestureDetector mGestureDetector;
	File mPictureFilePath;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.imageview);
		Bundle extras = getIntent().getExtras();
		mPictureFilePath = (File)extras.get("picturefilepath");

		Log.v(TAG, "pictureFilePath=" + mPictureFilePath.getAbsolutePath());
		mImageview =  (ImageView) findViewById(R.id.picture);

		Bitmap myBitmap = BitmapFactory.decodeFile(mPictureFilePath.getAbsolutePath());
		int h = (int) ( myBitmap.getHeight() * (640.0 / myBitmap.getWidth()) );

		Bitmap scaled = Bitmap.createScaledBitmap(myBitmap, 640, h, true);
		mImageview.setImageBitmap(scaled);        

		mGestureDetector = new GestureDetector(this);

		mGestureDetector.setBaseListener(new GestureDetector.BaseListener() {
			@Override
			public boolean onGesture(Gesture gesture) {
				if (gesture == Gesture.TAP) {
					Log.v(TAG, "TAP");
					openOptionsMenu();

					return true;
				} else if (gesture == Gesture.TWO_TAP) {
					Log.v(TAG, "TWO_TAP");
					return true;
				} else if (gesture == Gesture.SWIPE_RIGHT) {
					Log.v(TAG, "SWIPE_RIGHT");
					return true;
				} else if (gesture == Gesture.SWIPE_LEFT) {
					return true;
				} else if (gesture == Gesture.LONG_PRESS) {
					Log.v(TAG, "LONG_PRESS");					
					return true;
				} else if (gesture == Gesture.SWIPE_DOWN) {
					Log.v(TAG, "SWIPE_DOWN");
					return false;
				} else if (gesture == Gesture.SWIPE_UP) {
					Log.v(TAG, "SWIPE_UP");
					return true;
				} else if (gesture == Gesture.THREE_LONG_PRESS) {
					Log.v(TAG, "THREE_LONG_PRESS");
					return true;
				} else if (gesture == Gesture.THREE_TAP) {
					Log.v(TAG, "THREE_TAP");
					return true;
				} else if (gesture == Gesture.TWO_LONG_PRESS) {
					Log.v(TAG, "TWO_LONG_PRESS");
					return true;
				} else if (gesture == Gesture.TWO_SWIPE_DOWN) {
					Log.v(TAG, "TWO_SWIPE_DOWN");
					return false;
				} else if (gesture == Gesture.TWO_SWIPE_LEFT) {
					Log.v(TAG, "TWO_SWIPE_LEFT");
					return true;
				} else if (gesture == Gesture.TWO_SWIPE_RIGHT) {
					Log.v(TAG, "TWO_SWIPE_RIGHT");
					return true;
				} else if (gesture == Gesture.TWO_SWIPE_UP) {
					Log.v(TAG, "TWO_SWIPE_UP");
					return true;
				}

				return false;
			}
		});
	}



	public boolean onGenericMotionEvent(MotionEvent event) {
		if (mGestureDetector != null) {
			return mGestureDetector.onMotionEvent(event);
		}
		return false;
	}        




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.imageview, menu);

		return true;
	} 

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.upload:
			Uri imgUri = Uri.parse("file://" + mPictureFilePath.getAbsolutePath());
			Intent shareIntent = ShareCompat.IntentBuilder.from(this)
					.setText("Share image taken by Glass")
					.setType("image/jpeg")
					.setStream(imgUri )
					.getIntent()
					.setPackage("com.google.android.apps.docs");

			startActivity(shareIntent);			

			return true;

		case R.id.delete:
			mPictureFilePath.delete();
			Toast.makeText(ImageViewActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
			finish();
			return true;


		default:
			return super.onOptionsItemSelected(item);
		}
	}    	      

}
