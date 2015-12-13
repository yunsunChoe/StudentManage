
package mobile.example.dbfinalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;


public class OptionActivity extends Activity{
	
		private Switch swVibrate_option;
		Vibrator vibrator;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.options_activity);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		swVibrate_option = (Switch)findViewById(R.id.swVibrate_options);
		swVibrate_option.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton cb, boolean isChecking){
			
			if(isChecking){
				vibrator.vibrate(DEFAULT_KEYS_SHORTCUT);
				Toast.makeText(getApplication(), "진동 On" ,Toast.LENGTH_SHORT).show();
			}
			else{
				vibrator.cancel();
				Toast.makeText(getApplication(), "진동 Off" ,Toast.LENGTH_SHORT).show();
			}
			}
		});
	}
	public void onClick(View v){
		switch(v.getId()){
		case R.id.btnDeveloper_option:
				Intent intent = new Intent(this, DevelopInfo.class);
				startActivity(intent);
				break;
		default:
			break;
			
		}	
	}

}
