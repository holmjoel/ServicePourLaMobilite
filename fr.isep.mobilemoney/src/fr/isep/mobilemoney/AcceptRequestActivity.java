package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class AcceptRequestActivity extends MoneyActivity {

	private EditText mTransaction;
	private long transaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accept_request);
		mTransaction = (EditText) findViewById(R.id.request_number);
	}

	public void acceptRequest(View clickedButton) {
		String transactionStr = mTransaction.getText().toString();

		if (transactionStr.isEmpty()) {
			Resources res = getResources();
			showDialog(res.getString(R.string.invalid_input), res.getString(R.string.invalid_empty));
		} else {
			this.transaction = Long.parseLong(transactionStr);

			SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
			int userId = settings.getInt("userId", -1);

			RequestJSON requestObj = new RequestJSON();
			requestObj.setAction("acceptRequest");
			requestObj.setTransactionNumber(transaction);
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
			message = String.format(res.getString(R.string.money_sent),
					response.getAmount(), response.getMessage());
			String newBalance = String.format(
					res.getString(R.string.current_balance),
					response.getBalance());
			message += " " + newBalance;
		}

		goToScreen(R.id.main_menu, message);
	}
}