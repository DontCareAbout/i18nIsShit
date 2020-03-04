package us.dontcareabout.i18nIsShit.generator;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.csv.CSVRecord;

import us.dontcareabout.i18nIsShit.CSV;
import us.dontcareabout.i18nIsShit.Dictionary;

public abstract class Generator {
	private final List<String> keyList;
	private final HashMap<String, Dictionary> directionaryMap = new HashMap<>();

	public Generator(String url) throws IOException { this(new CSV(url)); }

	public Generator(File file) throws IOException { this(new CSV(file)); }

	public Generator(Reader reader) throws IOException { this(new CSV(reader)); }

	public Generator(CSV csv) {
		List<String> header = csv.getHeaderList();
		List<CSVRecord> records = csv.getRecords();

		//建立 keyList
		ArrayList<String> keyList = new ArrayList<>();
		for (CSVRecord r : records) {
			keyList.add(r.get(0));
		}
		Collections.sort(keyList);
		this.keyList = Collections.unmodifiableList(keyList);
		////

		//建立各語系字典檔
		for (int i = 1; i < header.size(); i++) {
			Dictionary locale = new Dictionary();
			directionaryMap.put(header.get(i), locale);

			for (CSVRecord r : records) {
				locale.put(r.get(0), r.get(i));
			}
		}
	}

	/**
	 * @param folder 語系檔輸出目錄
	 */
	public abstract void gen(File folder) throws IOException;

	/**
	 * @return 字典檔的 key 值列表（所有語系都會是相同內容）
	 */
	public List<String> getKeys() {
		return keyList;
	}

	/**
	 * @return 語系列表
	 */
	public List<String> getLocales() {
		return new ArrayList<>(directionaryMap.keySet());
	}

	/**
	 * @return 語系的字典檔
	 */
	public Dictionary getDictionary(String locale) {
		return directionaryMap.get(locale);
	}
}
