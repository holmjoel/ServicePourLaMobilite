package fr.isep.mobilemoney;

import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Withdrawal extends MoneyActivity {

	private EditText mAmount;
	private double amount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal);
		
		mAmount = (EditText) findViewById(R.id.amount);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_withdrawal, menu);
		return true;
	}

	public void withdrawal(View clickedButton){
		String amountStr = mAmount.getText().toString();

		if (amountStr.isEmpty()) {
			Resources res = getResources();
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_empty));
		} else {
			this.amount = Double.parseDouble(amountStr);
			SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
			int userId = settings.getInt("userId", -1);
	
			RequestJSON requestObj = new RequestJSON();
			requestObj.setAction("withdrawal");
			requestObj.setAmount(amount);
			requestObj.setUserId(userId);
	
			NetworkingTask task = new NetworkingTask(this);
			task.execute(requestObj);
		}
	}
	
	@Override
	public void showResult(ResponseJSON response) {
		String message;
		Resources res = getResources();
		
		if(!response.isSuccess()){
			message = response.getMessage();
		}else{
			message = String.format(res.getString(R.string.money_withdrawed), amount);
		}
		
		goToScreen(R.id.main_menu, message);
	}

}
