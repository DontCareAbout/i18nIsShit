package us.dontcareabout.i18nIsShit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

public class Util {
	public static void stringToFile(String string, File file) throws IOException {
		Files.write(
			file.toPath(), string.getBytes(StandardCharsets.UTF_8),
			StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE
		);
	}

	public static String urlToString(String url) throws IOException {
		Connection conn = Jsoup.connect(url).validateTLSCertificates(false);
		conn.request().method(Method.GET);
		return conn.execute().body();
	}
}
