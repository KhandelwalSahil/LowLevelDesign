package SD.Decorator;

public class Soy extends CondimentDecorator {

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    public String getDesc() {
        return beverage.getDesc() + " Soy";
    }

    public double getCost() {
        double cost = beverage.getCost();
        switch (beverage.getSize()) {
            case TALL:
                cost += .20;
                break;
            case GRANDE:
                cost += .15;
                break;
            case VENTI:
                cost += .10;
                break;
        }
        return cost;
    }
}
