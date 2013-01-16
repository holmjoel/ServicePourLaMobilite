package fr.isep.mobilemoney;

import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class ManageAccount extends MoneyActivity {
	
	private TextView mBalance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_account);

		mBalance = (TextView) findViewById(R.id.current_balance);
		
		// Get current balance
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		int userId = settings.getInt("userId", -1);

		RequestJSON requestObj = new RequestJSON();
		requestObj.setAction("getBalance");
		requestObj.setUserId(userId);

		NetworkingTask task = new NetworkingTask(this);
		task.execute(requestObj);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_account, menu);
		return true;
	}

	public void showResult(ResponseJSON response) {
		String message;
		Resources res = getResources();
		
		if (!response.isSuccess()) {
			message = response.getMessage();
			goToScreen(R.id.main_menu, message);
		} else {
			String newBalance = String.format(res.getString(R.string.current_balance), response.getBalance());
			mBalance.setText(newBalance.toCharArray(), 0, newBalance.length());
		}
	}

}
