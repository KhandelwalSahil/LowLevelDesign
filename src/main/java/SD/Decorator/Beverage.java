package SD.Decorator;

public abstract class Beverage {

    String desc = "Unknown Beverage";
    public enum Size {TALL, GRANDE, VENTI};
    Size size = Size.TALL;

    public String getDesc() {
        return desc;
    }
    public abstract double getCost();

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
