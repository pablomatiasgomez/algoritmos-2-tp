package model;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MediaPlayer {

	private ArrayList<Titulo> titles;
	private ArrayList<Filtro> filtros;
	

	public ArrayList<Titulo> buscarTitulos(String path) {
		// Busco todas las carpetas del path 
		// Para cada carpeta que tenga el archivo info.xxx creo el titulo 
		// Esta creado el metodo para crear un disco a partir del path
		
		return null;
	}

	

	private Titulo crearCancion(String path) {
		Titulo titulo = new Titulo();

		String name = buscarNombreTitulo(path);
		titulo.setName(name);

		agregarCancionesDelDisco(titulo, path);
	

		return titulo;

	}

	private void agregarCancionesDelDisco(Titulo titulo, String path) {
		
		// buscar todos los archivos de la carpeta
		ArrayList<String> archivos = archivosDeLaCarpeta(path);
		// filtrar archivos de extension validas
		ArrayList<String> canciones = archivos.stream()
											  .filter(archivo -> extensionValida(archivo))
											  .collect(Collectors.toCollection(ArrayList::new));
		// agregar las canciones al titulo
		titulo.setCanciones(canciones);
		
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
