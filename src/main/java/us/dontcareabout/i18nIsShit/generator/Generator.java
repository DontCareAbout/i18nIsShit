package us.dontcareabout.i18nIsShit.generator;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

import us.dontcareabout.i18nIsShit.CSV;

public abstract class Generator {
	public final CSV csv;

	public Generator(String url) throws IOException { this(new CSV(url)); }

	public Generator(File file) throws IOException { this(new CSV(file)); }

	public Generator(Reader reader) throws IOException { this(new CSV(reader)); }

	public Generator(CSV csv) {
		this.csv = csv;
	}

	/**
	 * @param folder 語系檔輸出目錄
	 */
	public abstract void gen(File folder) throws IOException;
}
