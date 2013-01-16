package fr.isep.mobilemoney;

import android.telephony.SmsManager;

public class SmsManagerWrapper {

	private static SmsManager sms;

	public static boolean sendTextMessage(String reciever, String message) {

		if (sms == null) {
			sms = SmsManager.getDefault();
		}
		sms.sendTextMessage(reciever, null, message, null, null);
		return true;

	}
}
