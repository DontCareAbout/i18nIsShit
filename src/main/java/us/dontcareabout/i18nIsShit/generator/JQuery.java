package us.dontcareabout.i18nIsShit.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import us.dontcareabout.i18nIsShit.CSV;
import us.dontcareabout.i18nIsShit.Dictionary;
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

		List<String> keyList = getKeys();

		for (String locale : getLocales()) {
			Util.stringToFile(
				gen(keyList, getDictionary(locale)),
				new File(folder, locale + ".json")
			);
		}
	}

	public static String gen(List<String> keyList, Dictionary locale) {
		if (keyList.size() == 0) { return "{}"; }

		StringBuffer result = new StringBuffer("{" + tuple(keyList.get(0), locale.get(keyList.get(0)), ""));
		for (int i = 1; i < keyList.size(); i++) {
			result.append(tuple(keyList.get(i), locale.get(keyList.get(i)), ","));
		}
		result.append("}");

		return result.toString();
	}

	public static String tuple(String key, String value, String prefix) {
		return value == null || value.isEmpty() ? "" :
			prefix + "\"" + key + "\":\"" + value + "\"";
	}
}
