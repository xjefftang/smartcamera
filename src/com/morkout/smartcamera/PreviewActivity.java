package com.morkout.smartcamera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends Activity
{
	public static String TAG = "PreviewActivity";
	
	public static float FULL_DISTANCE = 8000.0f;
	
    private SurfaceView mPreview;
    private SurfaceHolder mPreviewHolder;
    private Camera mCamera;
    private boolean mInPreview = false;
    private boolean mCameraConfigured = false;
    private TextView mZoomLevelView;
    
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;


 
       

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	Log.v(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        
        // as long as this window is visible to the user, keep the device's screen turned on and bright.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // WORKS ON GLASS!

        mPreview = (SurfaceView)findViewById(R.id.preview);
        mPreviewHolder = mPreview.getHolder();
        mPreviewHolder.addCallback(surfaceCallback);
        
        mZoomLevelView = (TextView)findViewById(R.id.zoomLevel);
        mZoomLevelView.setText("");
        

        mCamera = Camera.open();
        startPreview();        
    }



    private void initPreview(int width, int height) 
    {    	
        if ( mCamera != null && mPreviewHolder.getSurface() != null) {
            try 
            {
                mCamera.setPreviewDisplay(mPreviewHolder);
            }
            catch (Throwable t) 
            {
                Log.e(TAG, "Exception in initPreview()", t);
                Toast.makeText(PreviewActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

            if ( !mCameraConfigured ) 
            {
                Camera.Parameters parameters = mCamera.getParameters();
                parameters.setPreviewSize(240, 160); // almost there too
                mCamera.setParameters(parameters);
                
                mCameraConfigured = true;
            }
        }
    }


    
    private void startPreview() 
    {
        if ( mCameraConfigured && mCamera != null ) 
        {
            mCamera.startPreview();
            mInPreview = true;
        }
    }


    SurfaceHolder.Callback surfaceCallback = new SurfaceHolder.Callback() {
        public void surfaceCreated( SurfaceHolder holder ) 
        {
        	// nothing
        	Log.v(TAG, "surfaceCreated");
        }

        public void surfaceChanged( SurfaceHolder holder, int format, int width, int height ) 
        {
        	Log.v(TAG, "surfaceChanged="+width+","+height);
            initPreview(width, height);
            startPreview();
        }

        public void surfaceDestroyed( SurfaceHolder holder ) 
        {
        	Log.v(TAG, "surfaceDestroyed");
        	if (mCamera != null) {
	            mCamera.release();
	            mCamera = null;
        	}
        }
    };
    
    
    @Override
    public void onResume() 
    {
    	Log.v(TAG, "onResume");
        super.onResume();

        // Re-acquire the camera and start the preview.
    }

    @Override
    public void onPause() 
    {
    	Log.v(TAG, "onPause");
        if ( mInPreview ) {
            mCamera.stopPreview();

	        mCamera.release();
	        mCamera = null;
	        mInPreview = false;
        }
        super.onPause();
    }    
    
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	Log.v(TAG,  "onKeyDown");
        if (keyCode == KeyEvent.KEYCODE_CAMERA) { // for both quick press (image capture) and long press (video capture)
        	Log.v(TAG,  "KEYCODE_CAMERA: "+ (event.isLongPress()?"long press": "not long press"));

//        	if (event.isLongPress()) // video capture 
//        		return true; // If you return true from onKeyDown(), your activity consumes the event and the Glass camera 
        	// doesn't start. Do this only if there is no way to interrupt your activity's use of the camera (for example, 
        	// if you are capturing continuous video).

        	
//            // Stop the preview and release the camera.
//            // Execute your logic as quickly as possible
//            // so the capture happens quickly.
//        	
        	if ( mInPreview ) {
                mCamera.stopPreview();

	            mCamera.release();
	            mCamera = null;
	            mInPreview = false;
        	}
	
            return false;
            
            
        } else {
        	Log.v(TAG,  "NOT KEYCODE_CAMERA");
        	
            return super.onKeyDown(keyCode, event);
        }
    }    
}