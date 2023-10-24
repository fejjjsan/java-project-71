# CLI difference generator for JSON and YML

[![Actions Status](https://github.com/fejjjsan/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/fejjjsan/java-project-71/actions)
![Actions Status](https://github.com/fejjjsan/java-project-71/actions/workflows/project-71-check.yml/badge.svg)
[![Maintainability](https://api.codeclimate.com/v1/badges/e6593b99af8961fe8f73/maintainability)](https://codeclimate.com/github/fejjjsan/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e6593b99af8961fe8f73/test_coverage)](https://codeclimate.com/github/fejjjsan/java-project-71/test_coverage)

CLI that receives two json/yml files and shows difference between them in different styles.
Default style is set to stylish. When used with **-f, --format=format** option, format should be specified.

## Prerequisites
* OpenJDK version >= 20.0.1
* Gradle >= 8.2.1


## Demo

```demo#
➜  app git:(main) ✗ ./build/install/app/bin/app -help                                     
Usage: gendiff [-hV] [-f=format] filepath1 filepath2
Compares two configuration files and shows a difference.
      filepath1         path to first file
      filepath2         path to second file
  -f, --format=format   output format [default:stylish]
  -h, --help            Show this help message and exit.
  -V, --version         Print version information and exit.

// use case without -f option

➜  app git:(main) ✗ ./build/install/app/bin/app jsonfile1.json jsonfile2.json
{
  - follow: false
    host: hexlet.io
  - proxy: 123.234.53.22
  - timeout: 50
  + timeout: String
  + verbose: true
}

// use case with -f option and style provided 

➜  app git:(main) ✗ ./build/install/app/bin/app -f plain jsonNested1.json jsonNested2.json
Property 'chars2' was updated. From [complex value] to false
Property 'checked' was updated. From false to true
Property 'default' was updated. From null to [complex value]
Property 'id' was updated. From 45 to null
Property 'key1' was removed
Property 'key2' was added with value: 'value2'
Property 'numbers2' was updated. From [complex value] to [complex value]
Property 'numbers3' was removed
Property 'numbers4' was added with value: [complex value]
Property 'obj1' was added with value: [complex value]
Property 'setting1' was updated. From 'Some value' to 'Another value'
Property 'setting2' was updated. From 200 to 300
Property 'setting3' was updated. From true to 'none'
```



## Setup

```bash
make build
```

## Run

```bash
make run
```

## Run-dist
```bash
make run-dist
```

## Run tests

```bash
make test
```

## Run checkstyle

```bash
make lint
```

## Author

👤 **Artur Lastovskiy**

- Github: [@fejjsan](https://github.com/fejjjsan)