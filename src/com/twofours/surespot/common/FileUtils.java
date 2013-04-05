package com.twofours.surespot.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

public class FileUtils {
	private static final String STATE_DIR = "state";
	private final static String HTTP = "http";
	public final static String IDENTITIES_DIR = "identities";
	private final static String PUBLICKEYS_DIR = "publicKeys";
	private static final String TAG = "FileUtils";

	public static File getHttpCacheDir(Context context) {

		return getCacheDir(context, HTTP);
	}

	private static File getCacheDir(Context context, String unique) {

		// Check if media is mounted or storage is built-in, if so, try and use external cache dir
		// otherwise use internal cache dir
		String cachePath = null;

		// see if we can write to the "external" storage

		File fCacheDir = getExternalCacheDir(context);
		if (fCacheDir != null) {
			String cacheDir = fCacheDir.getPath() + File.separator + unique;

			if (ensureDir(cacheDir)) {
				cachePath = cacheDir;
			}
		}

		if (cachePath == null) {
			cachePath = context.getCacheDir().getPath() + File.separator + unique;
		}

		// SurespotLog.w(TAG,"cachePath", new Exception(cachePath));
		return new File(cachePath);

	}

	private static File getExternalCacheDir(Context context) {
		if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
			return null;
		}

		String cacheDir = null;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			File fCacheDir = context.getExternalCacheDir();
			if (fCacheDir != null) {
				cacheDir = fCacheDir.getPath();
			}
		}
		else {

			String baseDir = Environment.getExternalStorageDirectory().getPath();
			cacheDir = baseDir + "/Android/data/com.twofours.surespot/cache/";
		}
		return new File(cacheDir);

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
		// http://stackoverflow.com/questions/5694933/find-an-external-sd-card-location/5695129#5695129
		return new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "surespot" + File.separator + "identities");
	}

	public static File getImageCaptureDir(Context context) {
		return getCacheDir(context, ".image_capture");
	}

	public static String getIdentityDir(Context context) {
		return context.getFilesDir().getPath() + File.separator + IDENTITIES_DIR;
	}

	public static String getPublicKeyDir(Context context) {
		return context.getFilesDir().getPath() + File.separator + PUBLICKEYS_DIR;
	}

	public static String getStateDir(Context context) {
		return context.getFilesDir().getPath() + File.separator + STATE_DIR;
	}

	public static void wipeImageCaptureDir(Context context) {
		File dir = getImageCaptureDir(context);
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				file.delete();
			}
		}
	}

	public static File createGalleryImageFile(String suffix) throws IOException {

		// Create a unique image file name
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String imageFileName = "image" + "_" + timeStamp + suffix;

		File dir = getPublicImageStorageDir();
		if (FileUtils.ensureDir(dir)) {
			File file = new File(dir.getPath(), imageFileName);
			file.createNewFile();
			// file.setWritable(true, false);
			// SurespotLog.v(TAG, "createdFile: " + file.getPath());
			return file;
		}
		else {
			throw new IOException("Could not create image temp file dir: " + dir.getPath());
		}

	}

	private static File getPublicImageStorageDir() {
		File imageDir;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
			imageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "surespot");
		}
		else {
			imageDir = new File(Environment.getExternalStorageDirectory().getPath() + "/Pictures/surespot");

		}
		return imageDir;
	}

	public static void galleryAddPic(Activity activity, String path) {
		if (activity == null || TextUtils.isEmpty(path)) {
			return;
		}

		Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		File f = new File(path);
		Uri contentUri = Uri.fromFile(f);
		mediaScanIntent.setData(contentUri);
		activity.sendBroadcast(mediaScanIntent);
	}

	public static boolean isExternalStorageMounted() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);
	}
	
	public static void deleteRecursive(File fileOrDirectory) {
		if (fileOrDirectory.isDirectory()) {
			File[] files = fileOrDirectory.listFiles();
			if (files != null) {
				for (File child : files)
					deleteRecursive(child);
			}
		}
		fileOrDirectory.delete();
	}
	
	public static String getSharedPrefsDir(Context context) {
		SurespotLog.v(TAG, "databasePath: " + context.getDatabasePath("ADAM"));
		return null;
	}
	
}
