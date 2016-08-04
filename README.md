# MyBatis Generator Criteria plugin

A plugin for [MyBatis Generator](http://mybatis.github.io/generator/)

## Using the plugin

First things first, clone this repository locally and run:

    mvn clean install

Then, in your MyBatis Generator configuration, include the plugin:

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE generatorConfiguration
            PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
            "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

    <generatorConfiguration>
        <context id="example"
                 targetRuntime="MyBatis3Simple"
                 defaultModelType="flat">
            <!-- include the plugin -->
            <plugin type="com.github.tj123.mybatis.generator.CriteriaPlugin"/>

            <!-- other configurations -->

        </context>
    </generatorConfiguration>

If you run MyBatis Generator from Maven, you can add the plugin as a dependency
for mybatis-generator-maven-plugin:

    <plugin>
        <groupId>org.mybatis.generator</groupId>
        <artifactId>mybatis-generator-maven-plugin</artifactId>
        <version>${mybatis.generator.version}</version>
        <configuration>
            <overwrite>true</overwrite>
        </configuration>
        <dependencies>
            <dependency>
                <groupId>com.github.tj123</groupId>
                <artifactId>mybatis-generator-criteria-plugin</artifactId>
                <version>1.0-SNAPSHOT</version>
            </dependency>
        </dependencies>
    </plugin>


## Author

[tj123](https://github.com/tj123)