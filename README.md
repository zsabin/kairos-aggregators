# KairosDB Aggregators
This project provides custom aggregators for KairosDB. See https://kairosdb.github.io/docs/build/html/kairosdevelopment/Aggregators_and_GroupBys.html for installation instructions.

## Partition Aggregator
Partitions the data according to a set of thresholds. Each data point is mapped to a value between 0 and n where n is the number of thresholds.

Parameters:
* `direction`: the order by which partition values are assigned
* `thresholds`: an unordered list of thresholds
    * `value`: the threshold value
    * `boundary`: determines how to compare against values equal to the threshold value. One of either `superior` or `inferior`.
    Values equal to the threshold value are greater than thresholds with inferior boundaries and less than thresholds with superior boundaries.
    
Example:
```
{
    "name": "partition",
    "direction": "ascending",
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


The same configuration with `descending` direction would yield equivalent results, except with inverted partition numbering:  

| raw data point | aggregated data point |
|---------------:|----------------------:|
|             -1 |                     2 |
|              0 |                     2 |
|              1 |                     1 |
|             10 |                     0 |
|             11 |                     0 |
