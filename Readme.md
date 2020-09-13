[![Apache Sling](https://sling.apache.org/res/logos/sling.png)](https://sling.apache.org)

&#32;[![Build Status](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-kickstart-maven-plugin/job/master/badge/icon)](https://ci-builds.apache.org/job/Sling/job/modules/job/sling-kickstart-maven-plugin/job/master/)&#32;[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-kickstart-maven-plugin&metric=coverage)](https://sonarcloud.io/dashboard?id=apache_sling-kickstart-maven-plugin)&#32;[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=apache_sling-kickstart-maven-plugin&metric=alert_status)](https://sonarcloud.io/dashboard?id=apache_sling-kickstart-maven-plugin)&#32;[![JavaDoc](https://www.javadoc.io/badge/org.apache.sling/sling-kickstart-maven-plugin.svg)](https://www.javadoc.io/doc/org.apache.sling/kickstart-maven-plugin)&#32;[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.apache.sling/sling-kickstart-maven-plugin/badge.svg)](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.apache.sling%22%20a%3A%22sling-kickstart-maven-plugin%22) [![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# Sling Start Feature Maven Plugin

This Maven Plugin is the Feature Model based version of the Slingstart
Maven Plugin. It does not depend on its predecessor to keep the Provisioning Model
and Feature Model code bases separate.

## Build

This plugin is built as usual with:
```
mvn clean install
```

## Usage

The plugin can be used like this:
```
<plugin>
    <groupId>org.apache.sling</groupId>
    <artifactId>sling-kickstart-maven-plugin</artifactId>
    <version>0.0.8</version>
    <extensions>true</extensions>
    <executions>
        <execution>
            <id>start-container</id>
            <goals>
                <goal>start</goal>
                <goal>stop</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <parallelExecution>false</parallelExecution>
        <servers>
            <server>
                <port>${http.port}</port>
                <controlPort>${sling.control.port}</controlPort>
                <debug>true</debug>
                <stdOutFile>kickstart.out</stdOutFile>
            </server>
        </servers>
    </configuration>
</plugin>
```

## Customize Kickstart Dependency

The Plugin comes with the latest released version of the Kickstart dependency.
That said it provides the ability to use a different Kickstart version. These
are the two options:

* **kickstartJar**: path to the Kickstart JAR file
* **kickstartDependency**: Maven Dependency of the Kickstart artifact

## Notes

For now this Plugin only supports the starting and stopping of a Sling
instance for example to run IT tests.
