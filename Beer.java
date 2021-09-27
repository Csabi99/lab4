import java.io.Serializable;

public class Beer implements Serializable {
    private String name;
    private String style;
    private double strength;

    public Beer(String n, String s, double str) {
        name=n;
        style=s;
        strength=str;
    }


    public String getName() {
        return name;
    }
    public String getStyle() {
        return style;
    }
    public double getStrength() {
        return strength;
    }
    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", style='" + style + '\'' +
                ", strength=" + strength +
                '}';
    }
}
