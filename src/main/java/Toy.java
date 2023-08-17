import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Toy implements Comparable<Toy> {

    private int toyId;
    private String toyTitle;
    @Setter
    private int toyVictoryFrequency;

    public Toy(int toyId, String toyTitle, int toyVictoryFrequency) {
        this.toyId = toyId;
        this.toyTitle = toyTitle;
        this.toyVictoryFrequency = toyVictoryFrequency;
    }

    public String getInfo() {
        return String.format("ID: %d, Name: %s", toyId, toyTitle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toy toy = (Toy) o;
        return toyTitle.equals(toy.toyTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toyTitle);
    }

    @Override
    public int compareTo(Toy o) {
        return Integer.compare(this.toyVictoryFrequency, o.toyVictoryFrequency);
    }
}