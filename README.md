# Map vs List Comparator

The project compares the time needed to find a given element in a Map vs the time needed to find a given element in a
List.

## To note

The time duration for the map test *includes* the time spent building the map.

## Results

The time needed to find the element in a collection with:

- 10 000 elements took
    - < 1s for the map
    - < 1s for the list
- 50 000 elements took
    - < 1s for the map
    - ~6s for the list
- 100 000 elements took
    - < 1s for the map
    - ~45s for the list
- 200 000 elements took
    - < 1s for the map
    - ~5m 35s for the list
- 250 000 elements took
    - < 1s for the map
    - ~7m 15s for the list

The map started taking ~1s at 1 500 000 elements.