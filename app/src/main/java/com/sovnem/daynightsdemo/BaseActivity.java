package com.sovnem.daynightsdemo;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class BaseActivity extends FragmentActivity {
	public boolean isDay;
	public static final String ISDAY = "isday";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isDay = getIsDay();
		if (!isDay) {
			setTheme(R.style.night);
		} else {
			setTheme(R.style.day);
		}

	}

	private boolean getIsDay() {
		SharedPreferences sp = getSharedPreferences(ISDAY, MODE_PRIVATE);
		return sp.getBoolean(ISDAY, true);
	}

	public void changeActionBarTitleColor(int color) {
		int titleId = Resources.getSystem().getIdentifier("action_bar_title",
				"id", "android");
		TextView yourTextView = (TextView) findViewById(titleId);
		yourTextView.setTextColor(color);
	}

	public void changeActionbarBg(int color) {
		getActionBar().setBackgroundDrawable(new ColorDrawable(color));
	}
}
