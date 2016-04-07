package algoritmos.tp.impl;

import java.util.List;

import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class LabelImpleTrucha implements Label {
    
	private String name;

	public LabelImpleTrucha(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Title> getTitles() {
	    // TODO
		return null;
	}

	@Override
	public List<Label> getSublabels() {
		// TODO Auto-generated method stub
		return null;
	}

}
