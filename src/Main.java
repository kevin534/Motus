import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    //todo: fonction principale main
    public static void main(String[] args)
    {
        List<String> listeDesMots = liste();
        String motAleatoire = "";
        System.out.println("Bienvenue dans Motus"+ "\n");
        int index = getNombreAlleatoire(0,4);
        String recomencer = "0";
        while (recomencer.equalsIgnoreCase("0")){

            //todo:Appeler la fonction choisirMot qui permettra de choisir le mot caché à partir de la liste.
            motAleatoire = choisirMot(listeDesMots,index);
            System.out.println("Le mot aléatoire tiré de la liste est : "+motAleatoire);

            //todo: Afficher le nombre de caractères du mot caché et la première lettre.
            System.out.println("Le mot Caché contient "+motAleatoire.length()+ " Caractères et commence par la lettre  "+ motAleatoire.charAt(0));

            int tentative = 6;
            boolean flag = false;
            boolean motBienSaisi = false;
            while (tentative > 0){
                System.out.println("Il vous reste "+tentative+" coups à jouer");
                System.out.println("Entrez votre mot de  "+motAleatoire.length()+ " Caractères ");
                String motSaisie = getMot(motAleatoire.length());
                System.out.println("le mot Saisi est: "+motSaisie);
               motBienSaisi = testMot(motAleatoire , motSaisie);

               if(motBienSaisi){
                   System.out.println("Bravo vous avez gagné, le mot caché était : "+motAleatoire);
               }
               else{
                   System.out.println("Désole vous avez perdu ! Le mot caché était : " +motAleatoire);
               }
                tentative -= 1;


                if(tentative == 0){
                    System.out.println("Il vous reste "+ tentative+ " coups à jouer");
                    System.out.println("Désole vous avez perdu ! Le mot caché était : " +motAleatoire);
                    flag = true;
                }

            }

                    System.out.println("Voulez-vous rejouer à ce jeux ? (o/n)");
            recomencer = scanner.next();
        }


    }

    /*
    Créer une fonction testMot(String motCache, String motSaisie) qui va vérifier si les caractères
contenus dans le mot caché correspondent à des caractères contenus dans le mot saisie. Elle
affichera les lettres bien placées en rouge et les lettres mal placées en jaune (utiliser la fonction
afficherCouleur pour afficher une lettre avec la bonne couleur). Elle retournera un booléen dont la
valeur sera true si le joueur a trouvé le mot caché.
     */


    public static boolean testMot(String motCache, String motSaisie){
        boolean motTrouver = false;
        System.out.println("Votre mot contient : ");
        for(int i = 0; i < motCache.length(); i++){
            // récupère chaque caractère du motCaché
            char character = motCache.charAt(i);
            // controlle si la lèttre est present dans le mot
            if(motSaisie.indexOf(character) != -1){
                //la lèttre est à la bonne place
                if(motSaisie.indexOf(character) == i){
                    afficherCouleur(motSaisie.charAt(i),"ANSI_RED");
                    motTrouver = true;
                }
                else{
                    afficherCouleur(motSaisie.charAt(i),"ANSI_YELLOW");
                    motTrouver = false;
                }
            }
            else{
                afficherCouleur(motSaisie.charAt(i),"WHITE");
                motTrouver = false;
            }
        }
        System.out.println();
        return  motTrouver;
    }

    //todo: choisi un mot aléatoire dans la liste de mots
    public static String choisirMot(List<String> mots,int index){
        return  mots.get(index).toUpperCase();
    }

    //todo: retourne la liste de string
    public static List<String> liste(){
        List<String> listeDesMots = new ArrayList<>();
        listeDesMots.add("abaissa");
        listeDesMots.add("abaissai");
        listeDesMots.add("abaissas");
        listeDesMots.add("abaissat");
        listeDesMots.add("abaisse");
        return listeDesMots;
    }

    //todo: retourne un nombre aléatoire
    private static int getNombreAlleatoire(int min, int max ){
        Random random = new Random();
        int nb;
        nb = min+random.nextInt(max-min);
        return nb;
    }

    //todo: lire la liste des mots et récuperer les élements de longueur entre 6 et 8
    private static List<String> Mot(String uri) {
        //List<String> listeDesMots = new ArrayList<>();
        //listeDesMots = Mot("https://www.lri.fr/~fpirot/mot/scrabble.json");
        return null;
    }

    //todo: affiche le texte avec la couleur démandée en utilisant les codes ANSI
    public static void afficherCouleur(Character texte , String couleur){

        if(couleur.equals("ANSI_RED")){
            System.out.print("\u001B[31m" + texte + "\u001B[0m");
        }
        else if(couleur.equals("ANSI_YELLOW")){
            System.out.print("\u001B[33m" + texte + "\u001B[0m");
        }
        else{
            System.out.print(texte);
        }
    }

    //todo: retourne un mot de longueur lenMot saisi par le joueur
    public static String getMot(int lenMot){
        System.out.println("Saisissez un mot de longueur: "+lenMot);
       String motEntrerParLeJoueur = "";
        boolean entrer = false;
        while (!entrer){
             motEntrerParLeJoueur = scanner.nextLine();
            System.out.println(motEntrerParLeJoueur.length());
            if(motEntrerParLeJoueur.length() == lenMot){
                entrer = true;
            }
            else {
                System.out.println("Saisissez a nouveau le mot de longueur: "+lenMot);
            }
        }
        return motEntrerParLeJoueur.toUpperCase();
    }



}