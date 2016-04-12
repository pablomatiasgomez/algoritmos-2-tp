package model;

import java.util.ArrayList;

public class MediaPlayer {

	private ArrayList<Titulo> titles;
	private ArrayList<Filtro> filtros;
	

	public ArrayList<Titulo> buscarTitulos(String path) {
		// Para cada autor, crear un disco por cada subcarpeta
		return null;
	}

	private ArrayList<String> buscarCanciones(String path) {
		return null;
	}

	private Titulo crearCancion(String path) {
		Titulo titulo = new Titulo();

		String name = buscarNombreTitulo(path);
		titulo.setName(name);

		ArrayList<String> canciones = buscarCancionesDelDisco(path);
		titulo.setCanciones(canciones);

		return titulo;

	}

	private ArrayList<String> buscarCancionesDelDisco(String path) {
		// TODO Auto-generated method stub
		// buscar todos los archivos
		ArrayList<String> archivos = archivosDeLaCarpeta(path);

		archivos.stream().filter(archivo -> extensionValida(archivo));

		// filtrar las extensiones validas

		return archivos;
	}

	private boolean extensionValida(String a) {
		// TODO Auto-generated method stub
		return true;
	}

	private ArrayList<String> archivosDeLaCarpeta(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	private String buscarNombreTitulo(String path) {
		// TODO Auto-generated method stub
		return null;
	}

}
