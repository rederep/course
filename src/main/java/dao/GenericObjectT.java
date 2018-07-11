package dao;

public class GenericObjectT<T> {
    private T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public GenericObjectT(T t) {
        this.t = t;
    }

    public GenericObjectT() {
    }
}