public class Matrice
{

    // Attributs

    private int modulus;
    private int height;
    private int width;

    private int matrice[][];

    // Constructeurs
    public Matrice(int aHeight, int aWidth, int aModulus)
    {
        height = aHeight;
        width = aWidth;
        modulus = aModulus;

        matrice = new int[height][width];

        for (int M = 0; M < height; ++M)
        {
            for (int N = 0; N < width; ++N)
            {
                matrice[M][N] = (int) (Math.random() * modulus);
            }
        }
    }

    public Matrice(int size, int aMaxVal)
    {
        this(size, size, aMaxVal);
    }

    public Matrice(int aMatrice[][], int aModulus)
    {
        modulus = aModulus;
        height = aMatrice.length;
        width = aMatrice[0].length;

        matrice = aMatrice;
        correctContent();
    }

    // Méthodes

    public void showContent()
    {
        for (int M = 0; M < height; ++M)
        {
            for (int N = 0; N < width; ++N)
            {
                System.out.print(matrice[M][N]);
            }
            System.out.print('\n');
        }
    }

    public Matrice add(Matrice m)
    {
        return operation(m, Operator.ADDITION);
    }

    public Matrice sub(Matrice m)
    {

        return operation(m, Operator.SUBTRACTION);
    }

    public Matrice prod(Matrice m)
    {
        return operation(m, Operator.PRODUCT);
    }

    private Matrice operation(Matrice m, Operator operator)
    {
        if (this.modulus != m.modulus) throw new RuntimeException();

        Matrice result = new Matrice(Math.max(this.height, m.height),
                Math.max(this.width, m.width),
                this.modulus);


        for (int M = 0; M < result.height; ++M)
        {
            for (int N = 0; N < result.width; ++N)
            {
                if (M >= this.height || N >= this.height)
                {
                    result.matrice[M][N] = m.matrice[M][N];
                }
                else if (M >= m.height || N >= m.height)
                {
                    result.matrice[M][N] = this.matrice[M][N];
                }
                else
                {
                    result.matrice[M][N] = Math.floorMod(
                            operator.apply(this.matrice[M][N], m.matrice[M][N]), modulus);
                }
            }
        }

        return result;
    }


    private void correctContent()
    {
        for (int M = 0; M < height; ++M)
        {
            for (int N = 0; N < width; ++N)
            {
                matrice[M][N] = Math.floorMod(matrice[M][N], modulus);
            }
        }
    }

    public static void main(String[] args)
    {
        //Matrice one = new Matrice(4, 5);
        //Matrice two = new Matrice(3, 5, 5);


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
        one.showContent();

        System.out.println("\ntwo");
        two.showContent();

        System.out.println("\none + two");
        one.add(two).showContent();

        System.out.println("\none - two");
        one.sub(two).showContent();

        System.out.println("\none x two");
        one.prod(two).showContent();
    }
}
