package algoritmos.tp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
	private List<Label> labels;
	private Config config;

	private static LibraryImpl instance;
	
	public static synchronized LibraryImpl getInstance() {
		if (instance == null) {
			instance = new LibraryImpl();
		}
		return instance;
	}
	
	private LibraryImpl() {
		this.titles = new ArrayList<Title>();
		this.filters = new ArrayList<Filter>();
		this.labels = new ArrayList<Label>();

		config = new Config("config.xml");
		
		buscarTitulos(config.getAlbumsPath());
		
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
		Title title = new TitleImpl(name, path, attributes);
		attributes.values().forEach(labelList -> labelList.forEach(label -> {label.addTitle(title);}));
		this.titles.add(title);
		return title;
	}

	private Hashtable<Filter, List<Label>> procesarInfoFile(File infoFile)  {
		Scanner sc = this.getScanner(infoFile);
		Hashtable<Filter, List<Label>> info = new Hashtable<Filter, List<Label>>();
		
		while (sc.hasNextLine()) {
						
			String line = sc.nextLine();
			if (line.isEmpty()) break;// Algunos archivos JML tienen lineas vacias al final

			String[] split = line.split("="); // FIXME Algunos titulos tienen "=" en el nombre
			if (split.length < 2) break ;// Algunos Filters no tienen Label asociadas
			String filterName = split[0];
			String[] labelNames= split[1].split( String.format("\\%s", config.getLabelDivider()));
			
			
			List<Label> labels = Stream.of(labelNames).map(labelName -> createLabel(labelName)).collect(Collectors.toList());
			Filter filter = createFilter(filterName, labels);
			info.put(filter, labels);
		}
		sc.close();
		return info;
	}
	
	private Filter createFilter(String filterName, List<Label> labels) {
		Optional<Filter> filter = this.filters
			    .stream()
			    .filter((f)-> f.getName().equals((filterName)))
			    .findFirst();
		if (filter.isPresent()) {
			((Filter) filter.get()).addLabels(labels);
			return (Filter) filter.get();
		}
		FilterImpl new_filter = new FilterImpl(filterName, labels);
		this.filters.add(new_filter);
		return new_filter;
	}

	private Label createLabel(String labelName){
		Optional<Label> label = this.labels
			    .stream()
			    .filter((l)-> l.getName().equals((labelName)))
			    .findFirst();
		if (label.isPresent()) {
			return (Label) label.get();
		}
		LabelImpl new_label = new LabelImpl(labelName);
		this.labels.add(new_label);
		return  new_label;
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
