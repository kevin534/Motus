import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Motus {

    //todo: Noms du Groupe pour le TP Motus:
        //todo: TOUKAM  KEVIN  XAVIER
        //todo: MBAYE   NDIAYE
        //todo: STEN    TCHANDE
    public static Scanner scanner = new Scanner(System.in);

    //todo: fonction principale main qui exécute le code
    public static void main(String[] args)
    {
       List<String> listeDesMots = chargerMot();
        int max = 0;
        for(String s: listeDesMots){
            max ++;
        }
        String motAleatoire = "";
        System.out.println("Bienvenue dans Motus"+ "\n");
        //todo: retourne l'indice alléatoire du mot à choisir dans la liste
        int index = getNombreAlleatoire(0,max);
        String recomencer = "0";
        while (recomencer.equalsIgnoreCase("0")){

            //todo:Appeler la fonction choisirMot qui permettra de choisir le mot caché à partir de la liste des mots.
            motAleatoire = choisirMot(listeDesMots,index);

            System.out.println("Le mot aléatoire tiré de la liste est : "+motAleatoire);

            System.out.println("Le mot Caché contient "+motAleatoire.length()+ " Caractères et commence par la lettre  "+"\u001B[31m" + motAleatoire.charAt(0) + "\u001B[0m");

            int tentative = 6;
            boolean flag = false;
            boolean motBienSaisi = false;
            while (tentative > 0){
                String motSaisie = getMot(motAleatoire.length());
                System.out.println("Votre mot Saisi est: "+motSaisie);

                motBienSaisi = testMot(motAleatoire , motSaisie);
                if(motBienSaisi){
                    System.out.println("Bravo vous avez gagné, le mot caché était : "+motAleatoire);
                    System.out.println("Voulez-vous rejouer à ce jeux ? (o/n)");
                    recomencer = scanner.next();
                }
                else{
                    System.out.println("Il vous reste "+ tentative + " coups à jouer");
                }
               tentative -= 1;
            }

            System.out.println("Désole vous avez perdu ! Le mot caché était : " +motAleatoire);
            System.out.println("Voulez-vous rejouer à ce jeux ? (o/n)");
            recomencer = scanner.next();
        }
    }

    //todo:  retourne true si le motSaisi est identique au motCache
    public static boolean testMot(String motCache, String motSaisie)
    {
        boolean motTrouver = false;
        int nbLettreBienPlace = 0;
        System.out.println("Votre mot contient : ");

        for (int i = 0; i < motCache.length(); i++) {
           int  nbLettreMauvaise = 0;
            int c = 0;
            //todo: controlle si les lettres bien placées
            if (motCache.charAt(i) == motSaisie.charAt(i)) {
                nbLettreBienPlace ++;
                //todo:  affiche la couleur rouge pour les lettres bien placées
                afficherCouleur(motSaisie.charAt(i),"ANSI_RED");

                if(nbLettreBienPlace == motCache.length()){
                    motTrouver = true;
                }
            } else {
                //todo:Partie pour definir la couleur des lettres qui sont dans le mot mais au mauvais endroit.
                for (int k = 0; k < motCache.length(); k++) {
                    if (motSaisie.charAt(i) == motCache.charAt(k) && k!=i) {
                        c++;
                    }
                }
                if (c>=1) {
                    //todo: affiche la couleur jaune pour les lettres mal placées
                    afficherCouleur(motSaisie.charAt(i),"ANSI_YELLOW");
                }
            }
            //todo:Partie pour définir la couleur des lettres mauvaises.
            for (int j = 0; j < motCache.length(); j++) {
                if (motSaisie.charAt(i) != motCache.charAt(j))
                    nbLettreMauvaise++;
            }
            //todo:nbLettreMauvaise pour vérifier que la lettre selectionée est bien différente de toute les autres.
            if (nbLettreMauvaise == motCache.length()) {
                //todo: affiche la lèttre suivis de la couleur blanche
                afficherCouleur(motSaisie.charAt(i),"WHITE");
            }
        }
        System.out.println();
        return  motTrouver;
    }

    //todo: affiche les mots suivis de la couleur démandée en utilisant les codes ANSI
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

    //todo: choisi un mot aléatoire dans la liste de mots
    public static String choisirMot(List<String> mots,int index){
        return  mots.get(index).toUpperCase();
    }

    //todo: lire la liste des mots et récuperer les mots de longueur comprise entre 6 et 8
    public static List<String> chargerMot(){
        List<String> listeDesMots = new ArrayList<>();
        try
        {
            File file = new File("./src/fileMot.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String line;
            while((line = br.readLine()) != null)
            {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            String[] lines = sb.toString().split("\\n");

            //todo: ajoute uniquement les mot de longueur comprise entre 6 et 8 à la liste des mots
            for(String s: lines){
                if(s.length() > 5 && s.length() < 9){
                    listeDesMots.add(s);
                }

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return listeDesMots;
    }
    //todo: retourne un nombre aléatoire
    private static int getNombreAlleatoire(int min, int max ){
        Random random = new Random();
        int nb;
        nb = min+random.nextInt(max-min);
        return nb;
    }

    //todo: retourne le mot saisi par le joueur de longueur lenMot
    public static String getMot(int lenMot){
        System.out.println("Entrez votre mot de  "+lenMot+ " Caractères :  ");
        String motEntrerParLeJoueur = "";
        boolean entrer = false;
        while (!entrer){
            motEntrerParLeJoueur = scanner.nextLine();
            if(motEntrerParLeJoueur.length() == lenMot){
                entrer = true;
            }
            else {
                System.out.println("Entrez à nouveau votre mot de  "+lenMot+ " Caractères.");
            }
        }
        return motEntrerParLeJoueur.toUpperCase();
    }


}