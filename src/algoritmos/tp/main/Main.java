package algoritmos.tp.main;

import algoritmos.tp.def.Library;
import algoritmos.tp.model.LibraryImpl;

public class Main {
	public static void main(String[] args) {

		Library lib = LibraryImpl.getInstance();
		lib.getFilters().stream().forEach(filter -> {
			System.out.println(filter.getName());
			filter.getLabels().stream().forEach(label -> {
				System.out.println("\t" + label.getName());
				label.getTitles().stream().forEach(title-> {
					System.out.println("\t\t" +title.getName());
					System.out.println("\t\t" +title.getPath());
				});
			});
		});

	}
}
