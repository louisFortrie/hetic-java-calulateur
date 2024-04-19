package fr.hetic;

import fr.hetic.readers.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


class LineProcessingException extends Exception {
    public LineProcessingException(String message) {
        super(message);
    }
}

public class Calculateur {
    public static void main(String[] args) throws LineProcessingException {
           try {
            ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
            DataReader dataReader = (DataReader) context.getBean("dataReader");

            dataReader.process();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    }
   
