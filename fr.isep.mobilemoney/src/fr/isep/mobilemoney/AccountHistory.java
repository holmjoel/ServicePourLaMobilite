package fr.isep.mobilemoney;

import java.util.List;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.jsonobjects.Transaction;
import fr.isep.networking.NetworkingTask;

public class AccountHistory extends MoneyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_history);
		this.getAccountHistory();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_account_history, menu);
		return true;
	}
	
	private void getAccountHistory(){
		
		
		RequestJSON requestObj = new RequestJSON();
		
		SharedPreferences settings = getSharedPreferences("user_data", MODE_PRIVATE);
		int userId = settings.getInt("userId", -1);
		
		requestObj.setAction("getTransactions");
		requestObj.setUserId(userId);
		
		NetworkingTask task = new NetworkingTask(this);
		task.execute(requestObj);
		
		
		
		
		
		
	}

	@Override
	public void showResult(ResponseJSON response) {
		List<Transaction> listOfTransactions = response.getListOfTransactions();
		
		ListView listView = (ListView) findViewById(R.id.transactions);
		

		// Define a new Adapter
		// First parameter - Context
		// Second parameter - Layout for the row
		// Third parameter - ID of the TextView to which the data is written
		// Forth - the Array of data

		String[] transactionsArray = new String[listOfTransactions.size()];
		for(int i = 0; i < listOfTransactions.size(); i++){
			transactionsArray[i] = listOfTransactions.get(i).toString();
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		  android.R.layout.simple_list_item_1, android.R.id.text1, transactionsArray);

		// Assign adapter to ListView
		listView.setAdapter(adapter); 
		
		//Set balance
		TextView balance = (TextView) this.findViewById(R.id.accountBalance);
		balance.setText("Account balance: " + response.getBalance());
		
		
	}

}
