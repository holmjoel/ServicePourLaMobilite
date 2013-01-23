package fr.isep.mobilemoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class InviteFriend extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invite_friend);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_invite_friend, menu);
		return true;
	}
	
	public void invite(View v){
		
		EditText phonenumber = (EditText) this.findViewById(R.id.phonenumber);
		SmsManagerWrapper.sendTextMessage(phonenumber.toString(), "Someone wants you to join MobileMoneyApp");
	}

}
