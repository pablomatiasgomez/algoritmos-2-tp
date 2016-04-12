package algoritmos.tp.model;

import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;

public class FilterImpl implements Filter {

	private String name;
	private List<Label> labels;

	public FilterImpl(String name, List<Label> labels) {
		this.name = name;
		this.labels = labels;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Label> getLabels() {
		return this.labels;
	}

}
