package com.datayaz.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class DetailedLogFormat extends Formatter {
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss,SSS");

	@Override
	public String format(LogRecord rec) {
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();

		StackTraceElement stackElement = stackTrace[1];

		for (int i = 1; i < stackTrace.length; i++) {
			if (stackTrace[i].getClassName().startsWith("com.datayaz")) {
				stackElement = stackTrace[i];

				break;
			}
		}
		LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(rec.getMillis()), ZoneId.systemDefault());
		String formatlıTarih = dateTime.format(df);

		StringBuilder sb = new StringBuilder(formatlıTarih).append(' ').append(rec.getLevel()).append(" [")
				.append(Thread.currentThread().getName()).append("] ").append(rec.getSourceClassName()).append("#")
				.append(rec.getSourceMethodName()).append(" (").append(stackElement.getFileName()).append(':')
				.append(stackElement.getLineNumber()).append(") ").append(rec.getMessage());

		if (rec.getThrown() != null) {
			sb.append(rec.getThrown().toString());
		}

		sb.append('\n');

		return sb.toString();
	}

}
