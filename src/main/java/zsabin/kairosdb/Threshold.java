package zsabin.kairosdb;

import org.kairosdb.core.annotation.FeatureProperty;

public class Threshold implements Comparable<Threshold>
{
    @FeatureProperty(
            name = "value",
            label = "Value",
            description = "The value of the threshold"
    )
    private double value;

    private Threshold()
    {
    }

    public Threshold(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return "Threshold{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Threshold threshold)
    {
        return (int)(value - threshold.getValue());
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Threshold threshold = (Threshold) o;

        return value == threshold.value;
    }

    @Override
    public int hashCode()
    {
        return Double.hashCode(value);
    }
}
