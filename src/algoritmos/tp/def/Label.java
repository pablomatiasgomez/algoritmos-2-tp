package algoritmos.tp.def;

import java.util.List;

public interface Label {
    
	public String getName();

	public List<Title> getTitles();

	public List<Label> getSublabels();
}
