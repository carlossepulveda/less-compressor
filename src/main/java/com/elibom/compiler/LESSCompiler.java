package com.elibom.compiler;

import java.io.File;
import org.apache.maven.plugin.logging.Log;
import org.lesscss.LessCompiler;

/**
 *
 * @author Carlos Sepulveda
 */
public class LESSCompiler {

    private String folderOut;

    private String folderIn;

    private Log log;

    public LESSCompiler(String folderIn, String folderOut) {
        this.folderIn = folderIn;
        this.folderOut = folderOut;
    }

    public void compile() {
        try {
            createFolder(folderOut);
            File folder = new File(folderIn);
            processFolder(folder, folderOut);
        } catch (Exception e) {
            log.info("Error trying to compile :  folderIn " + folderIn + " -  FolderOut " + folderOut, e);
        }
        
    }

    private void processFolder(File folder, String output) {
        log.info("Processing folder " + folder.getAbsolutePath() + " output : " + output);
        createFolder(output);
        File [] files = folder.listFiles();
        for (int i = 0; i < files.length ; i++) {
            File f = files[i];
            if(f.isDirectory()) {
                processFolder(f, output +f.getName() + "/");
            } else {
                processFile(f, output);
            }
        }
        log.info("Folder processed : " + folder.getAbsolutePath());
    }

    private void processFile(File file, String output) {
        log.info("Processing path : " + file.getAbsolutePath());
        String inputFile = file.getAbsolutePath();
        String outputFile = output + file.getName().replace(".less", ".css");
        LessCompiler lessCompiler = new LessCompiler();
        try {
            lessCompiler.compile(new File(inputFile), new File(outputFile));
        } catch (Exception e) {
            log.error("Error processing file " + file.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private void createFolder(String path) {
        File newFolder = new File(path);
        newFolder.mkdir();
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
