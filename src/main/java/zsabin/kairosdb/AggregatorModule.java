package zsabin.kairosdb;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AggregatorModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(ScoreAggregator.class).in(Singleton.class);
    }
}
