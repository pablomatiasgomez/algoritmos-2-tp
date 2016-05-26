package algoritmos.tp.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import algoritmos.tp.def.Filter;
import algoritmos.tp.def.Label;
import algoritmos.tp.def.Library;
import algoritmos.tp.def.Title;

public class LibraryImpl implements Library {

	private List<Title> titles;
	private Set<Filter> filters;
	private Set<Label> labels;
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
		this.filters = new HashSet<Filter>();
		this.labels = new HashSet<Label>();

		this.config = new Config("config.xml");

		this.searchTitles(this.config.getAlbumsPath());
	}

	@Override
	public List<Title> getTitles() {
		return this.titles;
	}

	@Override
	public List<Filter> getFilters() {
		return new ArrayList<>(this.filters);
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

	public List<Title> searchTitles(String path) {

		File[] folders = new File(path).listFiles();

		List<Title> titles = Stream.of(folders).flatMap(folder -> {
			if (folder.isDirectory()) {
				return Stream.of(folder.listFiles());
			}
			return Stream.empty();
		}).filter(File::isDirectory).map(folder -> {
			File titleInfoFile = this.getTilteInfoFile(folder);
			if (titleInfoFile != null) {
				return this.createTitle(folder.getName(), folder.getAbsolutePath(), titleInfoFile);
			}
			return null;
		}).filter(Objects::nonNull).collect(Collectors.toList());

		return titles;
	}

	private Title createTitle(String name, String path, File titleInfoFile) {
		Hashtable<Filter, List<Label>> attributes = this.processInfoFile(titleInfoFile);
		Title title = new TitleImpl(name, path, attributes);
		attributes.values().forEach(labelList -> labelList.forEach(label -> {label.addTitle(title);}));
		this.titles.add(title);
		return title;
	}

	private Hashtable<Filter, List<Label>> processInfoFile(File infoFile)  {
		Scanner sc = this.getScanner(infoFile);
		Hashtable<Filter, List<Label>> attributes = new Hashtable<Filter, List<Label>>();

		while (sc.hasNextLine()) {

			String line = sc.nextLine();
			if (line.isEmpty()) {
				continue;
			}

			String[] split = line.split("=", 2);
			if (split.length < 2) {
				continue;
			}
			String filterName = split[0];
			String[] labelNames= split[1].split(String.format("\\%s", this.config.getLabelDivider()));

			List<Label> labels = Stream.of(labelNames).map(labelName -> this.createLabel(labelName)).collect(Collectors.toList());
			Filter filter = this.createFilter(filterName, labels);
			attributes.put(filter, labels);
		}
		sc.close();
		return attributes;
	}

	private Filter createFilter(String filterName, List<Label> labels) {
		Optional<Filter> filter = this.filters
				.stream()
				.filter(f-> f.getName().equals(filterName))
				.findFirst();

		if (filter.isPresent()) {
			filter.get().addLabels(labels);
			return filter.get();
		}
		FilterImpl newFilter = new FilterImpl(filterName, labels);
		this.filters.add(newFilter);
		return newFilter;
	}

	private Label createLabel(String labelName){
		Optional<Label> label = this.labels
				.stream()
				.filter(l-> l.getName().equals(labelName))
				.findFirst();

		if (label.isPresent()) {
			return label.get();
		}
		LabelImpl newLabel = new LabelImpl(labelName);
		this.labels.add(newLabel);
		return  newLabel;
	}

	private Scanner getScanner(File file) {
		try {
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private File getTilteInfoFile(File folder) {
		File titleInfoFile = Stream.of(folder.listFiles())
				.filter(file -> file.getName().equals(this.config.getInfoFileName()))
				.findAny()
				.orElse(null);

		return titleInfoFile;
	}
}
