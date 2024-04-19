package fr.hetic.readers;

import java.sql.*;

import fr.hetic.entities.FileEntity;
import fr.hetic.entities.LineEntity;

import java.io.*;

public class JDBCDataReader implements DataReader {
       private String url = "jdbc:postgresql://SG-hetic-mt4-java-5275-pgsql-master.servers.mongodirector.com:5432/TP";
    private String user = "etudiant";
    private String password = "MT4@hetic2324";

    @Override
    public void process() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            String sqlFichier = "SELECT * FROM FICHIER WHERE TYPE = 'OP'";

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlFichier)) {

                while (rs.next()) {
                    FileEntity file = new FileEntity();
                    file.setId(rs.getInt("ID"));
                    file.setNom(rs.getString("NOM"));
                    file.setType(rs.getString("TYPE"));

                    processFileFromDB(file, conn);
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
     public static void processFileFromDB(FileEntity fileEntity, Connection conn){
        String outputFilePath = fileEntity.getNom() + ".res";
        File outputFile = new File(outputFilePath);

        String sqlLigne = "SELECT * FROM LIGNE WHERE FICHIER_ID = " + fileEntity.getId();

        try (Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sqlLigne);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            while (rs.next()) {
                LineEntity line = new LineEntity();
                line.setId(rs.getInt("ID"));
                line.setParam1(rs.getInt("PARAM1"));
                line.setParam2(rs.getInt("PARAM2"));
                line.setOperateur(rs.getString("OPERATEUR").charAt(0));
                line.setFichierId(rs.getInt("FICHIER_ID"));

                try {
                   String lineString = line.getParam1() + " " + line.getParam2() + " " + line.getOperateur();
                   String result = DataReader.processLine(lineString);
                   writer.write(result);
                   writer.newLine();
                } catch (Exception e) {
                    writer.write(e.getMessage());
                    writer.newLine();
                }
            }
        } catch (SQLException | IOException e) {
            System.out.println("An error occurred processing file " + fileEntity.getNom() + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
}
