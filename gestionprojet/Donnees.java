import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Donnees {

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

    // Fonction pour récupérer un élément aléatoire d'une liste
    public static String[] getRandomData(List<String[]> dataList) {
        Random random = new Random();
        int randomIndex = random.nextInt(dataList.size()); // Génère un index aléatoire
        return dataList.get(randomIndex); // Retourne la ligne aléatoire
    }

    // Fonction principale qui intègre tout
    public static void fichier(String[] args) {
        // Chargement des données
        String CountriesfilePath = ".\\NomPays.csv";
        List<String[]> CountriesData = readCSV(CountriesfilePath);
        String NamefilePath = ".\\Nom.csv";
        List<String[]> NameData = readCSV(NamefilePath);
        String FirstNamefilePath = ".\\Prenom.csv";
        List<String[]> FirstNameData = readCSV(FirstNamefilePath);
        String StreetfilePath = ".\\Rue.csv";
        List<String[]> StreetData = readCSV(StreetfilePath);
        String PostalcodeLocalityfilePath = ".\\PostalcodeLocality.csv";
        List<String[]> PostalcodeLocalityData = readCSV(PostalcodeLocalityfilePath);

        // Récupérer des données aléatoires de chaque liste
        String[] randomCountry = getRandomData(CountriesData);
        String[] randomName = getRandomData(NameData);
        String[] randomFirstName = getRandomData(FirstNameData);
        String[] randomStreet = getRandomData(StreetData);
        String[] randomPostalcodeLocality = getRandomData(PostalcodeLocalityData);

        // Afficher les données aléatoires
        System.out.println("Données aléatoires sélectionnées :");
        System.out.println("Pays : " + String.join(", ", randomCountry));
        System.out.println("Nom : " + String.join(", ", randomName));
        System.out.println("Prénom : " + String.join(", ", randomFirstName));
        System.out.println("Rue : " + String.join(", ", randomStreet));
        System.out.println("Code postal et localité : " + String.join(", ", randomPostalcodeLocality));
        System.out.println(String.join(", ", randomFirstName)+" "+ String.join(", ", randomName) +" "+ String.join(", ", randomStreet)+" "+ String.join(", ", randomPostalcodeLocality)+" "+String.join(", ", randomCountry));
 

        // Affiche le contenu de la liste pour vérifier les données
        //for (String[] row : PostalcodeLocalityData) {
        //    for (String value : row) {
        //        System.out.print(value + " ");
        //    }
        //    System.out.println();
        //}
    }

    public static void main(String[] args) {
        fichier(args);
    }
}
