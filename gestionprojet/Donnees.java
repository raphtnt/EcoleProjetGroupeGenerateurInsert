import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Donnees {

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

    public static void main(String[] args) {
        String CountriesfilePath = ".\\NomPays.csv";
        List<String[]> CountriesData = readCSV(CountriesfilePath);
        String NamefilePath = ".\\Nom.csv";
        List<String[]> NameData = readCSV(NamefilePath);
        String FirstNamefilePath = ".\\Prenom.csv";
        List<String[]> FristNameData = readCSV(FirstNamefilePath);
        String StreetfilePath = ".\\Rue.csv";
        List<String[]> StreetData = readCSV(StreetfilePath);
        String PostalcodeLocalityfilePath = ".\\PostalcodeLocality.csv";
        List<String[]> PostalcodeLocalityData = readCSV(PostalcodeLocalityfilePath);
        

        // Affiche le contenu de la liste pour vérifier les données
        for (String[] row : PostalcodeLocalityData) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
