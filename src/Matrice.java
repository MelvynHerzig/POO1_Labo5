import javax.imageio.stream.ImageInputStream;
import java.util.Arrays;

/**
 * Class implémentant des matrices de taille personnalisable.
 * Le contenu est défini modulo N.
 * @author Forestier Quentin, Herzig Melvyn
 * @version 14.10.2020
 */
public class Matrice
{
    private int modulus;
    private int matrice[][];

    /**
     * Constructeur, génère la matrice d'après les dimensions en paramètre
     * ainsi que le contenu d'après le modulo.
     * @param aHeight Heuteur de la matrice.
     * @param aWidth Largeur de la matrice.
     * @param aModulus Modulo du contenu.
     * @throws IllegalArgumentException Dans le cas d'une dimension négative où
     *         d'un modulo inférieur à 1.
     */
    public Matrice(int aHeight, int aWidth, int aModulus)
    {
        if(aHeight < 0 || aWidth < 0 || aModulus < 1)
            throw new IllegalArgumentException();

        modulus = aModulus;

        matrice = new int[aHeight][aWidth];

        for (int M = 0; M < aHeight; ++M)
        {
            for (int N = 0; N < aWidth; ++N)
            {
                matrice[M][N] = (int) (Math.random() * modulus);
            }
        }
    }

    /**
     * Constructeur de matrice carrée d'après la taille en paramètre et
     * génère le contenu d'après le modulo.
     * @param size Hauteur et largeur de la matrice.
     * @param aModulus Modulo du contenu.
     */
    public Matrice(int size, int aModulus)
    {
        this(size, size, aModulus);
    }

    /**
     * Constructeur créée l'objet matrice d'après le tableau bidimensionnel
     * en paramètre. Corrige son contenu d'après le module.
     * @param aMatrice Matrice à integrer.
     * @param aModulus Module à appliquer.
     * @throws IllegalArgumentException Dans le cas d'un modulo inférieur à 1.
     */
    public Matrice(int aMatrice[][], int aModulus)
    {
        if(aModulus < 1) throw new IllegalArgumentException();

        modulus = aModulus;
        matrice = aMatrice;

        // Corrige le contenu pour respecter le modulo.
        for (int M = 0; M < this.height(); ++M)
        {
            for (int N = 0; N < this.width(); ++N)
            {
                matrice[M][N] = Math.floorMod(matrice[M][N], modulus);
            }
        }
    }

    /**
     * Retourne la taille de la matrice.
     * @return Taille de la matrice.
     */
    public int height()
    {
        return matrice.length;
    }

    /**
     * Retourne la largeur de la matrice
     * @return Largeur de la matrice.
     */
    public int width()
    {
        return matrice[0].length;
    }

    /**
     * Affiche le contenu de la matrice
     */
    public void displayContent()
    {
        for (int M = 0; M < this.height(); ++M)
        {
            for (int N = 0; N < this.width(); ++N)
            {
                System.out.print(matrice[M][N]);
            }
            System.out.print('\n');
        }
    }

    /**
     * Assemble les caractéristiques de la matrice pour les afficher.
     * @return String formaté avec les caractéristiques de la matrice.
     */
    @Override
    public String toString()
    {
        return "Matrice de hauteur " + this.height() +
               " de largeur " + this.width() +
               " de module " + modulus;
    }

    /**
     * Additionne la matrice courante avec la matrice m.
     * @param m Matrice, seconde opérande.
     * @return Matrice résultante de la somme.
     */
    public Matrice add(Matrice m)
    {
        return operation(m, Operator.ADDITION);
    }

    /**
     * Soustrait la matrice courante avec la matrice m.
     * @param m Matrice, seconde opérande.
     * @return Matrice résultante de la différence.
     */
    public Matrice sub(Matrice m)
    {
        return operation(m, Operator.SUBTRACTION);
    }

    /**
     * Multiple la matrice courante avec la matrice m.
     * @param m Matrice, seconde opérande.
     * @return Matrice résultante du produit.
     */
    public Matrice prod(Matrice m)
    {
        return operation(m, Operator.PRODUCT);
    }

    /**
     * Fonction principale de génération d'une matrice issue de deux matrices
     * combinées, la matrice courante et la matrice m.
     * @param m Matrice, seconde opérande.
     * @param operator Opération à appliquer entre les éléments Amn Bmn.
     * @return Retourne la matrice résultante de l'opération.
     */
    private Matrice operation(Matrice m, Operator operator)
    {
        if (this.modulus != m.modulus) throw new RuntimeException();

        Matrice result = new Matrice(Math.max(this.height(), m.height()),
                                     Math.max(this.width(), m.width()),
                                     this.modulus);

        int v1, v2;
        for(int M = 0; M < result.height(); ++M)
        {
            for (int N = 0; N < result.width(); ++N)
            {
                // Vérification si la case MxN est dans les tableaux.
                v1 = (M < this.height()  && N < this.width()) ? this.matrice[M][N] : 0;
                v2 = (M < m.height()     && N < m.width()   ) ? m.matrice[M][N]    : 0;

                result.matrice[M][N] = Math.floorMod(operator.apply(v1, v2), modulus);
            }
        }
        return result;
    }
}

/**
 * Enum des opérations applicables sur les Matrices
 * @author Forestier Quentin, Herzig Melvyn
 * @version 14.10.2020
 *
 * Inspiré de: https://stackoverflow.com/a/2902471
 */
enum Operator
{
    ADDITION
            {
                @Override
                public int apply(int x1, int x2)
                {
                    return x1 + x2;
                }
            },
    SUBTRACTION
            {
                @Override
                public int apply(int x1, int x2)
                {
                    return x1 - x2;
                }
            },
    PRODUCT
            {
                @Override
                public int apply(int x1, int x2)
                {
                    return x1 * x2;
                }
            };


    /**
     * Applique une opération sur x1 et x2.
     * @param x1 Premier nombre, élément de la première matrice.
     * @param x2 Second nombre, élément de la seconde matrice.
     * @return Résultat de l'opération entre x1 et x2.
     */
    public abstract int apply(int x1, int x2);
}
