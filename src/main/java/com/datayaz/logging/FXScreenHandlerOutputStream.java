package com.datayaz.logging;

import java.io.IOException;
import java.io.OutputStream;

import javafx.scene.control.TextArea;

public class FXScreenHandlerOutputStream extends OutputStream {

	private static final int INITIAL_BUFFER_SIZE = 1024 * 2;
	private static final int MAX_BUFFER_SIZE = 1024 * 8;

	private TextArea outputArea;

	private StringBuilder buffer = new StringBuilder(INITIAL_BUFFER_SIZE);

	public FXScreenHandlerOutputStream(TextArea outputArea) {
		this.outputArea = outputArea;
	}

	@Override
	public void write(int b) throws IOException {
		buffer.append((char) b);
		if (buffer.length() >= MAX_BUFFER_SIZE) {
			flush();
		}
	}

	@Override
	public void flush() {
		outputArea.setText(outputArea.getText() + buffer.toString());
		buffer = new StringBuilder(INITIAL_BUFFER_SIZE);
	}

	public TextArea getOutputArea() {
		return outputArea;
	}

	public void setOutputArea(TextArea outputArea) {
		this.outputArea = outputArea;
	}

}
