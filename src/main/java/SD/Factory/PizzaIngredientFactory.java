package SD.Factory;

public interface PizzaIngredientFactory {

    public Dough createDough();
    public Sauce createSauce();
    public Cheese createCheese();
    public Veggies[] createVeggies();
    public Papperoni createPepperoni();
    public Clams createClam();

}