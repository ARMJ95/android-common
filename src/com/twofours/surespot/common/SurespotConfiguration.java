package com.twofours.surespot.common;

import java.io.InputStream;
import java.util.Properties;
import android.content.Context;

public class SurespotConfiguration {
	public static final int ENVIRONMENT_DEV = 0;
	public static final int ENVIRONMENT_STAGE = 1;
	public static final int ENVIRONMENT_PROD = 1;

	
	private static final String TAG = "Configuration";
	private static Properties mConfigProperties;
	private static int mEnv;
	private static String mBaseUrl;
	private static Context mContext;

	public static void LoadConfigProperties(Context context) {
		// Read from the /res/raw directory
		try {
			InputStream rawResource = context.getResources().openRawResource(R.raw.configuration);
			Properties properties = new Properties();
			properties.load(rawResource);
			mConfigProperties = properties;
			mEnv = Integer.parseInt((String) properties.get("environment"));
			mBaseUrl = (String) properties.get("baseUrl");
			SurespotLog.v(TAG, "env: " + SurespotConfiguration.getEnvironment());
			SurespotLog.v(TAG, "baseUrl: " + SurespotConfiguration.getBaseUrl());		
		}
		catch (Exception e) {
			SurespotLog.e(TAG, "could not load configuration properties", e);
		}
	}

	public static Properties GetConfigProperties() {		
		return mConfigProperties;
	}

	
	public static int getEnvironment() {
		return mEnv;
	}
	
	public static String getBaseUrl() {
		return mBaseUrl;		
	}

	public static Context getContext() {
		return mContext;
	}

	public static void setContext(Context context) {
		SurespotConfiguration.mContext = context;
	}
}
