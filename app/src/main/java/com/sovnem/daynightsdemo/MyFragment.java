package com.sovnem.daynightsdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyFragment extends Fragment {

	boolean isDay;

	public MyFragment(boolean isDay) {
		this.isDay = isDay;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {

		final Context contextThemeWrapper = new ContextThemeWrapper(
				getActivity(), isDay ? R.style.day : R.style.night);
		LayoutInflater localInflater = inflater
				.cloneInContext(contextThemeWrapper);
		View v = localInflater.inflate(R.layout.fragment_layout, container,
				false);
		return v;
	}
}
