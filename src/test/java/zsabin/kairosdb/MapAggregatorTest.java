package zsabin.kairosdb;

import org.junit.Before;
import org.junit.Test;
import org.kairosdb.core.DataPoint;
import org.kairosdb.core.datapoints.DoubleDataPoint;
import org.kairosdb.core.datapoints.DoubleDataPointFactoryImpl;
import org.kairosdb.core.datastore.DataPointGroup;
import org.kairosdb.core.datastore.EmptyDataPointGroup;
import org.kairosdb.core.datastore.TagSetImpl;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class MapAggregatorTest
{
    private MapAggregator aggregator;

    @Before
    public void setup()
    {
        aggregator = new MapAggregator(new DoubleDataPointFactoryImpl());
    }

    @Test(expected = NullPointerException.class)
    public void test_aggregate_nullSet()
    {
        aggregator.aggregate(null);
    }

    @Test
    public void test_aggregate_emptySet()
    {
        DataPointGroup results = aggregator.aggregate(new EmptyDataPointGroup("Test.Metric", new TagSetImpl()));

        assertFalse(results.hasNext());
    }

    @Test
    public void test_aggregate_singleThreshold()
    {
        aggregator.setThresholds(Collections.singletonList(0.0));

        MutableDataPointGroup group = new MutableDataPointGroup("foo");
        group.addDataPoint(new DoubleDataPoint(0, -1));
        group.addDataPoint(new DoubleDataPoint(1, 0));
        group.addDataPoint(new DoubleDataPoint(2, 1));

        DataPointGroup results = aggregator.aggregate(group);

        DataPoint dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(0L));
        assertThat(dataPoint.getDoubleValue(), equalTo(0.0));

        dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(1L));
        assertThat(dataPoint.getDoubleValue(), equalTo(0.0));

        dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(2L));
        assertThat(dataPoint.getDoubleValue(), equalTo(1.0));
    }


    @Test
    public void test_aggregate_multipleThresholds()
    {
        aggregator.setThresholds(Arrays.asList(0.0, 10.0, 20.0));

        MutableDataPointGroup group = new MutableDataPointGroup("foo");
        group.addDataPoint(new DoubleDataPoint(0, -1));
        group.addDataPoint(new DoubleDataPoint(1, 1));
        group.addDataPoint(new DoubleDataPoint(2, 11));
        group.addDataPoint(new DoubleDataPoint(3, 21));

        DataPointGroup results = aggregator.aggregate(group);

        DataPoint dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(0L));
        assertThat(dataPoint.getDoubleValue(), equalTo(0.0));

        dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(1L));
        assertThat(dataPoint.getDoubleValue(), equalTo(1.0));

        dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(2L));
        assertThat(dataPoint.getDoubleValue(), equalTo(2.0));

        dataPoint = results.next();
        assertThat(dataPoint.getTimestamp(), equalTo(3L));
        assertThat(dataPoint.getDoubleValue(), equalTo(3.0));

    }
}
