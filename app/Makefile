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
	./gradlew test
	./gradlew jacocoTestReport

.PHONY: build
.PHONY: test
.PHONY: jacocoTestReport

