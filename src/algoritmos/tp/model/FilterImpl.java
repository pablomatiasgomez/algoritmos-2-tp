package algoritmos.tp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;

public class FilterImpl implements Filter {

	private String name;
	private Set<Label> labels;

	public FilterImpl(String name, List<Label> labels) {
		this.name = name;
		this.labels = new HashSet<>(labels);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public List<Label> getLabels() {
		return new ArrayList<>(this.labels);
	}

	public void addLabels(List<Label> labels){
		this.labels.addAll(labels);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		FilterImpl other = (FilterImpl) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

}
