less-compressor
===============

Maven plugin to compile less files and compress scripts like css and js


How to user it ? 

           <plugin>
                <groupId>com.elibom</groupId>
                <artifactId>less-compressor</artifactId>
                <version>1.0-SNAPSHOT</version>
                <configuration>
                    <lessInput>less</lessInput>
                    <lessOutput>static/stylesheets</lessOutput>
                    <foldersToCompressIn>
                        <param>static/stylesheets</param>
                        <param>static/javascripts</param>
                    </foldersToCompressIn>
                    <foldersToCompressOut>
                        <param>static/stylesheets</param>
                        <param>static/javascripts</param>
                    </foldersToCompressOut>
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
