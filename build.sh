#!/bin/bash

git pull origin hosting_set_up
mvn clean install
mvn spring-boot:run