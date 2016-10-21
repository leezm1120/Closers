package leezm.closers.Utils;

import android.util.Log;

import leezm.closers.Config.DevelopersConfig;

/**
 * 统一管理log类
 * 
 */
public class LogUtils {
	
	private static final String TAG = "LeeZMClosersLog";
	

	public static void v(String tag, String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.v(TAG, tag + "-->" + msg);
		}
	}

	public static void d(String tag, String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.d(TAG, tag + "-->" + msg);
		}
	}

	public static void i(String tag, String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.i(TAG, tag + "-->" + msg);
		}
	}

	public static void w(String tag, String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.v(TAG, tag + "-->" + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.e(TAG, tag + "-->" + msg);
		}
	}

	public static void e(String msg) {
		if (DevelopersConfig.ISDEBUG) {
			Log.e(TAG,"-->" + msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (DevelopersConfig.ISDEBUG) {
			Log.e(TAG, tag + "-->" + msg);
		}
	}
}
