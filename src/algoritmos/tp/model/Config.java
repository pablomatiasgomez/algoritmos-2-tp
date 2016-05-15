package algoritmos.tp.model;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

public class Config {

	private Document configFile;
	
	private String albumsPath;
	private String labelDivider;
	
	public Config(String configPath) {
		configFile = setConfigFile(configPath);
	}

	public String getAlbumsPath() {
		if (albumsPath == null) {
			albumsPath = setAlbumsPath();
		}	
		return albumsPath;
	}

	private String setAlbumsPath() {
		return configFile.getElementsByTagName("albums-path").item(0).getTextContent();
	}

	public String getLabelDivider() {
		if (labelDivider == null) {
			labelDivider = setLabelDivider();
		}	
		return labelDivider;
	}
	
	private String setLabelDivider() {
		return configFile.getElementsByTagName("divider").item(0).getTextContent();
	}

	private Document setConfigFile(String configPath) {
		try {
			File inputFile = new File(configPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			return dBuilder.parse(inputFile);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
}
