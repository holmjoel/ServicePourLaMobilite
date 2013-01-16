package fr.isep.mobilemoney;

import android.os.Bundle;
import android.view.Menu;
import fr.isep.jsonobjects.ResponseJSON;

public class MainActivity extends MoneyActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	public void showResult(ResponseJSON resp){
		//Not applicable
	}

}
