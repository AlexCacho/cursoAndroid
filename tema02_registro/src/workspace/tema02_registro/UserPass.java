package workspace.tema02_registro;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserPass extends Activity {	EditText inputUsuario, inputClave;
Button botonLogueo;
TextView etiqLogueo, etiqTitulo;

String statusAccesoOK = "Acceso correcto";
String statusAccesoKO = "Acceso prohibido";

Spannable seleccion;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_user_pass);
	
	
	inputUsuario = (EditText) findViewById(R.id.InputUser);
	inputClave = (EditText) findViewById(R.id.InputPass);
	botonLogueo = (Button) findViewById(R.id.BotonLogueo);
	etiqLogueo = (TextView) findViewById(R.id.EtiquetaLogueado);
	etiqTitulo = (TextView) findViewById(R.id.textView1);
	
	etiqLogueo.setVisibility(View.GONE);
	
	
	//Cambiamos el color del texto seleccionado
	seleccion = inputUsuario.getText();


	botonLogueo.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			
			String usuario = inputUsuario.getText().toString();
			String clave = inputClave.getText().toString();
			
			// Llamada a base de datos para sacar la informacion
			
			// Control de acceso
			
			if (usuario.equals("Alex") && clave.equals("pass")){
				Intent conexion = new Intent(UserPass.this, Localidad.class);
				Bundle array = new Bundle ();
				
				etiqLogueo.setVisibility(View.GONE);
				
				array.putString("Id", inputUsuario.getText().toString());
				array.putString("Pass", inputClave.getText().toString());
				
				conexion.putExtras(array);
				startActivity(conexion);
				
			}
			
			else {
				
				etiqLogueo.setText(getResources().getString(R.string.str_acceso_ko));
				etiqLogueo.setTextColor(Color.RED);
				
				etiqLogueo.setVisibility(View.VISIBLE);
				
			}


		}
	});
}

}
