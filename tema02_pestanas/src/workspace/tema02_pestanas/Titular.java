package workspace.tema02_pestanas;

public class Titular {

	private String titulo, subtitulo;

	public Titular (String nuevoTitulo, String nuevoSubtitulo){
		titulo = nuevoTitulo;
		subtitulo = nuevoSubtitulo;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}

	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
