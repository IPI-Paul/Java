package chapter01;  // collecting

import static java.util.stream.Collectors.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/**
import java.nio.file.*;
*/
import java.util.*;
import java.util.stream.*;
import javax.swing.JOptionPane;
import ipi.*;

/**
 * {@code DownStreamCollectors} class Listing 1.6 <br />
 * City static inner class <br />
 * This program demonstrates downstream collectors. <br />
 * @author Cay Horstmann
 */
public class DownStreamCollectors {
	private static final String MAIN_CLASS = "chapter01.Chapter01";
	private static String message = "";
	private static Loaders file = new Loaders();
	
	public static class City {
		private String name;
		private String subDivision;
		private String state;
		private String country;
		private double latitude;
		private int population;
		private double longitude;
		
		public City(String name, String subDivision, String state, String country, int population, 
				double latitude, double longitude) {
			this.name = name;
			this.subDivision = subDivision;
			this.state = state;
			this.country = country;
			this.population = population;
			this .latitude = latitude;
			this.longitude = longitude;
		}
		
		public String getName() {
			return name;
		}
		
		public String getSubDivision() {
			return subDivision;
		}
		
		public String getState() {
			return state;
		}
		
		public String getCountry() {
			return country;
		}
		
		public int getPopulation() {
			return population;
		}
		
		public double getLatitude() {
			return latitude;
		}
		
		public double getLongitude() {
			return longitude;
		}
		
		public String getNameWithPopulation() {
			return name + " pop:" + population + " lat:" + latitude + " long:" + longitude;
		}
	}
	
	public static Stream<City> readCities(String filename) throws IOException {
		/**
		return Files.lines(Paths.get(filename)).skip(1).map(l -> l.split("\\t"))
				.map(a -> new City(a[0], a[1], a[2], a[3], Integer.parseInt(a[4])
						, Double.parseDouble(a[5]), Double.parseDouble(a[6])));
		 */
		return file.toLines(StandardCharsets.UTF_8).skip(1).map(l -> l.split("\\t"))
				.map(a -> new City(a[0], a[1], a[2], a[3], Integer.parseInt(a[4])
						, Double.parseDouble(a[5]), Double.parseDouble(a[6])));
	}
	
	public static void main(String[] args) throws IOException {
		Charset cs = StandardCharsets.UTF_8;
		file.setChoice("DS", "txt", "", "cities.txt", cs, "", "", "For Source Text File");
		if (file.getChoice() == false) {
			Views.openWindowOpener(MAIN_CLASS, message);
			return;
		}
		String filePath = file.getFilePath();
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<Locale>> countryToLocaleSet = locales.collect(groupingBy(
				Locale::getCountry, toSet()));
		System.out.println("countryToLocaleSet: " + countryToLocaleSet);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Long> countryToLocaleCounts = locales.collect(groupingBy(
				Locale::getCountry, counting()));
		System.out.println("countryToLocaleCounts: " + countryToLocaleCounts);

		Stream<City> cities = readCities(filePath);
		Object[] countries = cities.collect(Collectors.groupingBy(City::getCountry)).keySet().toArray();
		Arrays.sort(countries);
		Object country = JOptionPane.showInputDialog(null, "Please give the Country Name to filter on:", 
				"Country Filter", JOptionPane.QUESTION_MESSAGE, null, countries, "");

		cities = readCities(filePath).filter(s -> s.getCountry().equals(country));
		Object[] subDivisions = cities.collect(Collectors.groupingBy(City::getSubDivision)).keySet().toArray();
		Arrays.sort(subDivisions);
		Object subDivision = JOptionPane.showInputDialog(null, "Please give the Sud Division Name to filter" +
				"on:", "Sub Division Filter", JOptionPane.QUESTION_MESSAGE, null, subDivisions, "");

		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		Map<String, Integer> stateToCityPopulation = cities.collect(groupingBy(
				City::getState, summingInt(City::getPopulation)));
		System.out.println("stateToCityPopulation: " + stateToCityPopulation);
		
		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		Map<String, Optional<String>> stateToLongestCityName = cities
				.collect(groupingBy(
						City::getState,
						mapping(City::getName,
								maxBy(Comparator.comparing(String::length)))));
		System.out.println("stateToLongestCityName: " + stateToLongestCityName);
		
		locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryToLanguages = locales.collect(groupingBy(
				Locale::getDisplayCountry,
				mapping(Locale::getDisplayLanguage, toSet())));
		System.out.println("countryToLanguages: " + countryToLanguages);

		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		/**
		 * Altered to enable web start support. <br />
		 * Other methods that worked <br />
		Set<String> stateGroup = cities.collect(Collectors.groupingBy(City::getState)).keySet();
		String[] states = new String[stateGroup.size()];
		* Either <br />
		stateGroup.forEach(s -> {
			states[Math.toIntExact(Arrays.stream(states).filter(f -> f != null).count())] = s;
			});
		* Or <br />
		int i = 0;
		for (String a : stateGroup) {
			states[i] = a;
			i++;
		}
		 */
		Object[] states = cities.collect(Collectors.groupingBy(City::getState)).keySet().toArray();
		Arrays.sort(states);
		Object state = JOptionPane.showInputDialog(null, "Please give State Name to filter on:", 
				"State Filter", JOptionPane.QUESTION_MESSAGE, null, states, "");

		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		Map<String, IntSummaryStatistics> stateToCityPopulationSummary = cities
				.collect(groupingBy(City::getState,
						summarizingInt(City::getPopulation)));
		System.out.println("stateToCityPopulationSummary(" + state + "): " + 
						stateToCityPopulationSummary.get(state));
		
		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		Map<String, String> stateToCityNames = cities.collect(groupingBy(
				City::getState, 
				reducing("",City::getNameWithPopulation, (s, t) -> s.length() == 0 ? t : s + ", " + t)));
		System.out.println("stateToCityNamesWithPopulation(" + state + "): " + 
				stateToCityNames.get(state));
		
		cities = readCities(filePath).filter(s -> s.getCountry().equals(country))
				.filter(s -> s.getSubDivision().equals(subDivision));
		stateToCityNames = cities.collect(groupingBy(
				City::getState,
				mapping(City::getName, joining(", "))));
		System.out.println("stateToCityNames: " + stateToCityNames);
		Views.openWindowOpener(MAIN_CLASS, message);
	}
}
