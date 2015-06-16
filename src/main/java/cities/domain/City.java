package cities.domain;

import com.google.auto.value.AutoValue;

import java.util.Optional;

@AutoValue
public abstract class City {
    public abstract String getName();

    public abstract String getDepartementId();

    public abstract String getDepartementName();

    public abstract String getRegion();

    public abstract Optional<GeographicCoordinates> getCoordinates();

    public static City build(String name, String departmentId, String departmentName, String region,
                             Optional<GeographicCoordinates> coordinates) {
        return new AutoValue_City(name, departmentId, departmentName, region, coordinates);
    }
}
