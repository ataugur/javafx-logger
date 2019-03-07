package com.datayaz.javafx;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datayaz.util.Utils;

import javafx.application.Application;
import javafx.stage.Stage;

public class Starter extends Application {

	private static final Logger LOG = LoggerFactory.getLogger(Starter.class);
	private static FXSlf4JLogger mainApp;

	public static void main(String[] args) throws IOException {
		initializeLogging();

		Properties props = readProperties("app.properties");

		mainApp = new FXSlf4JLogger(props);
		launch();
	}

	/**
	 * read a properties under conf folder
	 * 
	 * @param propertyFile
	 * @return
	 */
	public static Properties readProperties(String propertyFile) {

		Properties props = new Properties();

		try (InputStreamReader propFileReader = new InputStreamReader(Utils.getConfResource(propertyFile))) {
			props.load(propFileReader);

		} catch (IOException e) {
			LOG.error("Properties file ({}) not found", propertyFile, e.getMessage());
		}
		return props;
	}

	public static void initializeLogging() {
		try (InputStream inputStream = Utils.getConfResource("logging.properties")) {
			LogManager.getLogManager().readConfiguration(inputStream);
		} catch (final IOException e) {
			LOG.error("unable to find logging configuration file logging.properties");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainApp.start(primaryStage);
	}

}
