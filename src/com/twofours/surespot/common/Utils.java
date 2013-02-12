package com.twofours.surespot.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.SherlockPreferenceActivity;

public class Utils {
	private static Toast mToast;
	private static final String TAG = "Utils";

	// Fast Implementation
	public static String inputStreamToString(InputStream is) throws IOException {
		String line = "";
		StringBuilder total = new StringBuilder();

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
			total.append(line);
		}

		// Return full string
		rd.close();
		is.close();
		return total.toString();
	}

	public static byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		byteBuffer.close();
		inputStream.close();
		return byteBuffer.toByteArray();
	}

	public static String inputStreamToBase64(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		byteBuffer.close();
		inputStream.close();
		return new String(base64Encode(byteBuffer.toByteArray()));
	}

	public static byte[] base64Encode(byte[] buf) {
		return Base64.encode(buf, Base64.NO_WRAP | Base64.URL_SAFE);
	}

	public static byte[] base64Decode(String buf) {
		return Base64.decode(buf, Base64.NO_WRAP | Base64.URL_SAFE);
	}

	public static String makePagerFragmentName(int viewId, long id) {
		return "android:switcher:" + viewId + ":" + id;
	}

	public static void makeToast(Context context, String toast) {
		if (mToast == null) {
			mToast = Toast.makeText(context, toast, Toast.LENGTH_SHORT);
		}

		mToast.setText(toast);
		mToast.show();
	}
	

	public static void makeLongToast(Context context, String toast) {
		if (mToast == null) {
			mToast = Toast.makeText(context, toast, Toast.LENGTH_LONG);
		}

		mToast.setText(toast);
		mToast.show();
	}


	public static String getSharedPrefsString(Context context, String key) {
		SharedPreferences settings = context.getSharedPreferences(SurespotConstants.PrefNames.PREFS_FILE,
				android.content.Context.MODE_PRIVATE);
		return settings.getString(key, null);
	}

	public static void putSharedPrefsString(Context context, String key, String value) {
		SharedPreferences settings = context.getSharedPreferences(SurespotConstants.PrefNames.PREFS_FILE,
				android.content.Context.MODE_PRIVATE);
		Editor editor = settings.edit();
		if (value == null) {
			editor.remove(key);
		}
		else {
			editor.putString(key, value);
		}
		editor.commit();

	}

	public static HashMap<String, Integer> jsonToMap(JSONObject jsonObject) {
		try {
			HashMap<String, Integer> outMap = new HashMap<String, Integer>();

			@SuppressWarnings("unchecked")
			Iterator<String> names = jsonObject.keys();
			while (names.hasNext()) {
				String name = names.next();
				outMap.put(name, jsonObject.getInt(name));
			}

			return outMap;

		}
		catch (JSONException e) {
			SurespotLog.w(TAG, "jsonToMap", e);
		}
		return null;

	}

	public static HashMap<String, Integer> jsonStringToMap(String jsonString) {

		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(jsonString);
			return jsonToMap(jsonObject);
		}
		catch (JSONException e) {
			SurespotLog.w(TAG, "jsonStringToMap", e);
		}

		return null;

	}

	public String md5(String s) {
		try {
			// Create MD5 Hash
			MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++)
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			return hexString.toString();

		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Configure the title bar the way we want it. Would be nice if sherlock would give us an interface.
	 * 
	 * @param activity
	 * @param leftText
	 * @param rightText
	 */
	public static void configureActionBar(SherlockFragmentActivity activity, String leftText, String rightText, boolean home) {
		final ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(home);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		View customNav = LayoutInflater.from(activity).inflate(R.layout.actionbar_title, null);
		actionBar.setCustomView(customNav);
		setActionBarTitles(activity, leftText, rightText);

	}

	public static void setActionBarTitles(SherlockFragmentActivity activity, String leftText, String rightText) {
		TextView navView = (TextView) activity.findViewById(R.id.nav);
		TextView userView = (TextView) activity.findViewById(R.id.user);
		navView.setText(leftText);
		userView.setText(rightText);
	}

	public static void configureActionBar(SherlockActivity activity, String leftText, String rightText, boolean home) {
		final ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(home);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		View customNav = LayoutInflater.from(activity).inflate(R.layout.actionbar_title, null);
		actionBar.setCustomView(customNav);
		setActionBarTitles(activity, leftText, rightText);
	}

	public static void setActionBarTitles(SherlockActivity activity, String leftText, String rightText) {
		TextView navView = (TextView) activity.findViewById(R.id.nav);
		TextView userView = (TextView) activity.findViewById(R.id.user);
		navView.setText(leftText);
		userView.setText(rightText);
	}

	public static void configureActionBar(SherlockPreferenceActivity activity, String leftText, String rightText, boolean home) {
		final ActionBar actionBar = activity.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(home);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		View customNav = LayoutInflater.from(activity).inflate(R.layout.actionbar_title, null);
		actionBar.setCustomView(customNav);
		setActionBarTitles(activity, leftText, rightText);
	}

	public static void setActionBarTitles(SherlockPreferenceActivity activity, String leftText, String rightText) {
		TextView navView = (TextView) activity.findViewById(R.id.nav);
		TextView userView = (TextView) activity.findViewById(R.id.user);
		navView.setText(leftText);
		userView.setText(rightText);
	}
}
