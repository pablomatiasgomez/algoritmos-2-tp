package algoritmos.tp.model;

import java.util.Hashtable;
import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class TitleImpl implements Title {

	private String name;
	private String path;
	private Hashtable<Filter, List<Label>> atts;

	// TODO check if necessary.
	public TitleImpl() {
	}

	public TitleImpl(String name, String path, Hashtable<Filter, List<Label>> atts) {
		this.name = name;
		this.path = path;
		this.atts = atts;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getPath() {
		return this.path;
	}

	@Override
	public Hashtable<Filter, List<Label>> getAtts() {
		return this.atts;
	}

}
