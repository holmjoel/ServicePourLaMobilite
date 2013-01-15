package fr.isep.mobilemoney;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import fr.isep.jsonobjects.ResponseJSON;

public abstract class MoneyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle b = getIntent().getExtras();
		if (b != null){
			showDialog("Result", b.getString("message"));
		}
	}
	
	public void goToScreen(int id){
		goToScreen(id, null);
	}
	
	public void goToScreen(int id, String message) {
		Intent activityIntent;
		
		switch (id) {
			
			case R.id.send_money:
				activityIntent = new Intent(this, SendMoneyActivity.class);
				break;
			
			case R.id.request_money:
				activityIntent = new Intent(this, RequestMoneyActivity.class);
				break;
			
			case R.id.accept_request:
				activityIntent = new Intent(this, AcceptRequestActivity.class);
				break;
				
			case R.id.history:
				activityIntent = new Intent(this, AccountHistory.class);
				break;
			
			case R.id.log_out:
				activityIntent = new Intent(this, LoginActivity.class);
				break;
				
			case R.id.main_menu:
			default:
				activityIntent = new Intent(this, MainActivity.class);
				break;
		}
		
		if(message != null){
			//add message to intent
			activityIntent.putExtra("message", message);
		}
		this.startActivity(activityIntent);
	}
	
	public void onClick(View clickedButton){
		goToScreen(clickedButton.getId(), null);
	}
	
	protected void showDialog(String title, String message){
		   new AlertDialog.Builder(this)
		      .setMessage(message)
		      .setTitle(title)
		      .setCancelable(true)
		      .setNeutralButton(android.R.string.ok,
		         new DialogInterface.OnClickListener() {
		         public void onClick(DialogInterface dialog, int whichButton){}
		         })
		      .show();
	}
	
	public abstract void showResult(ResponseJSON response);

}
