package algoritmos.tp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Library;
import algoritmos.tp.def.Title;

public class LibraryImpl implements Library {

	private List<Title> titles;
	private List<Filter> filters;

	private static LibraryImpl instance;
	
	public static synchronized LibraryImpl getInstance() {
		if (instance == null) {
			instance = new LibraryImpl();
		}
		return instance;
	}
	
	private LibraryImpl() {
		// TODO inicializar todo aca
	}

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

	@Override
	public List<Title> getTitles(Filter filter, Label label) {
		return label.getTitles();
	}
	



	public List<Title> buscarTitulos(String path) {

		File[] folders = new File(path).listFiles();

		List<Title> titles = Stream.of(folders).flatMap(folder -> {
			if (folder.isDirectory()) {
				return Stream.of(folder.listFiles());
			}
			return Stream.empty();
		}).filter(File::isDirectory).map(folder -> {
			File titleInfoFile = getTilteInfoFile(folder);
			if (titleInfoFile != null) {
				return crearTitulo(folder.getName(), folder.getAbsolutePath(), titleInfoFile);
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		return titles;
	}

	private Title crearTitulo(String name, String path, File titleInfoFile) {
		// TODO preguntar filter tiene getTitles, el mapa no tendria sentido?
		Hashtable<Filter, List<Label>> attributes = procesarInfoFile(titleInfoFile);
		return new TitleImpl(name, path, attributes);
	}

	private Hashtable<Filter, List<Label>> procesarInfoFile(File infoFile)  {
		Scanner sc = this.getScanner(infoFile);
		
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String[] split = line.split("=");
			String filterName = split[0];
			String[] labelNames= split[1].split("|"); // TODO char de config
			
			List<Label> labels = Stream.of(labelNames).map(LabelImpl::new).collect(Collectors.toList());
			Filter filter = new FilterImpl(filterName, labels); // TODO en verdad estos no son todos los labels de filter, sino solo los que aplican a este disco..
		}
		sc.close();
		// TODO terminar
		return null;
	}
	
	private Scanner getScanner(File file) {
		try {
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getTilteInfoFile(File folder) {
		File titleInfoFile = Stream.of(folder.listFiles()).filter(file -> file.getName().equals("info.jml")) // TODO
																												// info
																												// name
				.findAny().orElse(null);

		return titleInfoFile;
	}

}
