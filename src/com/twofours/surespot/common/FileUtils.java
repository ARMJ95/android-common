package com.twofours.surespot.common;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Environment;

public class FileUtils {
	/**
	 * Get a usable cache directory (external if available, internal otherwise).
	 * 
	 * @param context
	 *            The context to use
	 * @param uniqueName
	 *            A unique directory name to append to the cache dir
	 * @return The cache dir
	 */
	public static File getDiskCacheDir(Context context, String uniqueName) {

		// Check if media is mounted or storage is built-in, if so, try and use external cache dir
		// otherwise use internal cache dir
		String cachePath = null;

		// see if we can write to the "external" storage
		if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED || !isExternalStorageRemovable()) {
			cachePath = getExternalCacheDir(context).getPath();
		}

		if (cachePath != null) {
			File cacheDir = new File(cachePath + File.separator + uniqueName);
			if (cacheDir.canWrite()) {
				return cacheDir;
			}

		}

		return new File(context.getCacheDir().getPath() + File.separator + uniqueName);

	}

	/**
	 * Check if external storage is built-in or removable.
	 * 
	 * @return True if external storage is removable (like an SD card), false otherwise.
	 */
	@SuppressLint("NewApi")
	public static boolean isExternalStorageRemovable() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
			return Environment.isExternalStorageRemovable();
		}
		return true;
	}

	/**
	 * Get the external app cache directory.
	 * 
	 * @param context
	 *            The context to use
	 * @return The external cache dir
	 */
	@SuppressLint("NewApi")
	public static File getExternalCacheDir(Context context) {
		File cacheDir = null;
		if (hasExternalCacheDir()) {
			cacheDir = context.getExternalCacheDir();
		}

		if (cacheDir == null) {
			// Before Froyo we need to construct the external cache dir ourselves
			final String sCacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
			cacheDir = new File(Environment.getExternalStorageDirectory().getPath() + sCacheDir);
		}
		return cacheDir;
	}

	/**
	 * Check if OS version has built-in external cache dir method.
	 * 
	 * @return
	 */
	public static boolean hasExternalCacheDir() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
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
		//http://stackoverflow.com/questions/5694933/find-an-external-sd-card-location/5695129#5695129
		return new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "surespot_identities");
	}
}
