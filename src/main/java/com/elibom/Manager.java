package com.elibom;

//import com.elibom.lesscompiler.LESSCompiler;
import com.elibom.compiler.LESSCompiler;
import com.elibom.compressor.CSSCompressor;
import com.elibom.compressor.JSLintCompressor;
import java.io.File;
import org.apache.maven.plugin.logging.Log;

/**
 *
 * @author Carlos Sepulveda
 */
public class Manager {

    private File parent;

    private String lessInput;

    private String lessOutput;

    private String cssInput;

    private String cssOutput;

    private String jsInput;

    private String jsOutput;

    private Log log;

    public Manager(File parent) {
        this.parent = parent;
    }

    public void process() {
        compileLESS(lessInput, lessOutput);
        compressCSS(cssInput, cssOutput);
        compressJS(jsInput, jsOutput);
    }

    private void compileLESS(String in, String out) {
        String pathIn = parent.getAbsolutePath() + "/" + in;
        String pathOut = parent.getAbsolutePath() + "/" + out;
        if (existsFolder(pathIn) && existsFolder(pathOut)) {
            LESSCompiler compiler = new LESSCompiler(pathIn, pathOut);
            compiler.setLog(log);
            compiler.compile();
        } else {
            log.error("Imposible to compile LESS files because paths dont exist (" + pathIn + " , " + pathOut + ")");
        }
    }

    private void compressCSS(String in, String out) {
        String pathIn = parent.getAbsolutePath() + "/" + in;
        String pathOut = parent.getAbsolutePath() + "/" + out;
        if (existsFolder(pathIn) && existsFolder(pathOut)) {
            CSSCompressor processor = new CSSCompressor();
            processor.setLog(log);
            processor.processFolder(pathIn, pathOut);
        } else {
            log.error("Imposible to compress CSS files because paths dont exist (" + pathIn + " , " + pathOut + ")");
        }
    }

    private void compressJS(String in, String out) {
        String pathIn = parent.getAbsolutePath() + "/" + in;
        String pathOut = parent.getAbsolutePath() + "/" + out;
        if (existsFolder(pathIn) && existsFolder(pathOut)) {
            JSLintCompressor compressor = new JSLintCompressor();
            compressor.setLog(log);
            compressor.processFolder(pathIn, pathOut);
        } else {
            log.error("Imposible to compress JS files because paths dont exist (" + pathIn + " , " + pathOut + ")");
        }
    }

    private boolean existsFolder(String path) {
        try {
            return new File(path).exists();
        } catch (Exception e) {
            return false;
        }
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public String getLessInput() {
        return lessInput;
    }

    public void setLessInput(String lessInput) {
        this.lessInput = lessInput;
    }

    public String getLessOutput() {
        return lessOutput;
    }

    public void setLessOutput(String lessOutput) {
        this.lessOutput = lessOutput;
    }

    public String getCssInput() {
        return cssInput;
    }

    public void setCssInput(String cssInput) {
        this.cssInput = cssInput;
    }

    public String getCssOutput() {
        return cssOutput;
    }

    public void setCssOutput(String cssOutput) {
        this.cssOutput = cssOutput;
    }

    public String getJsInput() {
        return jsInput;
    }

    public void setJsInput(String jsInput) {
        this.jsInput = jsInput;
    }

    public String getJsOutput() {
        return jsOutput;
    }

    public void setJsOutput(String jsOutput) {
        this.jsOutput = jsOutput;
    }
}
