package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class SendMoneyActivity extends MoneyActivity {

	private EditText mTargetUser, mAmount;
	private String targetUser;
	private double amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_money);

		mTargetUser = (EditText) findViewById(R.id.number_or_alias);
		mAmount = (EditText) findViewById(R.id.amount);
		
		SharedPreferences settings = getPreferences(MODE_PRIVATE);
	    SharedPreferences.Editor editor = settings.edit();
	    editor.putInt("userId", 1);
	    editor.commit();
	}

	public void sendMoney(View clickedButton) {
		this.targetUser = mTargetUser.getText().toString();
		String amountStr = mAmount.getText().toString();

		if (amountStr.isEmpty() || targetUser.isEmpty()) {
			Resources res = getResources();
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_empty));
		} else {
			this.amount = Double.parseDouble(amountStr);

			SharedPreferences settings = getPreferences(MODE_PRIVATE);
			int userId = settings.getInt("userId", -1);

			RequestJSON requestObj = new RequestJSON();
			requestObj.setAction("sendMoney");
			requestObj.setNumberOrAlias(targetUser);
			requestObj.setAmount(amount);
			requestObj.setUserId(userId);

			NetworkingTask task = new NetworkingTask(this);
			task.execute(requestObj);
		}
	}

	public void showResult(ResponseJSON response) {

		String message;
		Resources res = getResources();

		if (!response.isSuccess()) {
			message = response.getMessage();
		} else if (response.isUserExisted()) {
			String moneySent = String.format(
					res.getString(R.string.money_sent), amount, targetUser);
			String newBalance = String.format(
					res.getString(R.string.current_balance),
					response.getBalance());
			message = moneySent + " " + newBalance;
		} else {
			String moneySent = String.format(
					res.getString(R.string.money_sent_new_user), amount,
					targetUser);
			String newBalance = String.format(
					res.getString(R.string.current_balance),
					response.getBalance());
			message = moneySent + " " + newBalance;
		}

		goToScreen(R.id.main_menu, message);
	}

}