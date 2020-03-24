package us.dontcareabout.i18nIsShit.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import us.dontcareabout.i18nIsShit.CSV;
import us.dontcareabout.i18nIsShit.Dictionary;
import us.dontcareabout.i18nIsShit.Util;

/**
 * CSV 的第一個 locale 會作為 default locale，
 * 亦即產生的 properties 檔案不會有 language_country 資訊。
 */
public class GWT extends Generator {
	private String fileName = "I18N";

	public GWT(String url) throws IOException { super(url); }

	public GWT(File file) throws IOException { super(file); }

	public GWT(CSV csv) throws IOException { super(csv); }

	public String getFileName() {
		return fileName;
	}

	public GWT setFileName(String fileName) {
		this.fileName = fileName;
		return this;
	}

	@Override
	public void gen(File folder) throws IOException {
		if (!folder.exists()) { folder.mkdirs(); }

		//把第一個視為 default locale（檔名不用掛 language_country）
		boolean defaultFlag = true;

		for (String locale : getLocales()) {
			Util.stringToFile(
				gen(getKeys(), getDictionary(locale)),
				new File(
					folder,
					fileName + (defaultFlag ? "" : "_" + correctLocale(locale)) + ".properties"
				)
			);
			defaultFlag = false;
		}
	}

	public static String gen(List<String> keyList, Dictionary locale) {
		if (keyList.size() == 0) { return ""; }

		StringBuffer result = new StringBuffer();

		for (String key : keyList) {
			result.append(key + "=" + locale.get(key) + "\n");
		}

		return result.toString();
	}

	/**
	 * @return 將 locale 值轉成 GWT 允許的格式
	 * 	（language 全小寫英文字、country 只允許最多兩個大寫英文字）
	 */
	public static String correctLocale(String locale) {
		int index = locale.indexOf("_");

		if (index == -1) { return locale.toLowerCase(); }

		String country = locale.substring(index).toUpperCase();

		return locale.substring(0, index) +
			(country.length() <= 3 ? country : country.subSequence(0, 3));
	}
}
