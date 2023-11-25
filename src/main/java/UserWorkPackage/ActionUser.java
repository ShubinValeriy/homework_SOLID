package UserWorkPackage;

import DB.*;
import DB.WORKER.WorkerDB;
import Pricer.Pricer;
import Product.Product;

import java.util.List;
import java.util.Scanner;

public class ActionUser {
    public void startShop(DB warehouse, Pricer pricer) {
        DB basket = new Basket();
        boolean action = true;

        while (action) {
            System.out.println("""
                    Meню магазина: \s
                     1. Добавить в корзину товар\s
                     2. Перейти в корзину\s
                    -1. Покинуть магазин""");
            System.out.print("Выберите действие, которое необходимо выполнить: ");
            int input = getInt();
            switch (input) {
                case -1 -> {
                    System.out.println("Всего доброго!");
                    action = false;
                }
                case 1 -> {
                    actionAddProduct(pricer, warehouse, basket);
                }
                case 2 -> {
                    action = actionBasket(pricer, basket, warehouse);
                }
                default -> System.out.println("Внесено не корректное значение");
            }
        }
    }

    private boolean actionBasket(Pricer pricer, DB basket, DB warehouse) {
        boolean actionB = true;
        boolean actionM = true;
        while (actionB) {
            printRangeProduct(pricer, basket);
            if (basket.isEmpty()) {
                System.out.println("""
                        Меню корзины:\s
                        -1. Вернуться в меню \s
                        -2. Покинуть магазин""");
            } else {
                System.out.println("""
                        Меню корзины:\s
                         1. Увеличить количество товара в корзине \s
                         2. Уменьшить количество товара в корзине \s
                         3. Оформить заказ \s
                         4. Очистить корзину \s
                        -1. Вернуться в меню \s
                        -2. Покинуть магазин""");
            }
            System.out.print("Выберите действие, которое необходимо выполнить: ");
            int input = getInt();
            switch (input) {
                case -1 -> {
                    actionB = false;
                    actionM = true;
                }
                case -2 -> {
                    System.out.println("Всего доброго!");
                    actionM = false;
                    actionB = false;
                }
                case 1 -> {
                    actionIncreaseProduct(pricer, warehouse, basket);
                }
                case 2 -> {
                    actionDecreaseProduct(pricer, basket, warehouse);
                }
                case 3 -> {
                    actionOrdering(basket, pricer);
                    actionB = false;
                    actionM = false;
                }
                case 4 -> {
                    WorkerDB workerDB = new WorkerDB();
                    for (Product product : basket.getListProduct()) {
                        workerDB.moveFromTo(basket, warehouse, product, basket.getCountProduct(product));
                    }
                    basket.clear();
                    System.out.println("Корзина очищена");
                    actionB = false;
                    actionM = true;
                }
                default -> System.out.println("Внесено не корректное значение");
            }
        }
        return actionM;
    }

    private void actionAddProduct(Pricer pricer, DB from, DB to) {
        Product product = getProductUser(pricer, from);
        boolean action = true;
        while (action) {
            System.out.print("Укажите количество товара: ");
            int count = getInt();
            if (count <= 0 || count > from.getCountProduct(product)) {
                System.out.println("Внесено не корректное значение");
            } else {
                WorkerDB workerDB = new WorkerDB();
                workerDB.moveFromTo(from, to, product, count);
                action = false;
            }
        }
    }

    private void actionIncreaseProduct(Pricer pricer, DB from, DB to) {
        Product product = getProductUser(pricer, to);
        int count = 1;
        if (count > from.getCountProduct(product)) {
            System.out.println("Товара больше нет на " + from);
        } else {
            WorkerDB workerDB = new WorkerDB();
            workerDB.moveFromTo(from, to, product, count);
        }
    }

    private void actionDecreaseProduct(Pricer pricer, DB from, DB to) {
        Product product = getProductUser(pricer, from);
        int count = 1;
        if (count > from.getCountProduct(product)) {
            System.out.println("Товара больше нет на " + from);
        } else {
            WorkerDB workerDB = new WorkerDB();
            workerDB.moveFromTo(from, to, product, count);
        }
    }


    private Product getProductUser(Pricer pricer, DB db) {
        printRangeProduct(pricer, db);
        while (true) {
            System.out.print("Укажите номер товара: ");
            int input = getInt();
            if (input <= 0 || input > db.getListProduct().size()) {
                System.out.println("Внесено не корректное значение");
            } else {
                return db.getListProduct().get(input - 1);
            }
        }
    }

    private void actionOrdering(DB basket, Pricer pricer) {
        int orderNumber = (int) (Math.random() * 1000);
        System.out.println("Спасибо за заказ № " + orderNumber);
        List<Product> listProduct = basket.getListProduct();
        int i = 1;
        double totalprice = 0;
        for (Product product : listProduct) {
            double salesPrice = pricer.calcSalesPrice(product);
            System.out.printf("%s. %-24s цена: %-7s руб. количество (%s шт.) \n",
                    i++, product, salesPrice, basket.getCountProduct(product));
            totalprice += salesPrice * basket.getCountProduct(product);
        }
        System.out.printf("Итого стоимость заказа: %1.2f руб. \n", totalprice);
        System.out.println("Ожидайте, в течении 2-х часов с вами " +
                "свяжется менеджер для подтверждения заказа и оплаты.");
    }


    private int getInt() {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            return 0;
        }
        return scanner.nextInt();
    }

    private void printRangeProduct(Pricer pricer, DB db) {
        if (db.isEmpty()) {
            System.out.println("Извините, к сожалению " + db + " пуст(а)!");
        } else {
            System.out.println(db + ":");
            List<Product> listProduct = db.getListProduct();
            int i = 1;
            for (Product product : listProduct) {
                double salesPrice = pricer.calcSalesPrice(product);
                System.out.printf("%s. %-24s цена: %-7s руб. количество (%s шт.) \n",
                        i++, product, salesPrice, db.getCountProduct(product));
            }
        }
    }
}
