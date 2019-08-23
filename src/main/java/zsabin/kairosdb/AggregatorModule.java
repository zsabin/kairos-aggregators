package zsabin.kairosdb;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AggregatorModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(MapAggregator.class).in(Singleton.class);
    }
}
