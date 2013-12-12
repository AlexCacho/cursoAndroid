package workspace.tema02_registro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Estudios extends Activity {
	Bundle bundleDatos;
	RadioButton rb1, rb2;
	RadioGroup rg;
	Spinner combo;
	Button botonEnviar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_estudios);

		bundleDatos = this.getIntent().getExtras();
		combo = (Spinner) findViewById(R.id.spinner1);
		botonEnviar = (Button) findViewById(R.id.button1);

		rg = (RadioGroup) findViewById(R.id.radioGroup1);

//		 ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_style,R.array.estudios);
		 
		// OPCION 1: Usar XML para los elementos del array
		// Creo el adaptador desde el XML que esta en res/values
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
				this, // Esta clase
				R.array.estudios, // De donde tomamos el array
				R.layout.spinner_style);
//				android.R.layout.simple_spinner_dropdown_item); // Como lo vamos
																// a mostrar

		// Decimos como se va abrir el combo
		adaptador
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Asignar adaptador al combo
		combo.setAdapter(adaptador);
		
//		rg.getCheckedRadioButtonId();
		
//		combo.setEnabled(false);
		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup grupo, int i) {
//				for (int j = 0; j < grupo.getChildCount(); j++) {
//					if (grupo.getChildAt(j).getId() == i){
//						if(j==0) combo.setEnabled(true);
//						else combo.setEnabled(false);
//						
//					}
//				}
				RadioButton rb = (RadioButton) findViewById (rg.getCheckedRadioButtonId());
				if(rb.getText().toString()== getResources().getString(R.string.str_superiores)) // "Superiores"
					combo.setEnabled(true);
				else combo.setEnabled(false);

			}
		});

		botonEnviar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent conexion = new Intent(Estudios.this, Fin.class);

				if (combo.isEnabled()) {
					bundleDatos.putString("Estudios", combo.getSelectedItem()
							.toString());
				}

				else
					bundleDatos.putString("Estudios", "Inferiores");

				conexion.putExtras(bundleDatos);
				startActivity(conexion);
			}

		});

	}

}
