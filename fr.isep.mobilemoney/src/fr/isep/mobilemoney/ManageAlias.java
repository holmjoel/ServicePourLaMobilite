package fr.isep.mobilemoney;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import fr.isep.jsonobjects.Constants.MoneyAction;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.networking.NetworkingTask;

public class ManageAlias extends MoneyActivity {
	private EditText alias;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_alias);

		
		alias = (EditText) this.findViewById(R.id.current_alias);

		this.getAlias();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_manage_alias, menu);
		return true;
	}

	private void getAlias() {
		RequestJSON request = new RequestJSON();
		request.setAction("getAlias");
		request.setMoneyAction(MoneyAction.GET_ALIAS);
		
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		int userId = settings.getInt("userId", -1);
		request.setUserId(userId);
		
		NetworkingTask task = new NetworkingTask(this);
		task.execute(request);

	}

	public void updateAlias(View v) {
		RequestJSON request = new RequestJSON();
		request.setAction("manageAlias");
		request.setMoneyAction(MoneyAction.MANAGE_ALIAS);
		
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		int userId = settings.getInt("userId", -1);
		request.setUserId(userId);
		
		String text = this.alias.getText().toString();
		request.setNumberOrAlias(text);
		NetworkingTask task = new NetworkingTask(this);
		task.execute(request);
		
	}

	@Override
	public void showResult(ResponseJSON response) {
		MoneyAction action = response.getMoneyAction();
		if (action == MoneyAction.GET_ALIAS) {
			String alias = response.getMessage();
			if (alias != null && !alias.isEmpty()) {
				this.alias.setText(alias);
			}

		} else if (action == MoneyAction.MANAGE_ALIAS) {
			this.alias.setText(response.getMessage());
			Resources res = this.getResources();
			String message = res.getString(R.string.alias_changed) + response.getMessage();
			super.goToScreen(R.id.main_menu, message);
			
		}

	}

}
