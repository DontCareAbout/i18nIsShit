package us.dontcareabout.i18nIsShit.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import us.dontcareabout.i18nIsShit.CSV;
import us.dontcareabout.i18nIsShit.Util;

/**
 * jQuery.i18n 的 generator。
 * <p>
 * 輸出格式為：每個語系（header）獨立一個 JSON 檔，檔名為對應的 header 名。
 */
public class JQuery extends Generator {
	public JQuery(String url) throws IOException { super(url); }

	public JQuery(File file) throws IOException { super(file); }

	public JQuery(CSV csv) throws IOException { super(csv); }

	@Override
	public void gen(File folder) throws IOException {
		if (!folder.exists()) { folder.mkdir(); }

		List<String> header = csv.getHeaderList();

		for (int i = 1; i < header.size(); i++) {
			Util.stringToFile(
				genLocal(csv.getRecords(), header.get(i)),
				new File(folder, header.get(i) + ".json")
			);
		}
	}

	public static String genLocal(List<CSVRecord> records, String header) throws IOException {
		if (records.size() == 0) { return "{}"; }

		StringBuffer result = new StringBuffer("{" + tuple(records.get(0), header, ""));
		for (int i = 1; i < records.size(); i++) {
			result.append(tuple(records.get(i), header, ","));
		}
		result.append("}");

		return result.toString();
	}

	public static String tuple(CSVRecord record, String header, String prefix) {
		return record.get(header).isEmpty() ? "" :
			prefix + "\"" + record.get(0) + "\":\"" + record.get(header) + "\"";
	}
}
