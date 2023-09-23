package SD.Decorator;

public class StarBuzz {

    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDesc() + " " + beverage.getCost());

        Beverage mochaHouseBlend = new Mocha(new HouseBlend());
        System.out.println(mochaHouseBlend.getDesc() + " " + mochaHouseBlend.getCost());


    }
}
