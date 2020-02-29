package us.dontcareabout.i18nIsShit;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

/**
 * Commons CSV 的 wrapper
 */
public class CSV {
	private final CSVParser parser;

	//應該只能取一次，第二次之後就是空的，所以抽成 field
	private final List<CSVRecord> records;

	public CSV(String url) throws IOException {
		this(new StringReader(Util.urlToString(url)));
	}

	public CSV(File file) throws IOException {
		this(new FileReader(file));
	}

	public CSV(Reader reader) throws IOException {
		parser = CSV.read(reader);
		records = parser.getRecords();
	}

	public List<CSVRecord> getRecords() {
		return records;
	}

	public List<String> getHeaderList() {
		return parser.getHeaderNames();
	}

	public static CSVParser read(Reader reader) throws IOException {
		return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
	}
}
