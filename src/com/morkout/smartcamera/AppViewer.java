package com.morkout.smartcamera;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AppViewer extends FrameLayout {
    private static final String TAG = "AppViewer";
	private final TextView mTextView;

	public AppViewer(Context context) {
	    this(context, null, 0);
    	Log.v(TAG, "AppViewer constructor");
	}

	public AppViewer(Context context, AttributeSet attrs) {
	    this(context, attrs, 0);
    	Log.v(TAG, "AppViewer constructor 2");
	}
	    	
	public AppViewer(Context context, AttributeSet attrs, int style) {
	    super(context, attrs, style);
    	Log.v(TAG, "AppViewer constructor 3");
	    LayoutInflater.from(context).inflate(R.layout.start, this);
	    mTextView =  (TextView) findViewById(R.id.hello_view);
	    mTextView.setText("Smart\nCamera");
	}
}
