# KairosDB Aggregators
This project provides custom aggregators for KairosDB. See https://kairosdb.github.io/docs/build/html/kairosdevelopment/Aggregators_and_GroupBys.html for installation instructions.

## Score Aggregator
Scores the data based on a set of thresholds. Each data point will be mapped to a value between 0 and n where n is the number of thresholds.

Parameters:
* `order`: the order by which scores are assigned. Either `ascending` or `descending`.
* `thresholds`: a set of thresholds
    * `value`: the threshold value
    * `boundary`: determines how to compare against values equal to the threshold value. One of either `superior` or `inferior`.
    Values equal to the threshold value are greater than thresholds with inferior boundaries and less than thresholds with superior boundaries.
    
Example:
```
{
    "name": "score",
    "order": "ascending",
    "thresholds": [
        {
            "value": 0,
            "boundary": "superior"
        },
        {
            "value": 10,
            "boundary": "inferior"
        }
    ]
}
```

The above configuration would yield the following results:  

| raw data point | aggregated data point |
|---------------:|----------------------:|
|             -1 |                     0 |
|              0 |                     0 |
|              1 |                     1 |
|             10 |                     2 |
|             11 |                     2 |


The same configuration with `descending` order would yield equivalent results, except with inverted scores:  

| raw data point | aggregated data point |
|---------------:|----------------------:|
|             -1 |                     2 |
|              0 |                     2 |
|              1 |                     1 |
|             10 |                     0 |
|             11 |                     0 |
