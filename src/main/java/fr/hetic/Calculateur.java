package fr.hetic;

import fr.hetic.readers.*;

import java.util.Properties;
import java.io.*;

class LineProcessingException extends Exception {
    public LineProcessingException(String message) {
        super(message);
    }
}

public class Calculateur {
    public static void main(String[] args) throws LineProcessingException {
        Properties properties = new Properties();
        try (InputStream input = Calculateur.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }

            properties.load(input);
            String type = properties.getProperty("reader.type").trim();
            DataReader dataReader;
          
            if ("JDBC".equals(type)) {
                dataReader = new JDBCDataReader();
            } else if ("FILE".equals(type)) {
                if (args.length != 1) {
                    System.out.println("Expected : java -jar Exercice7.jar <inputName | directoryName>");
                    return;
                }
                String inputDirectoryPath = args[0];
                dataReader = new FileDataReader(inputDirectoryPath);
            } else {
                System.out.println("Invalid type specified " + type);
                return;
            }

            dataReader.process();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
   
}