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
        }

        /**
         * Test binding to service
         */
        @MediumTest
        public void testBindable() {
            Intent startIntent = new Intent();
            startIntent.setClass(getContext(), RingTimerService.class);
            IBinder service = bindService(startIntent);
            
        }

	@SmallTest
	public void testRingerSleep(){
            AudioManager mAudio = (AudioManager) getContext().getSystemService(Activity.AUDIO_SERVICE);
            mAudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            Binding binding = new Binding();
            binding.doBindService();
            RingTimerService rts = binding.getBoundService();
            assertNotNull(rts);
            rts.ringerSleep(1);
            assertEquals(AudioManager.RINGER_MODE_SILENT, mAudio.getRingerMode());
	}

	@SmallTest
	public void testTurnOffRinger(){
		assertTrue(true);
	}

  public static class Binding extends Activity {
        private boolean mIsBound;

        private RingTimerService mBoundService;

        private ServiceConnection mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {
                // This is called when the connection with the service has been
                // established, giving us the service object we can use to
                // interact with the service.  Because we have bound to a explicit
                // service that we know is running in our own process, we can
                // cast its IBinder to a concrete class and directly access it.
                mBoundService = ((RingTimerService.RingTimerBinder)service).getService();
            }

            public void onServiceDisconnected(ComponentName className) {
                // This is called when the connection with the service has been
                // unexpectedly disconnected -- that is, its process crashed.
                // Because it is running in our same process, we should never
                // see this happen.
                mBoundService = null;
            }
        };

        void doBindService() {
            // Establish a connection with the service.  We use an explicit
            // class name because we want a specific service implementation that
            // we know will be running in our own process (and thus won't be
            // supporting component replacement by other applications).
            bindService(new Intent(Binding.this, 
                    RingTimerService.class), mConnection, Context.BIND_AUTO_CREATE);
            mIsBound = true;
        }

        RingTimerService getBoundService() {
            while (mBoundService == null) {}
            return mBoundService;
        }

        void doUnbindService() {
            if (mIsBound) {
                // Detach our existing connection.
                unbindService(mConnection);
                mIsBound = false;
            }
        }
    }
}