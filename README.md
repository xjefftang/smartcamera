smartcamera
===========

Google Glass GDK app that lets you preview or zoom before taking a picture, and upload the picture taken after zoom to Google Drive!

Main Features 
--------------------------------
* Preview before you take a picture or video, using the Glass camera button; 

* Zoom in or out (using swipe forward or backward gesture) before long press on the touchpad to take a picture;

* After the picture is zoomed and taken, you can view it and tap to a see a menu to upload it to Google Drive (to make this work, you need to download the Google Drive apk at http://www.androidfilehost.com/?fid=23212708291680134 and then install it to Glass using 'adb install com.google.android.apps.docs-1.2.461.14.apk') or delete from Glass.

Notes
--------------------------------
Pictures taken after Preview will go to the timeline as pictures taken using the camera without preview - the default Camera functionality of Glass. You can make vignette, share or delete.

But pictures taken after zoom in or out will not go to the timline, but be presented an option to upload to Google Drive or delete from Glass.

Steps to Build and Run the App
--------------------------------
1) Get the repo `git clone https://github.com/xjefftang/smartcamera.git`

2) In your ADT’s Package Explorer, right mouse click to show the menu, then select Import and choose the smartcamera folder generated in step 1);

3) Right mouse click on the glasswaretemplate on the ADT’s left Package Explorer panel, select Properties, then change Project Build Target from “Android 4.0.3” to “Glass Development Kit Sneak Peek”; click OK and you’re ready to run the app;

4) Connect Glass to your computer, run and install the app. Say “ok glass” then "smart camera", the app will run and show the main screen with “Smart Camera” text, tap on it and swipe and you’ll see the menu items "STOP", “Preview”, and "Zoom".

Questions, Suggestions or Any Feedback?
--------------------------------
Contact me via email at xjefftang@gmail.com or https://plus.google.com/+JeffTangX
