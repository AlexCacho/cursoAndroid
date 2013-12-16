package workspace.tema02_tabs;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Principal extends Activity {
	TabHost gestorTabs;
	TabSpec pestana;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		Resources recursos = getResources();
		
		gestorTabs = (TabHost) findViewById(android.R.id.tabhost);
		
		gestorTabs.setup();
		
		pestana = gestorTabs.newTabSpec("Pestana 1");
		pestana.setContent(R.id.tab1);
		pestana.setIndicator("", recursos.getDrawable(android.R.drawable.ic_dialog_alert));
		gestorTabs.addTab(pestana);
		
		pestana = gestorTabs.newTabSpec("Pestana 2");
		pestana.setContent(R.id.tab2);
		pestana.setIndicator("", recursos.getDrawable(android.R.drawable.stat_notify_sync));
		gestorTabs.addTab(pestana);
		
//		Puedo indicar opcionalmente la pestana predeterminada
		gestorTabs.setCurrentTab(1);
		
		gestorTabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {	
			@Override
			public void onTabChanged(String arg0) {
				Log.i("PESTANA", "Has pulsado la pestana " + arg0);
				
			}
		});
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

}
