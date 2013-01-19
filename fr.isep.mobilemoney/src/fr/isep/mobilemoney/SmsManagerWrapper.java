package fr.isep.mobilemoney;

import android.telephony.SmsManager;

public class SmsManagerWrapper {

	private static SmsManager sms;

	public static boolean sendTextMessage(String receiver, String message) {

		if (sms == null) {
			sms = SmsManager.getDefault();
		}
		if(receiver.equalsIgnoreCase("0695393391") || receiver.equalsIgnoreCase("0695152140")){
			sms.sendTextMessage(receiver, null, message, null, null);
		}
		return true;

	}
}
