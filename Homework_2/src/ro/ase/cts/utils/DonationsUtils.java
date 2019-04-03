package ro.ase.cts.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DonationsUtils {
	public static boolean checkIBAN(String IBAN) {
		String regex = "[A-Za-z0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(IBAN);
		return matcher.matches();
	}
	
}
