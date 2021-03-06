/*
 * Copyright (C) 2010 keystoremanager authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.keystore;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.Preference.OnPreferenceChangeListener;

public final class PreferencesActivity extends PreferenceActivity
	implements OnSharedPreferenceChangeListener {

	static final String TAG = "PreferencesActivity";
	
	static final String KEY_SAUVE_PASSEPHRASE = "sauvepassephrase";
	static final String KEY_PASSEPHRASE = "passephrase";

	private CheckBoxPreference sauvePassephrase;
	private EditTextPreference passephrase;

	private boolean containsOnlyNumbers(String  s) {
		if(s.length()<1) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isDigit(s.charAt(i))) {
					return false;
				}
			}
			return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);

		PreferenceScreen preferences = getPreferenceScreen();
		preferences.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		
		sauvePassephrase  = (CheckBoxPreference) preferences.findPreference(KEY_SAUVE_PASSEPHRASE);
		passephrase = (EditTextPreference) preferences.findPreference(KEY_PASSEPHRASE);
		
	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(KEY_SAUVE_PASSEPHRASE)) {
			passephrase.setEnabled(sauvePassephrase.isChecked());
		}
		
	}
}
