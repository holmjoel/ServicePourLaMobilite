package fr.isep.networking;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import fr.isep.jsonobjects.RequestJSON;
import fr.isep.jsonobjects.ResponseJSON;
import fr.isep.mobilemoney.MoneyActivity;
import android.os.AsyncTask;

public class NetworkingTask extends AsyncTask<RequestJSON, Void, ResponseJSON> {

	private final static String URL =

	// "http://10.30.248.148:8080/GR50_MobileMoneyServer/MobileMoneyServer";
	// "http://172.16.250.153:8080/GR50_MobileMoneyServer/MobileMoneyServer";
	"http://192.168.51.103:8080/GR50_MobileMoneyServer/MobileMoneyServer";

	private MoneyActivity caller;

	public NetworkingTask(MoneyActivity caller) {
		this.caller = caller;
	}

	protected ResponseJSON doInBackground(RequestJSON... req) {
		RequestJSON request;
		ResponseJSON response;
		String reqString, resString;
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		request = req[0];
		reqString = gson.toJson(request);

		// TEMPORARILY HARDCODED
		/*
		 * response = new ResponseJSON(); response.setSuccess(true);
		 * response.setBalance(9001); response.setMessage("Success!");
		 * response.setTransactionNumber(1337); response.setAmount(42);
		 * response.setUserExisted(true);
		 * 
		 * if(request.getPin().equals("0000")){ response.setSuccess(false);
		 * response.setMessage("The username or pin number is incorrect."); }
		 */

		try {
			resString = HttpUtils.urlContentPost(URL, "json", reqString);
			response = gson.fromJson(resString, ResponseJSON.class);
		} catch (ClientProtocolException e) {
			response = new ResponseJSON();
			response.setSuccess(false);
			response.setMessage("There was an error connecting to the server. Verify that you are connected to the internet and try again.");
		} catch (IOException e) {
			response = new ResponseJSON();
			response.setSuccess(false);
			response.setMessage("There was an error connecting to the server. Verify that you are connected to the internet and try again.");
		} catch (JsonSyntaxException e) {
			response = new ResponseJSON();
			response.setSuccess(false);
			response.setMessage("I got non-Json from the server");
		}

		return response;
	}

	protected void onPostExecute(ResponseJSON resp) {
		this.caller.showResult(resp);
	}
}
