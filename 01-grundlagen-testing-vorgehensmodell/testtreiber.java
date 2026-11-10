public class TestTreiber {

    static boolean test_calculate_price() {
        double price;
        boolean test_ok = true;

        price = Preisberechnung.calculatePrice(20000, 3000, 1000, 0, 0);
        if (price != 24000) test_ok = false;

        price = Preisberechnung.calculatePrice(20000, 3000, 1000, 3, 0);
        if (price != 23900) test_ok = false;

        price = Preisberechnung.calculatePrice(20000, 3000, 1000, 5, 0);
        if (price != 23850) test_ok = false;

        price = Preisberechnung.calculatePrice(20000, 3000, 1000, 2, 10);
        if (price != 22000) test_ok = false;

        return test_ok;
    }

    public static void main(String[] args) {
        System.out.println(test_calculate_price());
    }
}