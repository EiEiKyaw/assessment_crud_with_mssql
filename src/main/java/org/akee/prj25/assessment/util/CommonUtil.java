package org.akee.prj25.assessment.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CommonUtil {

	public static String formatDateToString(Instant data) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z")
				.withZone(ZoneId.of("GMT+06:30"));

		return formatter.format(data);

	}
}
