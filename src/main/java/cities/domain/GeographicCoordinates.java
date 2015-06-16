package cities.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class GeographicCoordinates {
    public abstract double getLatitude();

    public abstract double getLongitude();

    public static GeographicCoordinates build(double latitude, double longitude) {
        return new AutoValue_GeographicCoordinates(latitude, longitude);
    }
}
