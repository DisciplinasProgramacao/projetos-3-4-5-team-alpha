package business.pattern;

public interface Sujeito {

    public void addObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void updateAll();
}
