package LuceneDemo;

import java.io.File; // Import the File class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.FileWriter; // Import the FileWriter class
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetClientGet {
    static String PATH = Constants.BASEPATH + Constants.DOCSPATH + "\\";
    public static class CreateFile {
        public CreateFile(String filenameString) {
            try {
                File myObj = new File(PATH+filenameString+".txt");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static class WriteToFile {
        public WriteToFile(String filenameString, String str) {
            try {
                FileWriter myWriter = new FileWriter(PATH+filenameString+".txt");
                myWriter.write(str);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            try {
                URL url = new URL("https://sajjadblogserver.vercel.app/api/blog/" + i);// your url i.e fetch data from .
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");
                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP Error code : "
                            + conn.getResponseCode());
                }
                InputStreamReader in = new InputStreamReader(conn.getInputStream());
                BufferedReader br = new BufferedReader(in);
                String output;
                String conteString = "";
                new CreateFile("file"+i);
                
                while ((output = br.readLine()) != null) {
                    conteString = conteString + (output+'\n');
                    
                }
                new WriteToFile("file"+i, conteString);
                conn.disconnect();

            } catch (Exception e) {
                System.out.println("Exception in NetClientGet:- " + e);
            }
        }
    }
}