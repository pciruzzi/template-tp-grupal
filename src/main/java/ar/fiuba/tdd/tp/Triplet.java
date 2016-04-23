package ar.fiuba.tdd.tp;

/**
 * Created by panchoubuntu on 23/04/16.
 */
public class Triplet<T, U, V> {
    T a;
    U b;
    V c;

    Triplet(T a, U b, V c)
    {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public T getA(){ return a;}
    public U getB(){ return b;}
    public V getC(){ return c;}
}
