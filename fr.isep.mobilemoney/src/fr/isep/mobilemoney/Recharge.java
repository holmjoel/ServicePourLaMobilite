package fr.isep.mobilemoney;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Recharge extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recharge);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_recharge, menu);
		return true;
	}

}
