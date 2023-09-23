package SD.Decorator;

public abstract class CondimentDecorator extends Beverage {

    Beverage beverage;

    public abstract String getDesc();
}