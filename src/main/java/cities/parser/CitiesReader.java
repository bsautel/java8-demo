package cities.parser;

import cities.domain.City;
import cities.domain.GeographicCoordinates;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class CitiesReader {
    public List<City> readFrenchCities() throws IOException {
        InputStreamReader fileReader =
                new InputStreamReader(getClass().getClassLoader().getResourceAsStream("communes_france.csv"));
        CsvMapReader reader = new CsvMapReader(fileReader, CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE);
        String[] header = reader.getHeader(true);
        CellProcessor[] processors = {
                new NotNull(), // EU_circo
                new ParseInt(),// code_région
                new NotNull(),// nom_région
                new NotNull(),// chef-lieu_région
                new NotNull(),// numéro_département
                new NotNull(), // nom_département
                new NotNull(), // préfecture
                new ParseInt(), // numéro_circonscription
                new NotNull(), // nom_commune
                new NotNull(), // codes_postaux
                new NotNull(), // code_insee
                new Optional(new CoordinateParser()), // latitude
                new Optional(new CoordinateParser()), // longitude
                new Optional(new NotNull()) // éloignement
        };
        Map<String, Object> line;
        List<City> result = new ArrayList<>();
        while ((line = reader.read(header, processors)) != null) {
            result.add(readCity(line));
        }
        return result;
    }

    private City readCity(Map<String, Object> line) {
        String cityName = (String) line.get("nom_commune");
        String departmentId = (String) line.get("numéro_département");
        String departmentName = (String) line.get("nom_département");
        String regionName = (String) line.get("nom_région");
        java.util.Optional<GeographicCoordinates> optionalCoordinates = readCoordinates(line);
        return City.build(cityName, departmentId, departmentName, regionName, optionalCoordinates);
    }

    private java.util.Optional<GeographicCoordinates> readCoordinates(Map<String, Object> line) {
        Double latitude = (Double) line.get("latitude");
        Double longitude = (Double) line.get("longitude");
        if (latitude != null && longitude != null) {
            return of(GeographicCoordinates.build(latitude, longitude));
        }
        return empty();
    }
}
