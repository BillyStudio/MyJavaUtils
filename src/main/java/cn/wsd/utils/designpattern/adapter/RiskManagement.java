package cn.wsd.utils.designpattern.adapter;

import java.util.List;

interface WordsFilter {
	String doFilter(String text, String mask);
}

class ASensitiveWordsFilter {
	public String filterSexyWords(String text) {
		return "";
	}

	public String filterPoliticalWords(String text) {
		return "";
	}
}

class BSensitiveWordsFilter {
	public String filter(String text) {
		return "";
	}
}

class CSensitiveWordsFilter {
	public String filter(String text, String mask) {
		return "";
	}
}

class FilterAdapter implements WordsFilter {

	private String filterA(String text) {
		ASensitiveWordsFilter filterA = new ASensitiveWordsFilter();
		text = filterA.filterSexyWords(text);
		return filterA.filterPoliticalWords(text);
	}

	private String filterB(String text) {
		BSensitiveWordsFilter filterB = new BSensitiveWordsFilter();
		return filterB.filter(text);
	}

	private String filterC(String text, String mask) {
		CSensitiveWordsFilter filterC = new CSensitiveWordsFilter();
		return filterC.filter(text, mask);
	}

	public String doFilter(String text, String mask) {
		String maskedText = filterA(text);
		maskedText = filterB(maskedText);
		maskedText = filterC(maskedText, mask);
		return maskedText;
	}
}

public class RiskManagement { // 我们的系统调用A、B、C三个系统提供的方法

	FilterAdapter filterAdapter = new FilterAdapter();

	public String filterSensitiveWords(String text) {
		return filterAdapter.doFilter(text, "***");
	}
}
