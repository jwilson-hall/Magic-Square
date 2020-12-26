public class boxer extends dog
{
    private String Colour;
    private String Name;

    public boxer(int legs, String eats, String breed, String colour, String name) {
        super(legs, eats, breed);
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