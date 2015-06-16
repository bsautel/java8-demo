package cities.util;

import cities.domain.City;

import java.util.Comparator;

public class CityComparators {
    public static Comparator<City> latitudeComparator() {
        return (city1, city2) -> {
            if (city1.getCoordinates().isPresent() && city2.getCoordinates().isPresent()) {
                double latitude1 = city1.getCoordinates().get().getLatitude();
                double latitude2 = city2.getCoordinates().get().getLatitude();
                return Double.compare(latitude1, latitude2);
            }
            return 0;
        };
    }

    public static Comparator<City> longitudeComparator() {
        return (city1, city2) -> {
            if (city1.getCoordinates().isPresent() && city2.getCoordinates().isPresent()) {
                double longitude1 = city1.getCoordinates().get().getLongitude();
                double longitude2 = city2.getCoordinates().get().getLongitude();
                return Double.compare(longitude1, longitude2);
            }
            return 0;
        };
    }
}
