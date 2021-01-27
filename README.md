# openbrewerydb_test

1) Cover "Search Breweries" method of https://www.openbrewerydb.org/documentation with autotests using Java + REST Assured (pick up to 5 scenarios covering main method features and implement corresponding autotest in code, the rest scenarios you think should be included in complete test suite can be listed in readme file).

Running tests:
```bash
cd <project folder>
gradle test
allure generate -c allure-results
```

2) Examine "List Breweries" method and share your thoughts (in readme file) on how you would apply test automation to it (what approach, test design techniques you'd choose etc). Also please provide estimated effort for completing this task.

by_city, by_name, by_state, by_postal, by_type parameters require values listed in Db to show some results.

So the cases are:
* existent values
* nonexistent values
* empty parameter value
* special characters
* partial values
* using underscores and url encoded `%20` as spaces
* set multiple values
* hyphens and underscores (and url encoded kinds) for postal codes
* sql injections

Postal codes is a range of numbers, so the `boundary values` and `equivalence partitioning` can be applied to test them

For `sort` we can test both ascending and descending orders, also empty parameter and invalid parameter

For pagination also the `boundary values` and `equivalence partitioning` can be applied

Estimated time to cover scenarios about 16-20 hours. The cases are similar for different parameters so data driven testing can be applies here.
