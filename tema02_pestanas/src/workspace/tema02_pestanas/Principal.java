package workspace.tema02_pestanas;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Principal extends Activity {

	private TextView etiqGrid;
	private GridView gridOpciones;
	// private String [] alumnos;

	Titular[] datosLista;
	String opcionSeleccionada;
	TextView etiqueta;

	EditText inputUsuario, inputClave;
	Button botonLogueo, botonEnviarLista;
	TextView etiqLogueo, etiqTitulo;

	String statusAccesoOK = "Acceso correcto";
	String statusAccesoKO = "Acceso prohibido";

	Spannable seleccion;

	Bundle bundle;
	
	TabHost gestorTabs;
	TabSpec pestana;
	
	String localidadElegida;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		datosLista = new Titular [getResources().getStringArray(R.array.localidades).length];
		
		for (int i = 0; i < getResources().getStringArray(R.array.localidades).length; i++) {
			datosLista[i] = new Titular (getResources().getStringArray(R.array.localidades)[i].split(",")[0],getResources().getStringArray(R.array.localidades)[i].split(",")[1]);
//			datosLista[i].setTitulo(getResources().getStringArray(R.array.localidades)[i].split(",")[0]);
//			datosLista[i].setSubtitulo(getResources().getStringArray(R.array.localidades)[i].split(",")[1]);
		}
		

		bundle = new Bundle();
		Resources recursos = getResources();
		
		gestorTabs = (TabHost) findViewById(android.R.id.tabhost);
		
		gestorTabs.setup();
		
		pestana = gestorTabs.newTabSpec("Pestana 1");
		pestana.setContent(R.id.tab1);
		pestana.setIndicator("Login", recursos.getDrawable(android.R.drawable.ic_dialog_alert));
		gestorTabs.addTab(pestana);
		
		pestana = gestorTabs.newTabSpec("Pestana 2");
		pestana.setContent(R.id.tab2);
		pestana.setIndicator("Localidad", recursos.getDrawable(android.R.drawable.stat_notify_sync));
		gestorTabs.addTab(pestana);
		
		pestana = gestorTabs.newTabSpec("Pestana 3");
		pestana.setContent(R.id.tab3);
		pestana.setIndicator("Curso", recursos.getDrawable(android.R.drawable.stat_notify_sync));
		gestorTabs.addTab(pestana);
		
//		Puedo indicar opcionalmente la pestana predeterminada
//		gestorTabs.setCurrentTab(0);
		
//		gestorTabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {	
//			@Override
//			public void onTabChanged(String arg0) {
//				Log.i("PESTANA", "Has pulsado la pestana " + arg0);
//				
//			}
//		});
		
		
		
		// Tab Grid
		etiqGrid = (TextView) findViewById(R.id.textView1);
		gridOpciones = (GridView) findViewById(R.id.gridView1);
		// Meto los datos en el Grid
		ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
				this, R.array.cursos, android.R.layout.simple_list_item_1);

		gridOpciones.setAdapter(adaptador);

		gridOpciones
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int pos, long arg3) {
						// etiqGrid.setText(alumnos[pos]);
						bundle.putString("Curso", arg0.getItemAtPosition(pos).toString());
						Intent conexion = new Intent(Principal.this, Fin.class);
						conexion.putExtras(bundle);
						startActivity(conexion);

					}
				});

		// Tab Lista

		final ListView lista = (ListView) findViewById(R.id.listView1);
		etiqueta = (TextView) findViewById(R.id.textView1);
		botonEnviarLista = (Button) findViewById(R.id.button1);

		AdaptadorTitulares miAdaptador = new AdaptadorTitulares(this);
		
//		ArrayAdapter<CharSequence> miAdaptador = AdaptadorTitulares.createFromResource(
//				this, R.array.localidades, android.R.layout.simple_list_item_1);


		lista.setAdapter(miAdaptador);
		
		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				// opcionSeleccionada = ((Titular)
				localidadElegida = ((Titular)arg0.getAdapter().getItem(arg2)).getTitulo().toString();

				// Opcion 2: A traves de la vista
//				opcionSeleccionada = ((TextView) arg1.findViewById(R.id.titulo))
//						.getText().toString();
//				etiqueta.setText(opcionSeleccionada);
				
			}
		});
		
		
		
		botonEnviarLista.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				bundle.putString("Localidad", localidadElegida);
				
			}
		});
		
		
//		lista.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				// Opcion 1: Usar el metodo getAdapter
//				// opcionSeleccionada = ((Titular)
//				// arg0.getAdapter().getItem(arg2)).getTitulo().toString();
//
//				// Opcion 2: A traves de la vista
//				opcionSeleccionada = ((TextView) arg1.findViewById(R.id.titulo))
//						.getText().toString();
//				etiqueta.setText(opcionSeleccionada);
//
//			}
//		});

		// Tab login

		inputUsuario = (EditText) findViewById(R.id.inputUser);
		inputClave = (EditText) findViewById(R.id.inputPass);
		botonLogueo = (Button) findViewById(R.id.botonLogueo);
		etiqLogueo = (TextView) findViewById(R.id.etiqLogueado);
		etiqTitulo = (TextView) findViewById(R.id.textView1);

		etiqLogueo.setVisibility(View.GONE);

		// Cambiamos el color del texto seleccionado
		seleccion = inputUsuario.getText();

		botonLogueo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				String usuario = inputUsuario.getText().toString();
				String clave = inputClave.getText().toString();

				// Llamada a base de datos para sacar la informacion

				// Control de acceso

				if (usuario.equals("Alex") && clave.equals("pass")) {
					etiqLogueo.setVisibility(View.GONE);

					bundle.putString("Id", inputUsuario.getText().toString());
					bundle.putString("Pass", inputClave.getText().toString());

				}

				else {

					etiqLogueo.setText(getResources().getString(
							R.string.str_acceso_ko));
					etiqLogueo.setTextColor(Color.RED);

					etiqLogueo.setVisibility(View.VISIBLE);

				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.principal, menu);
		return true;
	}

	class AdaptadorTitulares extends ArrayAdapter<Titular> {

		Activity context;

		public AdaptadorTitulares(Activity context) {
			super(context, R.layout.elemento_titular, datosLista);
//			super(context, R.array.localidades, R.layout.elemento_titular);
			this.context = context;
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View vista = convertView;
			ViewHolder gestorPunteros;

			if (vista == null) {
				LayoutInflater inflater = context.getLayoutInflater();
				vista = inflater.inflate(R.layout.elemento_titular, null);

				gestorPunteros = new ViewHolder();
				gestorPunteros.titulo = (TextView) vista
						.findViewById(R.id.titulo);
				gestorPunteros.subtitulo = (TextView) vista
						.findViewById(R.id.subtitulo);

				vista.setTag(gestorPunteros);
			}

			else {
				gestorPunteros = (ViewHolder) vista.getTag();
			}
			
			String local[]=context.getResources().getStringArray(R.array.localidades);
			
			
//			gestorPunteros.titulo.setText("yeah");
			gestorPunteros.titulo.setText(datosLista[position].getTitulo());
			

//			gestorPunteros.subtitulo.setText("jar");
			gestorPunteros.subtitulo.setText(datosLista[position].getSubtitulo());

			// TextView etiqTitulo = (TextView) vista.findViewById(R.id.titulo);
			// TextView etiqSubtitulo = (TextView)
			// vista.findViewById(R.id.subtitulo);

			// etiqTitulo.setText(datosLista[position].getTitulo());
			// etiqSubtitulo.setText(datosLista[position].getSubtitulo());

//			if (position % 2 == 0) {
//				vista.setBackgroundColor(0x2200AA00);
//				// etiqSubtitulo.setBackgroundColor(color.BlancoSemi);
//			}
			// else etiqTitulo.setBackgroundResource(R.color.Marron);

			return (vista);
			// return super.getView(position, convertView, parent);
		}

	}

	// Con esta clase optimizamos el uso de los "punteros" en la aplicacion
	static class ViewHolder {
		TextView titulo, subtitulo;

	}

}
