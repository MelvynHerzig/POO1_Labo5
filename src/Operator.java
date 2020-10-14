public enum Operator
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
    // On peut ajouter d'autres opérateurs ici


    public abstract int apply(int x1, int x2);

    }