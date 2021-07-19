# URL Shortener Makefile

.PHONY: all clean build test install start stop docker-build docker-tag docker-push

clean:
	mvn clean

build:
	mvn clean build

test:
	mvn clean test

install:
	mvn clean install

all:
	mvn clean package
	sudo docker-compose up

start:
	sudo docker-compose up

stop:
	sudo docker-compose down

docker-build:
	sudo docker build -t url-shortener .

docker-tag:
	sudo docker tag url-shortener:latest cksidharthan/url-shortener:latest

docker-push:
	sudo docker push cksidharthan/url-shortener:latest

help:
	@ echo "Usage   :  make <target>"
	@ echo "Targets :"
	@ echo "   clean .........Removes build products"
	@ echo "   build .........Builds all Java files"
	@ echo "   test ..........Builds and runs all unit tests"
	@ echo "   install .......Builds and installs to local repository"
	@ echo "   package .......Generates project package."
	@ echo "   start .........Deploys the application in local docker container."
	@ echo "   stop ..........Stops the application in local docker container."
	@ echo "   all ...........Generates project package and deploys it in local docker container."
	@ echo "   docker-build ..Builds the docker image for url-shortener application."
	@ echo "   docker-tag ....Tags the docker image for url-shortener application."
	@ echo "   docker-push ...Pushes the docker image for url-shortener application to docker repository."
	@ echo "   help ..........Prints this help message"