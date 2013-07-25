less-compressor
===============

Maven plugin to compile less files and compress scripts like css and js


How to use it ? 

1) Add pluguinRepository

        <pluginRepositories>
          <pluginRepository>
            <id>elibom-snapshots</id>
              <name>less-compressor</name>
              <url>http://repository.elibom.net/nexus/content/repositories/snapshots</url>
          </pluginRepository> 
        </pluginRepositories>

2) Add dependency

        <dependency>
          <groupId>com.elibom</groupId>
          <artifactId>less-compressor</artifactId>
          <version>1.0-SNAPSHOT</version>
          <type>maven-plugin</type>
        </dependency>

3) Configure

        <plugin>
          <groupId>com.elibom</groupId>
          <artifactId>less-compressor</artifactId>
          <version>1.0-SNAPSHOT</version>
          <configuration>
            <lessInput>less</lessInput>
            <lessOutput>static/stylesheets</lessOutput>
            <cssInput></cssInput>
            <cssOutput></cssOutput>
            <jsInput></jsInput>
            <jsOutput></jsOutput>
          </configuration>
          <executions>
            <execution>
              <phase>compile</phase>
              <goals>
                <goal>compressor</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
          <executions>
            <execution>
              <phase>compile</phase>
              <goals>
                <goal>compressor</goal>
              </goals>
            </execution>
          </executions>
        </plugin>
