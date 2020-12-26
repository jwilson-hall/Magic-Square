public class pet
{
    public static void main(String args[])
    {
        boxer jeff = new boxer(4, "eats", "breed", "colour", "name");
        System.out.println(jeff.getName());
    }
    private int legs;
    private String eats;

    public pet(int legs, String eats){
        this.legs = legs;
        this.eats = eats;
    }

    public int getNumberOfLegs(){
        return legs;
    }
    public String getEat(){
        return eats;
    }

    public void setEat(String eats){
        this.eats = eats;
    }
    public void setLegs(int legs){
        this.legs = legs;
    }
}