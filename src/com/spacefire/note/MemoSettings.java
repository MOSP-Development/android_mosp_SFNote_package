package com.spacefire.note;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MemoSettings extends PreferenceActivity {

	SharedPreferences preferences;
	private OnSharedPreferenceChangeListener listener;
	public static int themeId;	
	
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.memo_settings);		
		themeId = R.style.AppBaseTheme3;
		
		preferences = getSharedPreferences("MemoSettings",Context.MODE_PRIVATE);
		preferences = PreferenceManager.getDefaultSharedPreferences(this);
		listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
		 @Override
		 public void onSharedPreferenceChanged(SharedPreferences prefs,
		 String key) {
			 changeTheme();
		 }
		 };
	}
	
	@SuppressWarnings("deprecation")
	@Override
	 protected void onResume() {
	 super.onResume();
	 getPreferenceScreen().getSharedPreferences()
	 .registerOnSharedPreferenceChangeListener(listener);
	 }
	@SuppressWarnings("deprecation")
	@Override
	 protected void onPause() {
	 super.onPause();
	 getPreferenceScreen().getSharedPreferences()
	 .unregisterOnSharedPreferenceChangeListener(listener);
	 }

	 private void changeTheme()
	 { 
	 preferences = PreferenceManager.getDefaultSharedPreferences(this);
	 String pref_Theme = preferences.getString("pref_Theme", "");
	 if (pref_Theme.trim().equalsIgnoreCase("NoTitleBar"))
	 {
		 NoteList.scheduledRestart = true;
	 themeId = R.style.AppBaseTheme3;
	 }
	 else if (pref_Theme.trim().equalsIgnoreCase("Light"))
	 {
		 NoteList.scheduledRestart = true;
	 themeId = R.style.AppBaseTheme;
	 }
	 else if (pref_Theme.trim().equalsIgnoreCase("Dark"))
	 {
     NoteList.scheduledRestart = true;
	 themeId = R.style.AppBaseTheme2;
	 } 
	 }
	 	 	 
	@SuppressWarnings("unused")
	public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        boolean value;                    
        return true;       
	}
	
}
