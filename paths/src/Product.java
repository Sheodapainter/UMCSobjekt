public abstract class Product {
    private String name;
    public Product (String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract double getPrice(int year, int month);
}
