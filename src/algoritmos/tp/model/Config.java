package algoritmos.tp.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class Config {

	private Document configFile;

	private String albumsPath;
	private String labelDivider;
	private String infoFileName;

	public Config(String configPath) {
		this.configFile = this.setConfigFile(configPath);
	}

	public String getAlbumsPath() {
		if (this.albumsPath == null) {
			this.albumsPath = this.getAlbumsPathFromConfigFile();
		}
		return this.albumsPath;
	}

	private String getAlbumsPathFromConfigFile() {
		return this.configFile.getElementsByTagName("albums-path").item(0).getTextContent();
	}

	public String getLabelDivider() {
		if (this.labelDivider == null) {
			this.labelDivider = this.getLabelDividerFromConfigFile();
		}
		return this.labelDivider;
	}

	private String getLabelDividerFromConfigFile() {
		return this.configFile.getElementsByTagName("divider").item(0).getTextContent();
	}

	public String getInfoFileName() {
		if (this.infoFileName == null) {
			this.infoFileName = this.getInfoFileNameFromConfigFile();
		}
		return this.infoFileName;
	}

	private String getInfoFileNameFromConfigFile() {
		return this.configFile.getElementsByTagName("info-file-name").item(0).getTextContent();
	}

	private Document setConfigFile(String configPath) {
		try {
			File inputFile = new File(configPath);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			return dBuilder.parse(inputFile);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}


}
