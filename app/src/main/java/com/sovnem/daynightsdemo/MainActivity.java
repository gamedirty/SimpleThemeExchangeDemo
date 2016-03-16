package com.sovnem.daynightsdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	View back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	TextView tView;
	ImageView iView;

	private void initView() {
		tView = (TextView) findViewById(R.id.textView1);
		iView = (ImageView) findViewById(R.id.imageView1);
		back = findViewById(R.id.back);
	}

	private boolean getIsDay() {
		SharedPreferences sp = getSharedPreferences(ISDAY, MODE_PRIVATE);
		return sp.getBoolean(ISDAY, true);
	}

	public void change(View v) {
		isDay = !isDay;
		Resources res = getResources();
		// if (isDay) {
		// tView.setBackgroundColor(res.getColor(R.color.textbgcolor_day));
		// tView.setTextColor(res.getColor(R.color.textcolor_day));
		// iView.setImageResource(R.drawable.day_allshare_normal_night);
		// back.setBackgroundColor(res.getColor(R.color.bgcolor_day));
		// } else {
		// tView.setBackgroundColor(res.getColor(R.color.textbgcolor_night));
		// tView.setTextColor(res.getColor(R.color.textcolor_night));
		// iView.setImageResource(R.drawable.night_allshare_normal);
		// back.setBackgroundColor(res.getColor(R.color.bgcolor_night));
		// }

		if (!isDay) {
			setTheme(R.style.night);
		} else {
			setTheme(R.style.day);
		}

		TypedArray tArray = obtainStyledAttributes(new int[] { R.attr.bgcolor,
				R.attr.imgsrc, R.attr.textbg, R.attr.textcolor });

		tView.setBackgroundColor(tArray.getColor(2,
				res.getColor(R.color.textbgcolor_day)));
		tView.setTextColor(tArray.getColor(3,
				res.getColor(R.color.textcolor_day)));
		iView.setImageDrawable(tArray.getDrawable(1));
		back.setBackgroundColor(tArray.getColor(0,
				res.getColor(R.color.bgcolor_day)));
		changeActionBarTitleColor(res
				.getColor(isDay ? android.R.color.holo_red_light
						: android.R.color.holo_red_dark));
		changeActionbarBg(res.getColor(isDay ? android.R.color.holo_green_light
				: android.R.color.holo_green_dark));

		saveIsDay(isDay);
	}

	private void saveIsDay(boolean isDay2) {
		SharedPreferences sp = getSharedPreferences(ISDAY, MODE_PRIVATE);
		sp.edit().putBoolean(ISDAY, isDay2).commit();
	}

	public void showFragment(View view) {
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.content, new MyFragment(isDay))
				.commitAllowingStateLoss();
	}

	public void jump(View view) {
		Intent intent = new Intent(this, OtherActivity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
	}
}
