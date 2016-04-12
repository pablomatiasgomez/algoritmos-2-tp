package model;

import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Library;
import algoritmos.tp.def.Title;

public class LibraryImpl implements Library {

	private List<Title> titles;
	private List<Filter> filters;

	@Override
	public List<Title> getTitles() {
		return this.titles;
	}

	@Override
	public List<Filter> getFilters() {
		return this.filters;
	}

	@Override
	public Filter getFilter(String filterName) {
		return this.filters.stream().filter(filter -> filter.getName().equals(filterName)).findAny().orElse(null);
	}

	@Override
	public List<Label> getLabels(Filter filter) {
		return filter.getLabels();
	}

	@Override
	public Label getLabel(Filter filter, String labelName) {
		return filter.getLabels().stream().filter(label -> label.getName().equals(labelName)).findAny().orElse(null);
	}

	// TODO tiene sentido que venga fitler aca...?
	@Override
	public List<Title> getTitles(Filter filter, Label label) {
		return label.getTitles();
	}

}
