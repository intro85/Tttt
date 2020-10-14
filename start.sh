#!/bin/bash
mvn clean validate compile package
docker-compose build
docker-compose up