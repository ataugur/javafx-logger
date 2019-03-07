package com.datayaz.javafx.gui.controller;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.LogManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.datayaz.logging.FXScreenHandler;
import com.datayaz.logging.FXScreenHandlerOutputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MainController {

	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
	private Stage mainStage;

	@FXML
	private TextField tfStatus;

	@FXML
	private TextArea taLogs;

	public MainController(Stage primaryStage) {
		this.mainStage = primaryStage;
	}

	@FXML
	private void initialize() {
		Collections.list(LogManager.getLogManager().getLoggerNames()).stream()
				.map(loggerName -> LogManager.getLogManager().getLogger(loggerName).getHandlers())
				.map(array -> Arrays.asList(array)).flatMap(list -> list.stream()).distinct()
				.filter(handler -> handler instanceof FXScreenHandler).forEach(handler -> ((FXScreenHandler) handler)
						.setOutputStream(new FXScreenHandlerOutputStream(taLogs)));

		LOG.info("UI initiliazed");
	}

	public void clearLog(ActionEvent ae) {
		taLogs.setText("");
	}

	public void doLoad(ActionEvent ae) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("MS Excel 2003,2007,2013..", "*.xls", "*.xlsx"),
				new ExtensionFilter("All Files", "*"));
		File selectedFile = fileChooser.showOpenDialog(mainStage);
		if (selectedFile != null) {
			LOG.info("{} file selected for parsing", selectedFile.getAbsolutePath());

			tfStatus.setText(String.format("Input file %s parsed", selectedFile.getName()));
			
			LOG.info("{} file parsed", selectedFile.getAbsolutePath());
		}
	}

}
