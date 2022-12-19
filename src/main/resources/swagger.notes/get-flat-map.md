## Description

It allows you to consult a list of weathers statistics using flatMap according to country, first_city and the second_city.

## API Access Path

| Method | URI                                                                              |
|--------|----------------------------------------------------------------------------------|
| GET    | /v1.0/weather/flat-map?country=CHILE&first_city=SANTIAGO&second_city=ANTOFAGASTA |

## Path Params

Does not apply

## Query Params

| Name        | Type    | Description                                                                | Example     |
|-------------|---------|----------------------------------------------------------------------------|-------------|
| country     | String  | Country consulted to obtain its weather statistics                         | CHILE       |
| first_city  | String  | First City consulted to obtain its weather statistics with the other city  | SANTIAGO    |
| second_city | String  | Second City consulted to obtain its weather statistics with the other city | ANTOFAGASTA |

## Request Params

Does not apply

## Response Data

| Name    | Type   | Description                                     | Example            |
|---------|--------|-------------------------------------------------|--------------------|
| minimum | double | The minimum recorded value between two cities   | 10.0               |
| maximum | double | The maximum recorded value between two cities   | 40.0               |
| count   | long   | The count of values recorded between two cities | 62                 |
| sum     | double | The sum of values recorded between two cities   | 1614.0             |
| average | double | The average recorded value between two cities   | 26.032258064516128 |