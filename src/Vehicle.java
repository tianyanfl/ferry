
public abstract class Vehicle {

    private int price;
    private int weight;

    /**
     * Construct
     */
    public Vehicle(int price, int weight) {
        this.price = price;
        this.weight = weight;
    }

    /**
     * get price
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * set price
     * @param price the price
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * get weight
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * set weight
     * @param weight the weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }
}
