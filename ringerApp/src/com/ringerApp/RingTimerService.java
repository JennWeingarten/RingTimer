package com.ringerApp;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

public class RingTimerService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
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
