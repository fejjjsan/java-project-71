# Makefile

build:
	./gradlew clean build

run:
	./gradlew run

test:
	./gradlew test

lint:
	./gradlew checkstyleMain

run-dist:
	./app/build/install/app/bin/app

test-report:
	gradle test
	gradle jacocoTestReport

.PHONY: build
.PHONY: test

