package test;

import analysis.PDFAnalyse;

public class Main {
	public static void main(String[] args) {
		String path = "./res/test.pdf";
		PDFAnalyse analyse = new PDFAnalyse(path);
		String report = analyse.report();
		System.out.println(report);
	}
}
