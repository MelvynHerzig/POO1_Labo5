/**
 * Classe de test pour la classe Matrice.
 * Il est nécessaire d'entrer les arguments M1 N1 M2 N2 P
 * Les arguments correspondent respectivement à la heuteur largeur
 * de la première matrice, hauteur largeur de la seconde matrice et
 * le modulo qu'elles respectent.
 * Deux matrices de contenu aléatoire seront générées et testées.
 * D'autres tests sont effectué avec des matrices prédéterminées pour
 * valider les cas limites.
 *
 * @author Forestier Quentin, Herzig Melvyn
 * @version 14.10.2020
 */
public class Test
{


    public static void testStatic()
    {
        int modulus = 5;

        Matrice one = new Matrice(new int[][]{
                {1, 3, 1, 1},
                {3, 2, 4, 2},
                {1, 0, 1, 0}
        }, modulus);

        Matrice two = new Matrice(new int[][]{
                {1, 4, 2, 3, 2},
                {0, 1, 0, 4, 2},
                {0, 0, 2, 0, 2}
        }, modulus);

        System.out.println("The modulus is " + modulus + "\n");

        System.out.println("one");
        System.out.println(one);

        System.out.println("\ntwo");
        System.out.println(two);

        System.out.println("\none + two");
        System.out.println(one.add(two));

        System.out.println("\none - two");
        System.out.println(one.sub(two));

        System.out.println("\none x two");
        System.out.println(one.prod(two));
    }

    public static void testUserInput(int M1, int N1, int M2, int N2,
                                     int modulus)
    {

        Matrice three = new Matrice(M1, N1, modulus);

        Matrice four = new Matrice(M2, N2, modulus);

        System.out.println("The modulus is " + modulus + "\n");

        System.out.println("three");
        System.out.println(three);

        System.out.println("\nfour");
        System.out.println(four);

        System.out.println("\nthree + four");
        System.out.println(three.add(four));

        System.out.println("\nthree - four");
        System.out.println(three.sub(four));

        System.out.println("\nthree x four");
        System.out.println(three.prod(four));
    }

    public static void testLimitCase()
    {
        try
        {
            System.out.println("Test de matrice avec une taille négative :");
            Matrice negSize = new Matrice(-1, 4);

        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }

        System.out.println("\n");

        try
        {
            System.out.println("Test de matrice avec un modulo incorrecte :");
            Matrice badModulus = new Matrice( new int[][]{{1, 2, 3}, {2, 3, 4}},
                                             0);
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }

        System.out.println("\n");

        try
        {
            System.out.println("Test d'une opération avec un modulo différent" +
                               " :");
            Matrice m1 = new Matrice(2, 5);
            Matrice m2 = new Matrice(5, 4);

            m1.add(m2);
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }

        System.out.println("\n");

        try
        {
            System.out.println("Test création d'une matrice vide + addition :");
            Matrice m1 = new Matrice(0, 0, 4);
            Matrice m2 = new Matrice(3, 4, 4);

            System.out.println(m1);
            System.out.println(m2);

            System.out.println(m2.sub(m1));
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }

        System.out.println("\n");

        try
        {
            System.out.println("Test création d'une matrice vide à partir " +
                    "d'un tableau vide");
            int[][] tbl = {};
            Matrice vide = new Matrice(tbl, 6);

            System.out.println(vide);
        }
        catch (RuntimeException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Fonction principale de test et entrée du programme.
     *
     * @param args Dimensions et module des matrices à créer
     * @throws IllegalArgumentException Si un argument manque.
     * @throws NumberFormatException Si les argument ne sont pas des entiers.
     */
    public static void main(String[] args)
    {
        if (args.length != 5)
            throw new IllegalArgumentException("Args: M1 N1 M2 N2 module");
        int M1 = Integer.parseInt(args[0]);
        int N1 = Integer.parseInt(args[1]);
        int M2 = Integer.parseInt(args[2]);
        int N2 = Integer.parseInt(args[3]);

        int modulus = Integer.parseInt(args[4]);

        System.out.println("------------ Test de la donnée ------------");
        testStatic();

        System.out.println("\n------------ Test de l'entrée utilisateur " +
                "------------");
        testUserInput(M1, N1, M2, N2, modulus);

        System.out.println("\n------------ Test de cas limite ------------");
        testLimitCase();
    }
}
