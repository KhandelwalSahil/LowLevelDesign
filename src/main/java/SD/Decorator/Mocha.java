package SD.Decorator;

public class Mocha extends CondimentDecorator {

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDesc() {
        return beverage.getDesc() + " Mocha";
    }

    public double getCost() {
        return beverage.getCost() + .20;
    }
}
