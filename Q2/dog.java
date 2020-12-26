public class dog extends pet{

    private String Breed;

    public dog(int legs, String eats, String breed) {
        super(legs, eats);
        this.Breed = breed;
    }

	public String getBreed(){
        return Breed;
    }
}