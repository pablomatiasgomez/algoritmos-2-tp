package algoritmos.tp.def;

import java.util.List;

public interface Filter {

	public String getName();

	public List<Label> getLabels();

	public void addLabels(List<Label> labels);

}
