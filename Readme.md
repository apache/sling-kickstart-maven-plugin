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
    <version>0.0.1-SNAPSHOT</version>
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
