package algoritmos.tp.model;

import java.util.List;

import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class LabelImpl implements Label {

	private String name;
	private List<Title> titles;
	private List<Label> sublabels;

	public LabelImpl(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<Title> getTitles() {
		return this.titles;
	}

	@Override
	public List<Label> getSublabels() {
		return this.sublabels;
	}

}