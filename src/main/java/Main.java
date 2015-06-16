import cities.domain.City;
import cities.parser.CitiesReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static cities.util.CityComparators.latitudeComparator;
import static cities.util.CityComparators.longitudeComparator;
import static java.util.stream.Collectors.*;

public class Main {
    public static void main(String[] args) throws IOException {
        CitiesReader citiesReader = new CitiesReader();
        List<City> cities = citiesReader.readFrenchCities();
        System.out.println("Found " + cities.stream().count() + " cities");

        List<City> vaucluseCities = cities.stream()
                .filter(city -> city.getDepartementId().equals("84"))
                .collect(toList());
        System.out.println(vaucluseCities.size() + " Vaucluse cities");
        vaucluseCities.stream()
                .map(City::getName)
                .forEach(System.out::println);

        City mostNorthernCity = cities.stream()
                .max(latitudeComparator())
                .get();
        System.out.println("The most northern city is " + mostNorthernCity.getName() + "(" + mostNorthernCity.getDepartementId() + ")");

        City mostWesternCity = cities.stream()
                .min(longitudeComparator())
                .get();
        System.out.println("The most western city is " + mostWesternCity.getName() + "(" + mostWesternCity.getDepartementId() + ")");

        Map<String, List<City>> citiesByDepartment = cities.stream()
                .collect(groupingBy(city -> city.getDepartementId()));
        Map<String, Integer> citiesCountByDepartment = citiesByDepartment.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
        System.out.println(citiesCountByDepartment);
    }
}
