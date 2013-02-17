package com.twofours.surespot.common;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class FileUtils {
	private static final String STATE_DIR = "state";
	private final static String HTTP_BASE = "http_";
	private final static String IDENTITIES_DIR = "identities";
	private static final String TAG = "FileUtils";

	public static File getHttpCacheDir(Context context, String cacheName) {

		return getCacheDir(context, HTTP_BASE + cacheName);
	}

	private static File getCacheDir(Context context, String unique) {

		// Check if media is mounted or storage is built-in, if so, try and use external cache dir
		// otherwise use internal cache dir
		String cachePath = null;

		// see if we can write to the "external" storage
		if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !Environment.isExternalStorageRemovable()) {
			String cacheDir = context.getExternalCacheDir().getPath() + File.separator + unique;
			if (ensureDir(cacheDir)) {
				cachePath = cacheDir;
			}
		}

		if (cachePath == null) {
			cachePath = context.getCacheDir().getPath() + File.separator + unique;
		}

//		SurespotLog.w(TAG,"cachePath", new Exception(cachePath));
		return new File(cachePath);

	}

	public static boolean ensureDir(String dir) {
		File file = new File(dir);
		return ensureDir(file);
	}

	public static boolean ensureDir(File file) {
		file.mkdirs();
		return file.isDirectory();
	}

	public static File getIdentityExportDir() {
		// TODO handle media not mounted
		// http://stackoverflow.com/questions/5694933/find-an-external-sd-card-location/5695129#5695129
		return new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "surespot" + File.separator + "identities");
	}

	public static File getImageCaptureDir(Context context) {
		return getCacheDir(context, ".image_capture");
	}

	public static String getIdentityDir(Context context) {
		return context.getFilesDir().getPath() + File.separator + IDENTITIES_DIR;
	}

	public static String getStateDir(Context context) {
		return context.getFilesDir().getPath() + File.separator + STATE_DIR;
	}
	
	public static void wipeImageCaptureDir(Context context) {
		File dir = getImageCaptureDir(context);
		for (File file : dir.listFiles()) {
			file.delete();
		}
	}
};
