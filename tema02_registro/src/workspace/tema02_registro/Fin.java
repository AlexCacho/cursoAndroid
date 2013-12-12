package workspace.tema02_registro;

import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fin extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fin);

		final TextView user, pass, localidad, estudios;

		user = (TextView) findViewById(R.id.textView2f);
		pass = (TextView) findViewById(R.id.textView4f);
		localidad = (TextView) findViewById(R.id.textView6f);
		estudios = (TextView) findViewById(R.id.textView8f);

		final Bundle bundleDatos = this.getIntent().getExtras();

		user.setText(bundleDatos.getString("Id"));
		pass.setText(bundleDatos.getString("Pass"));
		localidad.setText(bundleDatos.getString("Localidad"));
		estudios.setText(bundleDatos.getString("Estudios"));

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int action = MotionEventCompat.getActionMasked(event);

		// Toast.makeText(this, "Jarl", Toast.LENGTH_SHORT).show();

		Toast toast;
		// View view =
		// getWindow().getDecorView().findViewById(android.R.id.content);

		RelativeLayout view = (RelativeLayout) findViewById(R.id.RelativeLayout1);

		// .setGravity(Gravity.TOP|Gravity.LEFT, 0, 0)

		switch (action) {
		case (MotionEvent.ACTION_DOWN):
			toast = Toast.makeText(this, "Has apretao", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();

			// imageview= (ImageView)findViewById(R.id.);
			// Drawable res = getResources().getDrawable(imageResource);
			// imageView.setImageDrawable(res);

			return true;
		case (MotionEvent.ACTION_MOVE):

			view.setBackgroundResource(R.drawable.android1);

			PointF mv = new PointF(event.getX(), event.getY());
			view.setX((int) (mv.x - (view.getWidth() / 2)));
			view.setY((int) (mv.y - (view.getHeight() / 2)));
			// view.setX((int)(StartPT.x+mv.x-480));
			// view.setY((int)(StartPT.y+mv.y-480));

			return true;

		case (MotionEvent.ACTION_UP):
			toast = Toast.makeText(this, "Has soltao", Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
			toast.show();

			// imageview = getResources().getDrawable(R.drawable.android1);
			view.setBackground(getResources().getDrawable(
					R.drawable.background2));
			// setContentView(view);

			return true;
		default:
			return super.onTouchEvent(event);
		}
	}

}
