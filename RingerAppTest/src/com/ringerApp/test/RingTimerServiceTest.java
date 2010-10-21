package com.ringerApp.test;

import com.ringerApp.RingTimerService;

import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.SmallTest;

public class RingTimerServiceTest extends ServiceTestCase<RingTimerService> {

	public RingTimerServiceTest() {
		super(com.ringerApp.RingTimerService.class);
		// TODO Auto-generated constructor st
	}
	
	@SmallTest
	public void testTurnOffRinger(){
		assertTrue(true);
	}

}
