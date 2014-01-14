package com.cyanogenmod.settings.device;

import com.immersion.uhl.Device;
import com.immersion.uhl.ImmVibe;
import com.immersion.uhl.MagSweepEffectDefinition;

import android.os.Handler;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class VibBroadcastReceiver extends BroadcastReceiver{

	public static final String PREFS_NAME = "Vib_prefs";
	static boolean ringing = false;
	final static Handler checkhandler = new Handler();
	boolean device_on = false;
	private Context mContext;
	static boolean running = false;
	static boolean screenskip = false;
	static PowerManager powerManager = null;
	boolean custom_general = false;
	boolean custom_phone = false;
	static String general_vib = "8000";
	static String phone_vib = "8000";
	@Override
	   public void onReceive(Context context, Intent intent) {
		mContext = context;
    	int uid = intent.getIntExtra("uid", 6000);
    	Long myl = (long) 007;
    	Long millis = intent.getLongExtra("millis", myl);
		//System.out.println(intent.getAction());
		load();
		if (intent.getAction().equalsIgnoreCase("android.intent.action.PHONE_STATE")) telephone_manager();
		else if (running && intent.getAction().equalsIgnoreCase("vibrator.intent.off.broadcast")) vibrate_off();
		else if (running) vibrate(safeLongToInt(millis),uid);
  	  	else System.out.println("Vibrator Service is off");
	   }


	public void vibrate(int millis,int uid){
		if (var.mDevice!=null) var.mDevice.close();
		device_on = true;
		var.mDevice = Device.newDevice(mContext);
		if (ringing && custom_phone) uid = Integer.parseInt(phone_vib);
		else if (custom_general) uid = Integer.parseInt(general_vib);
		System.out.println("vibrate apk: uid = "+uid+" , milliseconds (maximum=3000) = "+millis);
		MagSweepEffectDefinition effectDef = new MagSweepEffectDefinition(millis, uid, ImmVibe.VIBE_STYLE_SHARP, 0, 0, 0, 0, 0);
		var.mDevice.playMagSweepEffect(effectDef);
	}
	
	public void vibrate_off(){
		if (var.mDevice!=null) var.mDevice.close();
	}
	public void load(){
		SharedPreferences settings = mContext.getSharedPreferences(PREFS_NAME, 0);
		running = settings.getBoolean("running", false);
		custom_general = settings.getBoolean("custom_general", false);
		general_vib = settings.getString("general_vib", "8000");
		custom_phone = settings.getBoolean("custom_phone", false);
		phone_vib = settings.getString("phone_vib", "8000");
	}
	public static int safeLongToInt(long l) {
	    int i = (int)l;
	    if ((long)i != l) {
	        throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
	    }
	    return i;
	}
	
	public void telephone_manager(){
		TelephonyManager telephonyManager = (TelephonyManager)mContext.getSystemService(Context.TELEPHONY_SERVICE);
		PhoneStateListener callStateListener = new PhoneStateListener() {
			public void onCallStateChanged(int state, String incomingNumber)
			{
				if(state==TelephonyManager.CALL_STATE_RINGING) ringing = true;
				else if(state==TelephonyManager.CALL_STATE_OFFHOOK) ringing = false;
				else if(state==TelephonyManager.CALL_STATE_IDLE) ringing = false;
			}
		};
		telephonyManager.listen(callStateListener,PhoneStateListener.LISTEN_CALL_STATE);
	}
}
