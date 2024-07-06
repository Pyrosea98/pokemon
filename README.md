# Pokemon Demo Project

This project demonstrates a SOAP-based web service for retrieving Pokemon data using Spring Boot.

## Project Structure

- `com.example.pokemon.model`: Contains the data models.
- `com.example.pokemon.service`: Contains the service class for interacting with the external Pokemon API.
- `com.example.pokemon.controller`: Contains the endpoint class for handling SOAP requests.
- `com.example.pokemon.config`: Contains the SOAP configuration class.

## Requirements

- Java 11
- Gradle

## Setup

* Clone the repository:

```bash
git clone https://github.com/yourusername/pokemon-soap-service.git
cd pokemon-soap-service
```

* Build the project:

```bash
./gradlew build
```

* Run the application:

```bash
./gradlew bootRun
```

## Usage

### SOAP Endpoints

- The SOAP web service endpoint is available at `http://localhost:8080/ws`.
- Use SOAP UI or any other SOAP client to send requests and receive responses.

### Example Request

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:pok="http://example.com/pokemon">
   <soapenv:Header/>
   <soapenv:Body>
      <pok:getPokemonRequest>
         <pok:offset>0</pok:offset>
         <pok:limit>10</pok:limit>
      </pok:getPokemonRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
## Testing

### To run the tests:

```bash
./gradlew test
```