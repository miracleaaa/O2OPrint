package analysis;

/**
 * @author 111250241
 * @created 2014-12-30
 * @version 1.0*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PDFAnalyse {
	private File file;
	private PDDocument document;

	public PDFAnalyse(String path) {
		try {
			file = new File(path);
			document = PDDocument.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @method getPageCount
	 * @return page counts in pdf
	 */
	public int getPageCount() {
		return document.getNumberOfPages();
	}

	/**
	 * @method getFonts
	 * @return all fonts used in pdf
	 */
	@SuppressWarnings("resource")
	public String getFonts() {
		LinkedList<String> fontsList = new LinkedList<String>();
		BufferedReader reader = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			reader = new BufferedReader(new InputStreamReader(fis, "gbk"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				if (line.contains("<</Type/Font/Subtype/Type0/BaseFont/")) {
					int prefix = line.indexOf('+');
					int suffix = line.indexOf("/Encoding");
					String font = line.substring(prefix + 1, suffix);
					fontsList.add(font);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fontsList.toString();
	}

	public String report() {
		StringBuilder sb = new StringBuilder();
		sb.append("文档页数: " + getPageCount() + "\n");
		sb.append("文档使用的字体：" + getFonts());
		return sb.toString();
	}

}
