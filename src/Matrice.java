/**
 * Class implémentant des matrices de taille personnalisable.
 * Le contenu est défini modulo N.
 * Les matrices peuvent être additionnées, soustraintes et multipliées.
 *
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
     *
     * @param aHeight  Heuteur de la matrice.
     * @param aWidth   Largeur de la matrice.
     * @param aModulus Modulo du contenu.
     * @throws RuntimeException Dans le cas d'une dimension négative où
     *                          d'un modulo inférieur à 1.
     */
    public Matrice(int aHeight, int aWidth, int aModulus)
    {
        if (aHeight < 0 || aWidth < 0 || aModulus < 1)
            throw new RuntimeException("Argument(s) invalide(s)");

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
     *
     * @param size     Hauteur et largeur de la matrice.
     * @param aModulus Modulo du contenu.
     */
    public Matrice(int size, int aModulus)
    {
        this(size, size, aModulus);
    }

    /**
     * Constructeur crée l'objet matrice d'après le tableau bidimensionnel
     * en paramètre. Corrige son contenu d'après le module.
     *
     * @param aMatrice Matrice à intégrer.
     * @param aModulus Module à appliquer.
     * @throws RuntimeException Dans le cas d'un modulo inférieur à 1.
     */
    public Matrice(int aMatrice[][], int aModulus)
    {
        if (aModulus < 1)
            throw new RuntimeException("Le modulo est < 1");

        modulus = aModulus;
        matrice = aMatrice;

        // Corrige le contenu pour respecter le modulo.
        for (int M = 0; M < this.getHeight(); ++M)
        {
            for (int N = 0; N < this.getWidth(); ++N)
            {
                matrice[M][N] = Math.floorMod(matrice[M][N], modulus);
            }
        }
    }

    /**
     * Retourne la hauteur de la matrice.
     *
     * @return Hauteur de la matrice.
     */
    public int getHeight()
    {
        return matrice.length;
    }

    /**
     * Retourne la largeur de la matrice
     *
     * @return Largeur de la matrice.
     */
    public int getWidth()
    {

        return matrice.length == 0 ? 0 : matrice[0].length;
    }


    /**
     * Retourne le modulo de la matrice
     *
     * @return Modulo de la matrice
     */
    public int getModulus()
    {
        return this.modulus;
    }

    /**
     * Affiche le contenu de la matrice.
     *
     * @return String de la matrice.
     */
    @Override
    public String toString()
    {
        if (getHeight() == 0 || getWidth() == 0) return "Matrice vide";
        StringBuilder result = new StringBuilder();
        for (int M = 0; M < this.getHeight(); ++M)
        {
            for (int N = 0; N < this.getWidth(); ++N)
            {
                result.append(matrice[M][N]);
            }
            result.append('\n');
        }
        return result.toString();

    }

    /**
     * Additionne la matrice courante avec la matrice m.
     *
     * @param m Matrice, seconde opérande.
     * @return Matrice résultante de la somme.
     */
    public Matrice add(Matrice m)
    {
        return operation(m, Operator.ADDITION);
    }

    /**
     * Soustrait la matrice courante avec la matrice m.
     *
     * @param m Matrice, seconde opérande.
     * @return Matrice résultante de la différence.
     */
    public Matrice sub(Matrice m)
    {
        return operation(m, Operator.SUBTRACTION);
    }

    /**
     * Multiple la matrice courante avec la matrice m.
     *
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
     *
     * @param m        Matrice (tableau d'entier 2D), seconde opérande.
     * @param operator Opération à appliquer entre les éléments Amn Bmn.
     * @return Retourne la matrice résultante de l'opération.
     */
    private Matrice operation(Matrice m, Operator operator)
    {
        if (this.modulus != m.modulus)
            throw new RuntimeException("Les modulos ne sont pas égaux");

        Matrice result = new Matrice(Math.max(this.getHeight(), m.getHeight()),
                Math.max(this.getWidth(), m.getWidth()),
                this.modulus);

        int v1, v2;
        for (int M = 0; M < result.getHeight(); ++M)
        {
            for (int N = 0; N < result.getWidth(); ++N)
            {
                // Vérification si la case MxN est dans les tableaux.
                v1 = (M < this.getHeight() && N < this.getWidth()) ?
                        this.matrice[M][N] : 0;
                v2 = (M < m.getHeight() && N < m.getWidth()) ?
                        m.matrice[M][N] : 0;

                result.matrice[M][N] = Math.floorMod(operator.apply(v1, v2),
                        modulus);
            }
        }
        return result;
    }
}

/**
 * Enum des opérations applicables sur les Matrices
 *
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
     *
     * @param x1 Premier nombre, élément de la première matrice.
     * @param x2 Second nombre, élément de la seconde matrice.
     * @return Résultat de l'opération entre x1 et x2.
     */
    public abstract int apply(int x1, int x2);
}
