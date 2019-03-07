package com.datayaz.javafx;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datayaz.javafx.gui.controller.MainController;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FXSlf4JLogger {

	private static final Logger LOG = LoggerFactory.getLogger(FXSlf4JLogger.class);

	@FXML
	private MainController mainController;

	private Properties properties;

	public FXSlf4JLogger(Properties props) {
		this.properties = props;
	}

	public void start(Stage primaryStage) {

		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				closeProperly();
			}
		});

		LOG.info("App is starting");
		
		openGui(primaryStage);
	}

	private void openGui(Stage primaryStage) {
		try {
			// Parse main screen fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(this.getClass().getResource("/view/bulkLoad.fxml"));

			mainController = new MainController(primaryStage);
			loader.setController(mainController);

			Parent rootLayout = loader.load();
			Scene scene = new Scene(rootLayout);
			scene.getStylesheets().add("/view/css/common.css");

			primaryStage.setTitle("FX Slf4J Logger (v" + properties.getProperty("app.version") + ')');
			primaryStage.getIcons().add(new Image("/view/img/javafx.png"));
			primaryStage.setScene(scene);
			primaryStage.setMaximized(false);
			primaryStage.show();

		} catch (IOException e) {
			LOG.error("Error while starting the app ", e);
		}
	}

	/**
	 * Açık kaynakları düzgün bir şekilde kapatıp öyle uygulamayı sonlandırır
	 */
	private void closeProperly() {

		Platform.exit();
		LOG.info("FX stopped");

		LOG.info("App closing");
		System.exit(1);
	}

}
