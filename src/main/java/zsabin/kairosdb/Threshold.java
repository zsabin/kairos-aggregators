package zsabin.kairosdb;

import org.kairosdb.core.annotation.FeatureProperty;

import java.util.Objects;

public class Threshold implements Comparable<Threshold>
{
    enum Boundary
    {
        SUPERIOR,
        INFERIOR
    }

    @FeatureProperty(
            name = "value",
            label = "Value",
            description = "The value of the threshold"
    )
    private double value;

    @FeatureProperty(
            name = "boundary",
            label = "Boundary",
            description = "Determines how to compare against values equal to the threshold value. " +
                    "Values equal to the threshold value are greater than thresholds with inferior boundaries " +
                    "and less than thresholds with superior boundaries.",
            type = "enum",
            options = {"superior", "inferior"},
            default_value = "superior"
    )
    private Boundary boundary = Boundary.SUPERIOR;

    private Threshold()
    {
    }

    public Threshold(double value, Boundary boundary)
    {
        this.value = value;
        this.boundary = Objects.requireNonNull(boundary, "boundary must not be null");
    }

    public int compareValue(double value)
    {
        if (value > this.value) {
            return 1;
        }
        if (value < this.value) {
            return -1;
        }
        switch (boundary) {
            case SUPERIOR:
                return -1;
            case INFERIOR:
                return 1;
            default:
                throw new IllegalStateException("Unknown boundary type: " + boundary);
        }
    }

    @Override
    public String toString()
    {
        return "Threshold{" +
                "value=" + value +
                "boundary=" + boundary +
                '}';
    }

    @Override
    public int compareTo(Threshold threshold)
    {
        return (int) (value - threshold.value);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Threshold threshold = (Threshold) o;
        return Double.compare(threshold.value, value) == 0 &&
                boundary == threshold.boundary;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value, boundary);
    }
}
