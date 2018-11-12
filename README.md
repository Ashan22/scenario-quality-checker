# scenario-quality-checker
![Build status](https://travis-ci.org/damian-horna/scenario-quality-checker.svg?branch=master)

University project for software engineering.
Definition of done: https://docs.google.com/spreadsheets/u/1/d/e/2PACX-1vSxEKEBzcopOqfu9OHFwQkD2oDQlztfqAW0Tf_IXjElZQyKDUrzl4-oxI78NQEHZaLh1Vorl2RSyEf3/pubhtml


### How to transform scenario
Start the scenario-checker and run from your terminal:

```bash
curl -X POST localhost:8080/check -d @data/exampleScenario.json \
--header "Content-Type: application/json"
```

### How to count number of steps
After you transform scenario run from your terminal:
```bash
curl -X GET localhost:8080/count
```