package algoritmos.tp.impl;

import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;

public class FilterImpleTrucha implements Filter {
	private String name;

	public FilterImpleTrucha(String n) {
		name = n;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<Label> getLabels() {
		// TODO
		return null;
	}

}
