package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class Recharge extends MoneyActivity {
	
	private double amount;
	
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
	
	public void recharge(View clickedButton){
		switch (clickedButton.getId()) {
		
		case R.id.money20:
			amount = 20;
			break;
			
		case R.id.money40:
			amount = 40;
			break;
			
		case R.id.money60:
			amount = 60;
			break;
			
		case R.id.money80:
			amount = 80;
			break;
			
		case R.id.money100:
			amount = 100;
			break;
			
		default:
			amount = 0;
			break;
		}
		
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		int userId = settings.getInt("userId", -1);

		RequestJSON requestObj = new RequestJSON();
		requestObj.setAction("recharge");
		requestObj.setAmount(amount);
		requestObj.setUserId(userId);

		NetworkingTask task = new NetworkingTask(this);
		task.execute(requestObj);
	}
	
	@Override
	public void showResult(ResponseJSON response) {
		String message;
		Resources res = getResources();
		
		if(!response.isSuccess()){
			message = response.getMessage();
		}else{
			message = String.format(res.getString(R.string.money_recharged), amount);
		}
		
		goToScreen(R.id.main_menu, message);
	}

}
