package be.raphtnt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a class that provides methods for reading CSV files,
 * retrieving random data elements from a list, and integrating these functionalities.
 */
public class Donnees {

    /**
     * Reads a CSV file and returns a list of string arrays representing each row of the CSV file.
     *
     * @param filePath the file path of the CSV file to read
     * @return a list of string arrays where each array represents a row in the CSV file
     */
    // Fonction pour lire un fichier CSV et retourner une liste
    public static List<String[]> readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();
        String separator = ","; // Définir le séparateur utilisé dans le CSV (par exemple, virgule)

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Divise la ligne en valeurs en utilisant le séparateur
                String[] values = line.split(separator);
                rows.add(values); // Ajoute le tableau de valeurs à la liste
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows; // Retourne la liste de lignes
    }

    /**
     * Retrieves a random element from a list.
     *
     * @param dataList The list from which to retrieve a random element.
     * @return A random element from the provided dataList as a String array.
     */
    // Fonction pour récupérer un élément aléatoire d'une liste
    public static String[] getRandomData(List<String[]> dataList) {
        Random random = new Random();
        int randomIndex = random.nextInt(dataList.size()); // Génère un index aléatoire
        return dataList.get(randomIndex); // Retourne la ligne aléatoire
    }

}
