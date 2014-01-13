package com.cyanogenmod.settings.device;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Switch;
import com.immersion.uhl.Device;
import com.immersion.uhl.ImmVibe;
import com.immersion.uhl.MagSweepEffectDefinition;

public class VibrationFragmentActivity extends Activity extends PreferenceFragment {
	static Context y = null;
	Device mDevice = null;
	Button Activate = null;
	Button save_button = null;
	Button Settings = null;
	Switch custom_general_switch = null;
	Switch custom_phone_switch = null;
	boolean custom_general = false;
	boolean custom_phone = false;
	static String general_vib = "8000";
	static String phone_vib = "8000";
	Button DeActivate = null;
	private static SeekBar general_seekBar;
	private static SeekBar phone_seekBar;
	public static final String PREFS_NAME = "Vib_prefs";
	private static final String TAG = "S2Plus_Vibration_Settings";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.vibration_preferences);
		y = this;
		load();
		//vibrate(10000,5000);
		main_layout();
	}
	public void vibrate(int millis,int uid){
		mDevice = Device.newDevice(y);
		MagSweepEffectDefinition effectDef = new MagSweepEffectDefinition(millis, uid, ImmVibe.VIBE_STYLE_SHARP, 0, 0, 0, 0, 0);
		mDevice.playMagSweepEffect(effectDef);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	public void save_running(Boolean running){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("running", running);
  	  	//System.out.println("save running : " + running);
		editor.commit();
	}
	public void settings_layout(){
		general_seekBar = (SeekBar) findViewById(R.id.seekBar1);
		phone_seekBar = (SeekBar) findViewById(R.id.seekBar2);
		custom_general_switch = (Switch) findViewById(R.id.switch1);
		custom_phone_switch = (Switch) findViewById(R.id.switch2);
		
		save_button = (Button) findViewById(R.id.button_save);
		save_button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				save_sets();
				setContentView(R.layout.activity_main);
				main_layout();
			}
		});
		general_seekBar.setMax(10100);
		general_seekBar.setProgress(Integer.parseInt(general_vib));
		general_seekBar.setOnTouchListener(new OnTouchListener() {@Override public boolean onTouch(View v, MotionEvent event) {
			general_vib = ""+general_seekBar.getProgress();
			return false;
			}
		});
		phone_seekBar.setMax(10100);
		phone_seekBar.setProgress(Integer.parseInt(phone_vib));
		phone_seekBar.setOnTouchListener(new OnTouchListener() {@Override public boolean onTouch(View v, MotionEvent event) {
			phone_vib = ""+phone_seekBar.getProgress();
			return false;
			}
		});
		if (custom_general==true) custom_general_switch.setChecked( true );  
		else custom_general_switch.setChecked( false );  
		if (custom_phone==true) custom_phone_switch.setChecked( true );  
		else custom_phone_switch.setChecked( false );
	}
	public void save_sets(){
		if (custom_general_switch.isChecked()) custom_general = true;
		else custom_general = false;
		if (custom_phone_switch.isChecked()) custom_phone = true;
		else custom_phone = false;
		save(custom_general,general_vib,custom_phone,phone_vib);
	}

	public void save(boolean CG, String CGV, boolean CP, String CPV){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("custom_general", CG);
		editor.putString("general_vib", CGV);
		editor.putBoolean("custom_phone", CP);
		editor.putString("phone_vib", CPV);
		editor.commit();
	}
	public void main_layout(){

		Activate = (Button) findViewById(R.id.button1);
		Activate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				vibrate(50,10000);
				save_running(true);
			}
		});

		DeActivate = (Button) findViewById(R.id.button2);
		DeActivate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (var.mDevice!=null) var.mDevice.close();
				save_running(false);
			}
		});

		Settings = (Button) findViewById(R.id.button3);
		Settings.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				setContentView(R.layout.settings);
				settings_layout();
			}
		});
	}

	public void load(){
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		custom_general = settings.getBoolean("custom_general", false);
		general_vib = settings.getString("general_vib", "8000");
		custom_phone = settings.getBoolean("custom_phone", false);
		phone_vib = settings.getString("phone_vib", "8000");
	}
}
