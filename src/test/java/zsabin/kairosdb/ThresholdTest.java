package zsabin.kairosdb;

import org.junit.Test;
import zsabin.kairosdb.Threshold.Boundary;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

public class ThresholdTest
{
    @Test
    public void test_compareTo()
    {
        assertThat(new Threshold(0, Boundary.SUPERIOR).compareTo(new Threshold(0, Boundary.SUPERIOR)), equalTo(0));
        assertThat(new Threshold(1, Boundary.SUPERIOR).compareTo(new Threshold(0, Boundary.SUPERIOR)), greaterThan(0));
        assertThat(new Threshold(-1, Boundary.SUPERIOR).compareTo(new Threshold(0, Boundary.SUPERIOR)), lessThan(0));
    }
}
