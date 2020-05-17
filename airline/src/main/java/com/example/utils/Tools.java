package com.example.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	public static String maskCreditCard(String sentenceThatMightContainACreditCard) {

		Pattern p = Pattern.compile("\\d{4}+");

		List<String> cardNumberGroup = new ArrayList<String>();

		Matcher m = p.matcher(sentenceThatMightContainACreditCard);
		while (m.find()) {
			cardNumberGroup.add(m.group());
		}

		cardNumberGroup.set(1, cardNumberGroup.get(1).substring(0, 2) + "**");

		cardNumberGroup.set(2, cardNumberGroup.get(1).replaceAll("\\d", "*"));

		String masked = String.join("", cardNumberGroup);

		return masked;
	}

}
