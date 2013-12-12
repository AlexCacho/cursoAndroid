package workspace.tema02_registro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Localidad extends Activity {

	Bundle bundleDatos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_localidad);

//		final TextView titulo;
		final Spinner combo;
		final Button botonEnviar;
		bundleDatos = this.getIntent().getExtras();

		combo = (Spinner) findViewById(R.id.spinner1);
		botonEnviar = (Button) findViewById(R.id.button1);

		// OPCION 1: Usar XML para los elementos del array
		// Creo el adaptador desde el XML que esta en res/values
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
				this, // Esta clase
				R.array.localidades, // De donde tomamos el array
				android.R.layout.simple_spinner_dropdown_item); // Como lo vamos
																// a mostrar

		// Decimos como se va abrir el combo
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Asignar adaptador al combo
		combo.setAdapter(adaptador);

		// Implementamos el listener
		combo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> fuente, View vista,
					int posicion, long id) {
				// titulo.setText(combo.getSelectedItem().toString());
				bundleDatos.putString("Localidad", fuente.getItemAtPosition(posicion).toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		botonEnviar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent conexion = new Intent(Localidad.this, Estudios.class);
				conexion.putExtras(bundleDatos);
				startActivity(conexion);
			}

		});

	}

}
