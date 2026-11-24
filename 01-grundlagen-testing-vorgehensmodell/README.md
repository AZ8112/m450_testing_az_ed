# Testing Basics

## Task 1

What kinds of tests do you know from computer science?
Work in pairs and explain at least three examples you know from practice.
How are the tests carried out?

- **Unit test:** test a single method, developer writes code that calls the method and checks the result
- **Integration test:** check whether multiple parts work together, e.g. frontend with API
- **Regression test:** after a change, check whether old stuff still works, usually automated

## Task 2

Give an example of a SW error and a SW deficiency.
Give an example of high damage caused by a SW error.

- Error: web shop calculates the total wrong
- Deficiency: total is correct, but it's displayed unreadably
- High damage: software error, wrong medication dose

## Task 3

Software is usually divided into a number of subsystems, which in turn consist of many elementary
components. We saw in the V-model that there are different test levels. In this
context, we now want to look at an example of the lowest level.

**Example: test in the class Preisberechnung (price calculation)**

In the car sales software, prices are given discounts.
We have the following elements and rules:
There is a base price (_baseprice_), minus a dealer discount (_discount_).
Added to that are the special model surcharge (_specialprice_) and the price for additional equipment (_extraprice_).
If three or more extras (_extras_) are selected, there is a 10% discount on these
extras. If there are five or more extras, the discount is 15%.
The dealer discount applies to the base price. The extras discount only applies to the price of the extras.
The code would therefore look like this:

```
double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
    double addon_discount;
    double result;

    if (extras >= 3)
        addon_discount = 10;
    else if (extras >= 5)
        addon_discount = 15;
    else
        addon_discount = 0;

    if (discount > addon_discount)
        addon_discount = discount;

    result = baseprice/100.0 * (100-discount) + specialprice
            + extraprice/100.0 * (100-addon_discount);

    return result;
}
```

Implement this code in your development environment. Now create a corresponding _test driver_ to test this price calculation. A test driver is a program that executes the respective interface calls (method calls) and then receives the result. Roughly sketched, it would look like this:

```
boolean test_calculate_price(){

    double price;
    boolean test_ok = true;

    < your code>

}
```

Note: We are not writing unit tests here. We will use unit tests later in the module.
Put your tests in the repository and show your solution to the teacher.

```java
public class Preisberechnung {

    public static double calculatePrice(double baseprice, double specialprice, double extraprice, int extras, double discount) {
        double addon_discount;
        double result;

        if (extras >= 3)
            addon_discount = 10;
        else if (extras >= 5)
            addon_discount = 15;
        else
            addon_discount = 0;

        if (discount > addon_discount)
            addon_discount = discount;

        result = baseprice / 100.0 * (100 - discount) + specialprice
                + extraprice / 100.0 * (100 - addon_discount);

        return result;
    }
}
```

```java
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
```

## Task 3 - Bonus

The code snippet has a bug ;) Find the bug in the code. What would need to be fixed?

- The if order is wrong: `>= 3` comes before `>= 5`, so you never get 15%. `>= 5` has to come first.
- `if (discount > addon_discount) addon_discount = discount;` applies the dealer discount to the extras too. Should be removed.
