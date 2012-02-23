/*
 * Tint Browser for Android
 * 
 * Copyright (C) 2012 - to infinity and beyond J. Devauchelle and contributors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * version 3 as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 */

package org.tint.ui.fragments;

import org.tint.R;
import org.tint.ui.UIManagerProvider;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class TabletWebViewFragment extends BaseWebViewFragment {
		
	private Tab mTab;
	
	private boolean mInitialized;
	
	public TabletWebViewFragment() {	
		super();
		
		mUIManager = null;
		mTab = null;		
		mParentView = null;
		mWebView = null;
		
		mUrlToLoadWhenReady = null;
		mInitialized = false;
	}
	
	public void onTabSelected(Tab tab) {
		mTab = tab;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		if (!mInitialized) {
			try {
				mUIManager = ((UIManagerProvider) activity).getUIManager();
			} catch (ClassCastException e) {
				Log.e("TabletWebViewFragment.onAttach()", e.getMessage());
			}
			
			mInitialized = true;
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		if (mWebViewFragmentListener != null) {
			mWebViewFragmentListener.onFragmentReady(this, mUrlToLoadWhenReady);
		}
		
		mUrlToLoadWhenReady = null;
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (mParentView == null) {
			mParentView = inflater.inflate(R.layout.tablet_webview_fragment, container, false);
		}
		
		return mParentView;
	}
	
	public void onReceivedTitle(WebView view, String title) {
		if (view == mWebView) {
			if (!TextUtils.isEmpty(title)) {
				mTab.setText(title);
			} else {
				mTab.setText(R.string.NewTab);
			}
		}
	}

}
