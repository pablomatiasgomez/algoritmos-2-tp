package algoritmos.tp.main;

import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Library;
import algoritmos.tp.model.LibraryImpl;

public class Main {
	public static void main(String[] args) {
		
		Library lib = new LibraryImpl();
		List<Filter> filtros = lib.getFilters();

		for (Filter f : filtros) {
			List<Label> labels = f.getLabels();
		}

	}
}
