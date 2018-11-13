# scenario-quality-checker
![Build status](https://travis-ci.org/damian-horna/scenario-quality-checker.svg?branch=master)

University project for software engineering.
Definition of done: https://docs.google.com/spreadsheets/u/1/d/e/2PACX-1vSxEKEBzcopOqfu9OHFwQkD2oDQlztfqAW0Tf_IXjElZQyKDUrzl4-oxI78NQEHZaLh1Vorl2RSyEf3/pubhtml

### How to count number of steps
After you transform scenario run from your terminal:
```bash
curl -X POST localhost:8080/count -d @data/exampleScenario.json \
--header "Content-Type: application/json"
```

### How to get a sub-scenarios with specified deep level
After you transform scenario run from your terminal:
```bash
curl -X POST localhost:8080/scenarios/levels/{level} -d @data/exampleScenario.json \
--header "Content-Type: application/json"
```
where `{level}` is your deep level bigger or equal to 0

Things to improve before next spring (#2):
* improve communication between scrum master and the rest of the team
* Artur should write tests
* Improve code quality
* We should be nicer to one another
