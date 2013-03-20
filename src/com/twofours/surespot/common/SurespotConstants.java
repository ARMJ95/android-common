package com.twofours.surespot.common;


public class SurespotConstants {

	private static final String SERVER_PUBLIC_KEY_DEV = 
			  "-----BEGIN PUBLIC KEY-----\n" +
			  "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBBpDN/3pyASvHhkY5eb7SEGqN0BkU\n" +
			  "rE+XR4YPDuA84B69n7LFxXtQwf+iQFpaKTskp3TLV2DSB0RGAUAbXxGOHPUAntbw\n" +
			  "3tfnOP8ti7L6jRuGdTJkzy86k5hrlb010OUS7dSHzLlfKxIuj7mmvtHvqfEWOBhL\n" +
			  "O34yFdvRBoN0+KvLvWo=\n" + 
			  "-----END PUBLIC KEY-----";
	private static final String SERVER_PUBLIC_KEY_PROD =
	"-----BEGIN PUBLIC KEY-----\n" + 
	"MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBHYBSUCgPnd4Fmj8Zq4HfdHPIwDLz\n" +
	"eWE3yvoEitQQ6FA7OiUI52vwyMxaWU7SwcXtg8vEt7FeSwLd+geDgTa759YAfKO9\n" +
	"UGAf/BzjZRkGosnrxSbK9wRa142xY4Ues+zOKlYXmb9vLVg1T2bO/sgABHWGBAk9\n" +
	"6UT2R4Adc12kHVJKx/g=\n" +
	"-----END PUBLIC KEY-----";

	public static final String SERVER_PUBLIC_KEY = SERVER_PUBLIC_KEY_DEV;
			

	public class IntentFilters {
		public static final String INVITE_REQUEST = "invite_request_intent";
		public static final String INVITE_RESPONSE = "invite_response_intent";
		public static final String MESSAGE_RECEIVED = "message_added_event";
		public static final String FRIEND_INVITE_RESPONSE_EVENT = "friend_invite_event";
		public static final String SOCKET_CONNECTION_STATUS_CHANGED = "socket_io_connection_status_changed";
		public static final String INVITE_NOTIFICATION = "invite_notification";
	}

	public class ExtraNames {
		public static final String NAME = "notification_data";
		public static final String FRIEND_ADDED = "friend_added_data";
		public static final String MESSAGE = "message_data";
		public static final String INVITE_RESPONSE = "friend_invite_response";
		// public static final String SHOW_CHAT_NAME = "show_chat_name";
		public static final String MESSAGE_FROM = "message_from";
		public static final String MESSAGE_TO = "message_to";

		public static final String GCM_CHANGED = "gcm_changed";
		public static final String CONNECTED = "connected";
		public static final String IMAGE_MESSAGE = "image_message";
		public static final String NOTIFICATION_TYPE = "notification_type";

	}

	public final static int MAX_IMAGE_DIMENSION = 1000;

	// TODO change by screen size
	public final static int IMAGE_DISPLAY_HEIGHT = 320;
	public final static int MAX_USERNAME_LENGTH = 24;
	public final static int MAX_PASSWORD_LENGTH = 256;

	public class PrefNames {
		public final static String PREFS_FILE = "surespot_preferences";
		public final static String GCM_ID_RECEIVED = "gcm_id_received";
		public final static String GCM_ID_SENT = "gcm_id_sent";
		public static final String LAST_USER = "last_user";
		public static final String LAST_CHAT = "last_chat";
	}

	public class MimeTypes {
		public final static String TEXT = "text/plain";
		public final static String IMAGE = "image/";
	}

	public class IntentRequestCodes {
		public final static int NEW_MESSAGE_NOTIFICATION = 0;
		public final static int INVITE_REQUEST_NOTIFICATION = 1;
		public final static int INVITE_RESPONSE_NOTIFICATION = 2;
		public final static int FOREGROUND_NOTIFICATION = 3;
		public final static int REQUEST_EXISTING_IMAGE = 4;
		public final static int REQUEST_SELECT_IMAGE = 5;
		public final static int REQUEST_SETTINGS = 6;
		public final static int LOGIN = 7;
		public final static int REQUEST_CAPTURE_IMAGE = 8;
	}
}
