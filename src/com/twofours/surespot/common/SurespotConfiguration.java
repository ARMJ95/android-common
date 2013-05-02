package com.twofours.surespot.common;

import java.io.InputStream;
import java.util.Properties;

import android.content.Context;

public class SurespotConfiguration {
	public static final int SSL_NOT_STRICT = 0;
		
	private static final String TAG = "Configuration";
	private static Properties mConfigProperties;
	private static boolean mStrictSsl;
	private static String mBaseUrl;
	

	public static void LoadConfigProperties(Context context) {
		// Read from the /res/raw directory
		try {
			InputStream rawResource = context.getResources().openRawResource(R.raw.configuration);
			Properties properties = new Properties();
			properties.load(rawResource);
			mConfigProperties = properties;
			mStrictSsl =  Boolean.parseBoolean((String) properties.get("ssl_strict"));
			mBaseUrl = (String) properties.get("baseUrl");
			SurespotLog.v(TAG, "ssl_strict: %b", SurespotConfiguration.isSslCheckingStrict());
			SurespotLog.v(TAG, "baseUrl: %s", SurespotConfiguration.getBaseUrl());		
		}
		catch (Exception e) {
			SurespotLog.e(TAG, e, "could not load configuration properties");
		}
	}

	public static Properties GetConfigProperties() {		
		return mConfigProperties;
	}

	
	public static boolean isSslCheckingStrict() {
		return mStrictSsl;
	}
	
	public static String getBaseUrl() {
		return mBaseUrl;		
	}

	


}
