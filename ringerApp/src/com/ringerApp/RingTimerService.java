package com.ringerApp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Binder;
import android.os.IBinder;

public class RingTimerService extends Service {

        /**
         * Class for clients to access.  Because we know this service always
         * runs in the same process as its clients, we don't need to deal with
         * IPC.
         */
        public class RingTimerBinder extends Binder {
            public RingTimerService getService() {
                return RingTimerService.this;
            }
        }

        // This is the object that receives interactions from clients.  See
        // RemoteService for a more complete example.
        private final IBinder mBinder = new RingTimerBinder();

	@Override
	public IBinder onBind(Intent arg0) {
                return mBinder;
	}

	public void ringerSleep(int seconds) {
		AudioManager mAudio = (AudioManager) getSystemService(Activity.AUDIO_SERVICE);
		mAudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}	

	public void turnOffRinger(Intent intent){
		AudioManager mAudio = (AudioManager) getSystemService(Activity.AUDIO_SERVICE);
		mAudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
	}
	
	public void resetRinger(Intent intent) {
		AudioManager mAudio = (AudioManager) getSystemService(Activity.AUDIO_SERVICE);
		mAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	}

}
