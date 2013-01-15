package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class LoginActivity extends MoneyActivity {

	private EditText mMobileOrAlias, mPin;
	private String mobileOrAlias, pin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		mMobileOrAlias = (EditText) findViewById(R.id.number_or_alias);
		mPin = (EditText) findViewById(R.id.pin);
	}

	public void login(View clickedButton) {
		this.mobileOrAlias = mMobileOrAlias.getText().toString();
		this.pin = mPin.getText().toString();
		
		Resources res = getResources();

		if (pin.isEmpty() || mobileOrAlias.isEmpty()) {
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_empty));
		} else if (pin.length() != 4){
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_pin_length));
		} else {
			RequestJSON requestObj = new RequestJSON();
			requestObj.setAction("sendMoney");
			requestObj.setNumberOrAlias(mobileOrAlias);
			requestObj.setPin(pin);

			NetworkingTask task = new NetworkingTask(this);
			task.execute(requestObj);
		}
	}

	public void showResult(ResponseJSON response) {

		String message;
		Resources res = getResources();

		if (!response.isSuccess()) {
			message = response.getMessage();
			showDialog(res.getString(R.string.login_unsuccessful), message);
		} else {
			SharedPreferences settings = getPreferences(MODE_PRIVATE);
		    SharedPreferences.Editor editor = settings.edit();
		    editor.putInt("userId", (int) response.getTransactionNumber());
		    editor.commit();
		    
		    message = String.format(res.getString(R.string.welcome), mobileOrAlias);
			goToScreen(R.id.main_menu, message);
		}
	}

}