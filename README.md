# README #

This README would normally document whatever steps are necessary to explain this repository.

### Prerequisites ###
* Install Docker

### What is this repository for? ###
* It's a demo how to api test against mocks using WireMock in Docker

### Starting the Engine ###
* Navigate to the folder of the 'docker-compose.yml' file (root in our case)
* Create a docker image with command 'docker-compose up'
* Bring down the environment with 'docker-compose down'
* Run the tests manually from class 'API' in folder 'src/test/java'

### CommandLine Run ###
* 

### Utilities ###
* In the mockData folder you can see the definitions of the mocked json-data (scheme) in folder mappings
    * These mappings/schemes are spinned up in the docker image.
    * In other words, you test against it. It's your requirement of the schemes!
* In the mockData folder you can see the request bodies (sent during testing) in folder __files
* In the folder 'test/java' test are written for verification