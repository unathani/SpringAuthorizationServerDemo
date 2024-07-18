# SpringAuthorizationServerDemo
POC on Spring Authorization Server for client credentials flow

# What is Spring Authorization Server ?
SAS is a spring component that helps you manage authorization in your applications. It simplifies the implementation of OAuth2 and OIDC (OpenID Connect) standards. These standards provide secure ways to authoriza users and grant them access to resources.

OAuth 2.0 -> A protocol that allows applications to delegate authorization. Allows users to grant third-party applications access to their resources without giving them their credentials.

# How Spring Authorization Server works ?
1. A user tries to log into an application (client)
2. The client sends an authorize request to the Authorization Server (AS) 

3. The AS authorizes the user using the creds and gives out access tokens

4. The client uses access token to request data from Resource Server.

# The /oauth/token flow more in detail
1. A user tries to log into an application (client) and the application receives the /oauth/token request with the required parameters (grant_type, client_id, client_secret)
2. The Security Configuration on the Spring Application, verifies if this HTTP request has all the required params and is valid.
3. Authentication Filter -> this filter from the filter chain kicks in and extracts and validates the client credentials from the request headers.
4. TokenEndpointFilter -> filter checks for the grant_type if it is client_credentials, it goes ahead authenticates the client and passes the request to the TokenService for token generation.
5. TokenGeneration -> TokenService in the AS component helps in generation of access tokens and appropriate data is bundled into the token and converted to JSON and passed as a response.
6. This JSON response sent back to the client.

# Project Details
This is a working POC that demonstrates the working of Spring Authorization Server for client credentials flow using SpringBoot 3.3.1, Spring Security 6.3.2 and Java 17.
There are 2 ways to get the access token, when running this service either call
1. GET http://localhost:9000/get-token - this endpoint internally would call the /oauth2/token endpoint that SAS implements behind the scenes for us with the correct registered client details.
2. POST /get HTTP/1.1
Host: localhost:9000
Content-Type: application/x-www-form-urlencoded
Authorization: ••••••
Content-Length: 29
grant_type=client_credentials


# References
https://spring.io/projects/spring-authorization-server
https://docs.spring.io/spring-authorization-server/reference/getting-started.html
https://github.com/spring-projects/spring-authorization-server/tree/main/samples/default-authorizationserver
