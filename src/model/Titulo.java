package model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class Titulo implements Title{
	
	private String name;
	private ArrayList<String> canciones;
	
	public ArrayList<String> getCanciones() {
		return canciones;
	}
	public void setCanciones(ArrayList<String> canciones) {
		this.canciones = canciones;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Hashtable<Filter, List<Label>> getAtts() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
