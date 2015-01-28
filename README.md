Prerequisites:

- jdk 6 or later
- maven3

----------

Instructions to build the project:

1.- mvn clean install

2.- Run my-loyal-api with the command:
    <my-loyal-api-folder> java -jar target/my-loyal-api-service-1.0.0.jar

3.- java -jar target/capi-1.0.0-SNAPSHOT.jar

4.- Swagger UI is embedded in the project, go to http://localhost:9000/docs/index.html in the browser to interact with the REST API.

----------

Samples of requests to the API:

curl --header "API_KEY: test" http://localhost:9000/customer/1

curl --header "API_KEY: test" http://localhost:9000/customer/1/bookings

curl --header "API_KEY: test" http://localhost:9000/customer/1/bookings?fromDate=2014-12-01&toDate=2015-02-01

curl --header "API_KEY: test" http://localhost:9000/customer/1/points

