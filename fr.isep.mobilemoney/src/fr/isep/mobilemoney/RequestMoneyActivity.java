package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class RequestMoneyActivity extends MoneyActivity {

	private EditText mTargetUser, mAmount;
	private String targetUser;
	private double amount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.request_money);

		mTargetUser = (EditText) findViewById(R.id.number_or_alias);
		mAmount = (EditText) findViewById(R.id.amount);
	}

	public void requestMoney(View clickedButton) {

		this.targetUser = mTargetUser.getText().toString();
		String amountStr = mAmount.getText().toString();

		if (amountStr.isEmpty() || targetUser.isEmpty()) {
			Resources res = getResources();
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_empty));
		} else {
			this.amount = Double.parseDouble(amountStr);

			SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
			int userId = settings.getInt("userId", -1);

			RequestJSON requestObj = new RequestJSON();
			requestObj.setAction("requestMoney");
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
		} else {
			message = String.format(res.getString(R.string.money_requested),
					amount, targetUser) + response.getTransactionNumber();
		}

		goToScreen(R.id.main_menu, message);
	}

}