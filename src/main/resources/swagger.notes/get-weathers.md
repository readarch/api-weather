## Description

It allows you to consult a list of weathers according to the year, month, day, country and the city.

## API Access Path

| Method | URI                                                                        |
|--------|----------------------------------------------------------------------------|
| GET    | /v1.0/weather/weathers?year=2022&month=1&day=1&country=CHILE&city=SANTIAGO |

## Path Params

Does not apply

## Query Params

| Name    | Type    | Description                             | Example  |
|---------|---------|-----------------------------------------|----------|
| year    | Integer | Year consulted to obtain its weather    | 2022     |
| month   | Integer | Month consulted to obtain its weather   | 1        |
| day     | Integer | Day consulted to obtain its weather     | 1        |
| country | String  | Country consulted to obtain its weather | CHILE    |
| city    | String  | City consulted to obtain its weather    | SANTIAGO |

## Request Params

Does not apply

## Response Data

| Name                 | Type    | Description          | Example  |
|----------------------|---------|----------------------|----------|
| id                   | Integer | Weather identifier   | 1        |
| year                 | Integer | Year                 | 2022     |
| month                | Integer | Month                | 1        |
| day                  | Integer | Day                  | 1        |
| country              | String  | Country              | CHILE    |
| city                 | String  | City                 | SANTIAGO |
| temperature          | String  | Temperature          | 32 ºC    |
| precipitation        | String  | Precipitation        | 2.4 mm   |
| wind                 | String  | Wind                 | 12 Km/h  |
| rh                   | String  | RH                   | 65 %     |
| atmospheric_pressure | String  | Atmospheric Pressure | 1035 hPa |
| cloudiness           | String  | Cloudiness           | 2 octas  |