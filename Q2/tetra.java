public class tetra extends fish
{
    private String Colour;
    private String Name;

    public tetra(int legs, String eats, String Type, String colour, String name) {
        super(legs, eats, Type);
        this.Colour = colour;
        this.Name = name;
    }
    public String getColour(){
        return Colour;
    }

    public String getName(){
        return Name;
    }
}