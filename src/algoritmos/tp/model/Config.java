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
			this.albumsPath = this.getTextContent("albums-path");
		}
		return this.albumsPath;
	}

	public String getLabelDivider() {
		if (this.labelDivider == null) {
			this.labelDivider = this.getTextContent("divider");
		}
		return this.labelDivider;
	}

	public String getInfoFileName() {
		if (this.infoFileName == null) {
			this.infoFileName = this.getTextContent("info-file-name");
		}
		return this.infoFileName;
	}

	private String getTextContent(String key) {
		return this.configFile.getElementsByTagName(key).item(0).getTextContent();
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
