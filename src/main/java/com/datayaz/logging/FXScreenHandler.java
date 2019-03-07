package com.datayaz.logging;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class FXScreenHandler extends StreamHandler {

	public void setOutputStream(FXScreenHandlerOutputStream out) {
		super.setOutputStream(out);
	}

	@Override
	public void publish(LogRecord record) {
		super.publish(record);
		flush();
	}

	@Override
	public void close() {
		flush();
	}
	
}
