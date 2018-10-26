# scenario-quality-checker
university project for software engineering

### How to transform scenario
Start the scenario-checker and run from your terminal:

```bash
curl -X POST http://server/api/v1/places.json -d @data/testplace.json \
--header "Content-Type: application/json"
```

### How to count number of steps
After you transform scenario run from your terminal:
```bash
curl -X GET localhost:8080/count
```