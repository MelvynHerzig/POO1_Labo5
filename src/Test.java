/**
 * Classe de test pour la classe Matrice.
 * Il est nécessaire d'entrer les arguments M1 N1 M2 N2 I
 * Les arguments correspondent respectivement à la heuteur largeur
 * de la première matrice, hauteur largeur de la seconde matrice et
 * le modulo qu'elles respectent.
 * Deux matrices de contenu aléatoire seront générées et testées.
 * D'autres tests sont effectué avec des matrices prédéterminées pour
 * valider les cas limites.
 * @author Forestier Quentin, Herzig Melvyn
 * @version 14.10.2020
 */
public class Test
{
   /**
    * Fonction principale de test et entrée du programme.
    * @param args Dimensions et module des matrices à créer
    * @throws IllegalArgumentException Dans le cas d'une dimension négative où
    *         d'un modulo inférieur à 1.
    */
   public static void main(String[] args)
   {
      if(args.length != 5) throw new IllegalArgumentException("Args: M1 N1 M2 N2 module");
      int M1 =  Integer.parseInt(args[0]);
      int N1 =  Integer.parseInt(args[1]);
      int M2 =  Integer.parseInt(args[2]);
      int N2 =  Integer.parseInt(args[3]);

      // Tests selon donnée.
      Matrice one = new Matrice(new int[][]{
              {1, 3, 1, 1},
              {3, 2, 4, 2},
              {1, 0, 1, 0}}, 5);

      Matrice two = new Matrice(new int[][]{
              {1, 4, 2, 3, 2},
              {0, 1, 0, 4, 2},
              {0, 0, 2, 0, 2}}, 5);

      System.out.println("The modulus is 5\n");

      System.out.println("one");
      one.displayContent();

      System.out.println("\ntwo");
      two.displayContent();

      System.out.println("\none + two");
      one.add(two).displayContent();

      System.out.println("\none - two");
      one.sub(two).displayContent();

      System.out.println("\none x two");
      one.prod(two).displayContent();
   }
}
