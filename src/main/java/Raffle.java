import java.util.*;

public class Raffle {

    private final ArrayList<Toy> toys;
    private final PriorityQueue<Toy> prizes;
    private final SaveResult save;
    private int idCounter = 0;

    public Raffle() {
        toys = new ArrayList<>();
        prizes = new PriorityQueue<>();
        save = new SaveResult("Result.txt");
    }

    public void addToy() {
        Scanner scan = new Scanner(System.in);
        String title;
        int frequency;
        while (true) {
            System.out.print("Введите наименование: ");
            title = scan.nextLine();
            if (title.isEmpty()) {
                System.out.println("Некорректный ввод. Повторите попытку.");
                break;
            }
            System.out.print("Введите частоту выпадения: ");
            String value = scan.nextLine();
            if (isDigit(value)) {
                frequency = Integer.parseInt(value);
                if (frequency <= 0) {
                    System.out.println("Некорректный ввод. Повторите попытку.");
                } else {
                    Toy toy = new Toy(idCounter, title, frequency);
                    if (!toys.contains(toy)) {
                        idCounter++;
                        toys.add(toy);
                        System.out.println("Новая игрушка добавлена.");
                    } else {
                        System.out.println("Данная игрушка уже находится в призовом фонде!");
                    }
                }
            } else {
                System.out.println("Некорректный ввод. Повторите попытку.");
            }
            break;
        }
    }

    public void setFrequency() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите ID игрушки: ");
        String value = scan.nextLine();
        if (isDigit(value)) {
            int selectedId = Integer.parseInt(value);
            if (selectedId >= 0 && selectedId < toys.size()) {
                System.out.println("Игрушка " + toys.get(selectedId).getToyTitle() +
                        " имеет частоту выпадения " + toys.get(selectedId).getToyVictoryFrequency());
                System.out.print("Введите новую частоту выпадения: ");
                value = scan.nextLine();
                if (isDigit(value)) {
                    int newFrequency = Integer.parseInt(value);
                    toys.get(selectedId).setToyVictoryFrequency(newFrequency);
                    System.out.println("Частота изменена.");
                } else {
                    System.out.println("Некорректный ввод. Повторите попытку.");
                }
            } else {
                System.out.println("Игрушка с данным ID не найдена в призовом фонде!");
            }
        } else {
            System.out.println("Некорректный ввод. Повторите попытку.");
        }
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Toy getPrize() {
        if (prizes.size() == 0) {
            Random random = new Random();
            for (Toy toy : toys) {
                for (int i = 0; i < toy.getToyVictoryFrequency(); i++) {
                    Toy temp = new Toy(toy.getToyId(), toy.getToyTitle(), random.nextInt(1, 10));
                    prizes.add(temp);
                }
            }
        }
        return prizes.poll();
    }

    public void raffle() {
        if (toys.size() >= 2) {
            Toy prize = getPrize();
            System.out.println("Приз: " + prize.getToyTitle());
            save.write(prize.getInfo());
        } else {
            System.out.println("В призовой фонд необходимо добавить не менее двух игрушек.");
        }
    }
}