package algoritmos.tp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class LabelImpl implements Label {

	private String name;
	private Set<Label> sublabels;

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
		return LibraryImpl.getInstance().getTitles().stream().filter(title -> {
			return title.getAtts().values().contains(this);
		}).collect(Collectors.toList());
	}

	@Override
	public List<Label> getSublabels() {
		return new ArrayList<>(this.sublabels);
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
		LabelImpl other = (LabelImpl) obj;
		if (this.name == null) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		return true;
	}

}
