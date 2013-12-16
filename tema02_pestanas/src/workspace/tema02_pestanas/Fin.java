package workspace.tema02_pestanas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Fin extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fin);
		
		final Bundle bundle = this.getIntent().getExtras();
		
		((TextView) findViewById(R.id.textView2)).setText(bundle.getString("Id"));
		
		((TextView) findViewById(R.id.textView4)).setText(bundle.getString("Pass"));
		
		((TextView) findViewById(R.id.textView6)).setText(bundle.getString("Localidad"));
		
		((TextView) findViewById(R.id.textView8)).setText(bundle.getString("Curso"));
		
	}
	
	
}
