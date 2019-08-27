package zsabin.kairosdb;

import org.junit.Test;
import zsabin.kairosdb.Threshold.BoundaryType;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ThresholdTest
{
    @Test
    public void test_default_boundaryType()
    {
        Threshold threshold = new Threshold();

        assertEquals(BoundaryType.SUPERIOR, threshold.getBoundaryType());
    }

    @Test
    public void test_compareValue_superiorBoundary()
    {
        Threshold threshold = new Threshold(0, BoundaryType.SUPERIOR);

        assertThat(threshold.compareValue(-1), lessThan(0));
        assertThat(threshold.compareValue(0), lessThan(0));
        assertThat(threshold.compareValue(1), greaterThan(0));
    }

    @Test
    public void test_compareValue_inferiorBoundary()
    {
        Threshold threshold = new Threshold(0, BoundaryType.INFERIOR);

        assertThat(threshold.compareValue(-1), lessThan(0));
        assertThat(threshold.compareValue(0), greaterThan(0));
        assertThat(threshold.compareValue(1), greaterThan(0));
    }

    @Test
    public void test_compareTo()
    {
        assertThat(new Threshold(-1, BoundaryType.SUPERIOR).compareTo(new Threshold(0, BoundaryType.SUPERIOR)), lessThan(0));
        assertThat(new Threshold(0, BoundaryType.SUPERIOR).compareTo(new Threshold(0, BoundaryType.SUPERIOR)), equalTo(0));
        assertThat(new Threshold(1, BoundaryType.SUPERIOR).compareTo(new Threshold(0, BoundaryType.SUPERIOR)), greaterThan(0));

        assertThat(new Threshold(0, BoundaryType.INFERIOR).compareTo(new Threshold(0, BoundaryType.SUPERIOR)), lessThan(0));
        assertThat(new Threshold(0, BoundaryType.SUPERIOR).compareTo(new Threshold(0, BoundaryType.INFERIOR)), greaterThan(0));
    }
}
