package zsabin.kairosdb;

import org.kairosdb.core.DataPoint;
import org.kairosdb.core.datastore.AbstractDataPointGroup;

import java.util.LinkedList;
import java.util.Queue;

public class MutableDataPointGroup extends AbstractDataPointGroup
{
    private final Queue<DataPoint> dataPoints = new LinkedList<>();

    public MutableDataPointGroup(String name)
    {
        super(name);
    }

    public void addDataPoint(DataPoint dataPoint)
    {
        dataPoints.add(dataPoint);
    }

    @Override
    public void close()
    {

    }

    @Override
    public boolean hasNext()
    {
        return !dataPoints.isEmpty();
    }

    @Override
    public DataPoint next()
    {
        return dataPoints.poll();
    }
}
