package com.ringerApp.test;

import android.app.Activity;
import android.content.Context;
import android.content.ComponentName;
import com.ringerApp.RingTimerService;
import android.content.Intent;
import android.os.IBinder;
import android.content.ServiceConnection;
import android.media.AudioManager;

import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.test.suitebuilder.annotation.MediumTest;

public class RingTimerServiceTest extends ServiceTestCase<RingTimerService> {

	public RingTimerServiceTest() {
		super(RingTimerService.class);
	}
        protected void setUp() throws Exception {
            super.setUp();
        }
	
        @SmallTest
        public void testPreconditions() {
        }

        /**
         * Test basic startup/shutdown of Service
         */
        @SmallTest
        public void testStartable() {
            Intent startIntent = new Intent();
            startIntent.setClass(getContext(), RingTimerService.class);
            startService(startIntent);
            assertNotNull(getService());
        }

        /**
         * Test binding to service
         */
        @MediumTest
        public void testBindable() {
            Intent startIntent = new Intent();
            startIntent.setClass(getContext(), RingTimerService.class);
            IBinder service = bindService(startIntent);
            assertNotNull(service);
        }

	@SmallTest
	public void testRingerSleep(){
            Intent startIntent = new Intent();
            startIntent.setClass(getContext(), RingTimerService.class);
            startService(startIntent);
            AudioManager mAudio = (AudioManager) getContext().getSystemService(Activity.AUDIO_SERVICE);
            mAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            RingTimerService rts = getService();
            assertNotNull(rts);
            rts.ringerSleep(1);
            assertEquals(AudioManager.RINGER_MODE_SILENT, mAudio.getRingerMode());
	}

	@SmallTest
	public void testTurnOffRinger(){
		assertTrue(true);
	}
}
