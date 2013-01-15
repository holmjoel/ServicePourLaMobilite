package fr.isep.mobilemoney;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import fr.isep.jsonobjects.ResponseJSON;

public abstract class MoneyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Show message (if applicable)
		Bundle b = getIntent().getExtras();
		if (b != null){
			showDialog("Result", b.getString("message"));
		}
		
		//Check if logged in and redirect if not
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		if (!(settings.contains("userId")) && !(this.getClass().getName().equals("fr.isep.mobilemoney.LoginActivity"))){
		    Resources res = getResources();
		    goToScreen(R.id.login, res.getString(R.string.not_logged_in));
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
			
			case R.id.login:
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
	
	public void logOut(View clickedButton){
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.remove("userId");
	    editor.commit();
	    goToScreen(R.id.login, null);
	}
	
	public void showDialog(String title, String message){
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
