import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Raffle r = new Raffle();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("""
                    Главное меню:
                    1. Добавить новую игрушку в призовой фонд
                    2. Изменить частоту выпадения какой-либо игрушки
                    3. Провести розыгрыш и сохранить результат
                    0. Выход
                    Введите пункт меню: \s""");
            var selection = scanner.next();
            switch (selection) {
                case "1" -> r.addToy();
                case "2" -> r.setFrequency();
                case "3" -> r.raffle();
                case "0" -> {
                    System.out.println("Программа завершена!");
                    System.exit(0);
                }
                default -> System.out.println("Некорректный ввод. Повторите попытку.");
            }
        }
    }
}