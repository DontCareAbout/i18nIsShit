package us.dontcareabout.i18nIsShit;

import java.util.HashMap;

/**
 * 單一語系的字典檔，其實就是個 {@link HashMap}。
 */
public class Dictionary {
	private HashMap<String, String> map = new HashMap<>();

	public String get(String key) {
		return map.get(key);
	}

	//沒有要特別針對 return 值作什麼處理，所以就不 return 了
	public void put(String key, String value) {
		map.put(key, value);
	}
}
