package com.elibom;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

/**
 * Goal which touches a timestamp file.
 *
 * @goal compressor
 * 
 * @phase process-sources
 */
public class MyMojo extends AbstractMojo {
    /**
     * Location of the file.
     * @parameter expression="${project.build.directory}"
     * @required
     */
    private File outputDirectory;

    /**
    * LESS Input.
    *
    * @parameter
    */
    private String lessInput;

    /**
    * LESS Output.
    *
    * @parameter
    */
    private String lessOutput;

    /**
    * CSS Input.
    *
    * @parameter
    */
    private String cssInput;

    /**
     * CSS Output
     * 
     * @parameter
     */
    private String cssOutput;

    /**
    * CSS Input.
    *
    * @parameter
    */
    private String jsInput;

    /**
     * CSS Output
     * 
     * @parameter
     */
    private String jsOutput;

    public void execute() throws MojoExecutionException {
        Manager manager = new Manager(outputDirectory.getParentFile());
        manager.setLog(getLog());

        manager.setLessInput(lessInput);
        manager.setLessOutput(lessOutput);

        manager.setCssInput(cssInput);
        manager.setCssOutput(cssOutput);

        manager.setJsInput(jsInput);
        manager.setJsOutput(jsOutput);

        manager.process();
    }
}
