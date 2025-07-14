# Springboot-App-for-REST-Service-Access-with-JsonWebToken
# Transport Service API

A Spring Boot REST API for managing and retrieving transport entities.

## Features

- Retrieve a list of transport entities via REST endpoint
- OAuth2 authentication with external provider
- Uses WebClient for HTTP requests

## Prerequisites

- Java 17+
- Maven 3.6+
- Internet connection (for OAuth2 token retrieval)

## Setup

1. Clone the repository
2. Configure your `application.properties`:
   client.id=the_client_id 
   client.secret=the_client_secret

3. Build and run the application


## API Usage
Once the application is running, access the transport list at:http://localhost:8080/transports

This endpoint returns a list of transport entities.