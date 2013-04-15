package com.twofours.surespot.common;

import org.acra.ACRA;

import android.util.Log;
import ch.boye.httpclientandroidlib.client.HttpResponseException;

public class SurespotLog {
	// TODO set false for production
	private static boolean mLogging = SurespotConstants.LOGGING;
	// TODO set true for production
	private static boolean mReport = SurespotConstants.CRASH_REPORTING;

	public static void setLogging(boolean logging) {
		v("SurespotLog", "setting logging to: %b", logging);
		mLogging = logging;
	}

	// by using string.format we avoid string concat overhead when logging is disabled
	public static void w(String tag, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.w(tag, String.format(msg, msgArgs));
		}
	}

	public static void w(String tag, Throwable tr, String msg, Object... msgArgs) {
		if (mLogging) {
			// Log.w(tag, msg +", " + tr.getMessage());
			Log.w(tag, String.format(msg, msgArgs), tr);
		}

		if (mReport) {
			ACRA.getErrorReporter().handleSilentException(tr);
		}
	}

	public static void v(String tag, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.v(tag, String.format(msg, msgArgs));
		}

	}

	public static void d(String tag, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.d(tag, String.format(msg, msgArgs));
		}

	}

	public static void e(String tag, Throwable tr, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.e(tag, String.format(msg, msgArgs), tr);
		}

		if (tr instanceof HttpResponseException) {
			HttpResponseException error = (HttpResponseException) tr;
			int statusCode = error.getStatusCode();

			// no need to report these
			switch (statusCode) {
			case 401:
			case 403:
			case 404:
			case 409:
				return;
			}
		}
		if (mReport) {
			ACRA.getErrorReporter().handleSilentException(tr);
		}

	}

	public static void i(String tag, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.i(tag, String.format(msg, msgArgs));
		}

	}

	public static void v(String tag, Throwable tr, String msg, Object... msgArgs) {
		if (mLogging) {
			Log.v(tag, String.format(msg, msgArgs), tr);
		}
		
		if (mReport) {
			ACRA.getErrorReporter().handleSilentException(tr);
		}

	}
}
