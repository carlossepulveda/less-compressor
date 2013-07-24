package com.elibom.compressor;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Reader;
import java.io.Writer;
import org.apache.commons.io.IOUtils;
 
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.maven.plugin.logging.Log;
import ro.isdc.wro.extensions.processor.js.UglifyJsProcessor;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;
 
public class JSLintCompressor {

    private Log log;

    public void processFolder(String folderIn, String folderOut) {
        createFolder(folderOut);
        File folder = new File(folderIn);
        processFolder(folder, folderOut);
    }

    private void processFolder(File folder, String output) {
        log.info("Processing folder : " + folder.getAbsolutePath());
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

    public void processFile(File file, String outputFolder) {
        log.info("Processing file : " + file.getAbsolutePath());
        Reader in = null;
        Writer out = null;
        String outputFile = outputFolder + file.getName();
        try {
              Resource resource = Resource.create(file.getAbsolutePath(), ResourceType.JS);
              Reader reader = new FileReader(file.getAbsolutePath());
              Writer writer = new FileWriter(outputFile);
              UglifyJsProcessor c = new UglifyJsProcessor();
              c.process(resource, reader, writer);
        } catch (Exception e) {
            copyFile(file, outputFile);
            log.error("Error processing  : " + file.getName() + " " + e.getMessage());
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    private void createFolder(String path) {
        File newFolder = new File(path);
        newFolder.mkdir();
    }

    public void setLog(Log log) {
        this.log = log;
    }

    private void copyFile(File file, String outputFile) {
        InputStream inStream = null;
        OutputStream outStream = null;

        try{
            File bfile =new File(outputFile);

            inStream = new FileInputStream(file);
            outStream = new FileOutputStream(bfile);

            byte[] buffer = new byte[1024];

            int length;
            //copy the file content in bytes 
            while ((length = inStream.read(buffer)) > 0){
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();
        }catch(IOException e){
            log.error("Error copying file : " + outputFile);
            e.printStackTrace();
        }
    }
}
