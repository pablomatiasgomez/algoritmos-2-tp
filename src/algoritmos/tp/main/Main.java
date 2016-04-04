package algoritmos.tp.main;

import java.util.List;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Library;
import algoritmos.tp.impl.LibraryImpleTrucha;

public class Main {
	public static void main(String[] args) {
		
		Library lib = new LibraryImpleTrucha();
		List<Filter> filtros = lib.getFilters();

		for (Filter f : filtros) {
			List<Label> labels = f.getLabels();
		}

	}
}
