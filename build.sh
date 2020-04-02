#!/bin/bash

git pull origin master
mvn clean install
mvn spring-boot:run