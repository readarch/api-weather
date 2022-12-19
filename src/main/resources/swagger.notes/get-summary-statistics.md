## Description

It allows you to consult a list of weathers statistics with summary statistics according to the year, month, day, country and the city.

## API Access Path

| Method | URI                                                                                  |
|--------|--------------------------------------------------------------------------------------|
| GET    | /v1.0/weather/summary-statistics?year=2022&month=1&day=1&country=CHILE&city=SANTIAGO |

## Path Params

Does not apply

## Query Params

| Name    | Type    | Description                                        | Example  |
|---------|---------|----------------------------------------------------|----------|
| year    | Integer | Year consulted to obtain its weather statistics    | 2022     |
| month   | Integer | Month consulted to obtain its weather statistics   | 1        |
| day     | Integer | Day consulted to obtain its weather statistics     | 1        |
| country | String  | Country consulted to obtain its weather statistics | CHILE    |
| city    | String  | City consulted to obtain its weather statistics    | SANTIAGO |

## Request Params

Does not apply

## Response Data

| Name    | Type   | Description                  | Example            |
|---------|--------|------------------------------|--------------------|
| minimum | double | The minimum recorded value   | 10.0               |
| maximum | double | The maximum recorded value   | 40.0               |
| count   | long   | The count of values recorded | 62                 |
| sum     | double | The sum of values recorded   | 1614.0             |
| average | double | The average recorded value   | 26.032258064516128 |