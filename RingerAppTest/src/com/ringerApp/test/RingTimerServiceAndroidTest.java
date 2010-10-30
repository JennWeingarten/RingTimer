package com.ringerApp.test;

import java.sql.Connection;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import com.ringerApp.RingTimerService;

public class RingTimerServiceAndroidTest extends ServiceTestCase<RingTimerService> {

	public RingTimerServiceAndroidTest() {
		super(com.ringerApp.RingTimerService.class);
		// TODO Auto-generated constructor st
	}
	
	
	
	@SmallTest
	public void testTurnOffRinger(){
		Connection connection = bindService(new Intent(getContext(), RingTimerService.class));
		//RingTimerService underTest = new RingTimerService();
		//startService(new Intent(getContext(), RingTimerService.class));
		int secondsToSleep =  20;
		
		AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
		audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		underTest.sleepRinger(secondsToSleep);
		audioManager.getRingerMode();
		assertEquals(AudioManager.RINGER_MODE_SILENT, audioManager.getRingerMode());
		
	    
		
	}

}
