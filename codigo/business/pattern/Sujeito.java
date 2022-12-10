package business.pattern;

public interface Sujeito {

    public void observar(Observer observer);

    public void pararObservar(Observer observer);

    public void updateAll();
}
