package algoritmos.tp.main;

import algoritmos.tp.def.Library;
import algoritmos.tp.model.LibraryImpl;

public class Main {
	public static void main(String[] args) {

		Library lib = LibraryImpl.getInstance();
		lib.getTitles().stream().forEach(title -> {
			System.out.println(title.getName());
		});

	}
}
