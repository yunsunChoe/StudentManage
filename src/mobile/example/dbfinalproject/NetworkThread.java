package mobile.example.dbfinalproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.util.Log;

public class NetworkThread extends Thread {

	public final static int RESULT_OK = 0;
	public final static int RESULT_CANCEL = 1;
	Handler handler;
	String addr;
	
	public NetworkThread(Handler handler, String addr) {
		this.handler = handler;
		this.addr = addr;
	}	
	
	@Override
	public void run() {
		String resultHtml = downloadHtml(addr);
		
		Message msg = new Message(); 
//		Message msg = handler.obtainMessage();
		msg.obj = resultHtml;
		msg.what = RESULT_OK;
		Log.i("wefwefefwfw", "sssssssssseeeeenndddd");
		handler.sendMessage(msg);
	}
	
	String downloadHtml(String addr) { 
		StringBuilder html = new StringBuilder(); 
		try {
			URL url = new URL(addr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			if (conn != null) {
				conn.setConnectTimeout(10000);
				conn.setUseCaches(false);
				if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
					BufferedReader br = new BufferedReader(
							new InputStreamReader(conn.getInputStream()));
					for (;;) {
						String line = br.readLine();
						if (line == null) break;
						Log.i("xml", line);
						html.append(line + '\n'); 
					}
					br.close();
				}
				conn.disconnect();
			}
		} catch (NetworkOnMainThreadException e) {
			return "Error : error happen - " + e.getMessage();
		} catch (Exception e) {
			return "Error : " + e.getMessage();
		}
		return html.toString();
	}
}
