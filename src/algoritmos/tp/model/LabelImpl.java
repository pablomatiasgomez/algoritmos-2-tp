package algoritmos.tp.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import algoritmos.tp.def.Label;
import algoritmos.tp.def.Title;

public class LabelImpl implements Label {

	private String name;
	private Set<Title> titles;
	private Set<Label> sublabels;

	public LabelImpl(String name) {
		this.titles = new HashSet<Title>();
		this.name = name;
	}

	@Override
	public void addTitle(Title title){
		this.titles.add(title);
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
		return new ArrayList<>(this.titles);
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
