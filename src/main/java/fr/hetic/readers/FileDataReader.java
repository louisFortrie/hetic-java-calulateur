package fr.hetic.readers;

import java.io.*;

public class FileDataReader implements DataReader{
    
    private final String inputDirectoryPath;

    public FileDataReader(String inputDirectoryPath) {
        this.inputDirectoryPath = inputDirectoryPath;
    }

    @Override
    public void process() {
        File inputDirectory = new File(inputDirectoryPath);
        processDirectory(inputDirectory);
    }

    public static void processDirectory(File directory) {
        File[] files = directory.listFiles(file -> file.getName().endsWith(".op") || file.isDirectory());
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    processDirectory(file);
                } else {
                    String outputFileName = file.getPath().replace(".op", ".res");
                    processFile(file, new File(outputFileName));
                }
            }
        } else {
            System.out.println("File or Directory not found " + directory.getPath());
            System.exit(1);
        }
    }

    public static void processFile(File inputFile, File outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try{

                    line = DataReader.processLine(line);
                    writer.write(line);
                    writer.newLine();
                }catch (Exception e){
                    writer.write(e.getMessage());
                    writer.newLine();
                }
            }
        } catch (Exception e) {

        }
    }
}
