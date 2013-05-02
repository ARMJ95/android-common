package com.twofours.surespot.common;


public class SurespotConstants {

	private static final String SERVER_PUBLIC_KEY_STAGE = 
			  "-----BEGIN PUBLIC KEY-----\n" +
			  "MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBBpDN/3pyASvHhkY5eb7SEGqN0BkU\n" +
			  "rE+XR4YPDuA84B69n7LFxXtQwf+iQFpaKTskp3TLV2DSB0RGAUAbXxGOHPUAntbw\n" +
			  "3tfnOP8ti7L6jRuGdTJkzy86k5hrlb010OUS7dSHzLlfKxIuj7mmvtHvqfEWOBhL\n" +
			  "O34yFdvRBoN0+KvLvWo=\n" + 
			  "-----END PUBLIC KEY-----";
	private static final String SERVER_PUBLIC_KEY_PROD =			
			"-----BEGIN PUBLIC KEY-----\n" +
			"MIGbMBAGByqGSM49AgEGBSuBBAAjA4GGAAQBaKZxJgGhnJ7v78N1MfbUQUAGD+Fq\n" +
			"vlBEF8tW4w3iMqEw6biOe37XICc+iYiJdCZ/uzrSTODJtSF1WI1NBWlAlosAXBP+\n" +
			"+ypzzyiA7PtX4HOmgZ6fBQUS4ujKAyIkssKaKK8JAsy1eWqxSWFF5kCpZAwBR5jp\n" +
			"9ht00UV2HtyGhAg647c=\n" +
			"-----END PUBLIC KEY-----";

				
	
	public static final String GOOGLE_APP_LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAytYzwMl/RLFQWotcjazzM/xbgqU/UCMEjBJ3QgRG2aT0B0+LCaEkq4bL1FnDvXxUmKOAfGnhn7zbFMfgrfTOOvTuioKgspLE2RLryl9K9MnAxBn2rxBv45gG2E+K97gs7qqrxfrAUTTZPfq8McXVLc3k9WWOf/pfiH25WL7ijig3CNkzJ+BP1u2KQiFltsdln7q+9tMddUWUxd8zP6c0hmCIB9DYLutfqmel0ckjfEPAhTKqsOIpryzcGLZCs2C22Qg0AllQ62ohidEIFdA0SYKp0CjO9E5NO1pM+ZnBlf/cmdEwlhuYyh26rUcZj342fNhWzwiiHIeiiedvXOOS8wIDAQAB";

	public static final String SERVER_PUBLIC_KEY = SERVER_PUBLIC_KEY_STAGE;
			

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

	public final static int MESSAGE_IMAGE_DIMENSION = 1000;
	public final static int FRIEND_IMAGE_DIMENSION = 100;

	// TODO change by screen size
	public final static int IMAGE_DISPLAY_HEIGHT = 320;
	public final static int MAX_USERNAME_LENGTH = 20;
	public final static int MAX_PASSWORD_LENGTH = 256;
	public final static int SAVE_MESSAGE_BUFFER = 25;
	public final static int SAVE_MESSAGE_MINIMUM = 50;
	public final static int MAX_MESSAGE_LENGTH = 1024;
	
	//TODO change to false for production
	public final static boolean LOGGING = true;
	//TODO change to true for production
	public final static boolean CRASH_REPORTING = false;

	public class PrefNames {
		public final static String PREFS_FILE = "surespot_preferences";
		public final static String GCM_ID_RECEIVED = "gcm_id_received";
		public final static String GCM_ID_SENT = "gcm_id_sent";
		public static final String LAST_USER = "last_user";
		public static final String LAST_CHAT = "last_chat";
		public static final String REFERRERS = "referrers";
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
		public final static int PICK_CONTACT = 9;
		public final static int REQUEST_SELECT_FRIEND_IMAGE = 10;
	}
}
