public class Task1 {
    public static void main(String[] args) {
        Owner stas = new Owner("Стас");
        Cat barsik = new Cat("Барсик", 4, stas);
        Cat murzik = new Cat("Мурзик", 2, stas);

        barsik.showGreet();
        murzik.showGreet();

        AgeComparator ageComparator = new CatAgeComparator();
        ageComparator.compareAndPrint(barsik, murzik);

        Purrer purrer = () -> "Мур-мур!";
        System.out.println(purrer.purr());
    }
}

interface AgeComparator {
    void compareAndPrint(Cat cat1, Cat cat2);
}

class CatAgeComparator implements AgeComparator {
    @Override
    public void compareAndPrint(Cat cat1, Cat cat2) {
        int comparison = cat1.compareTo(cat2);
        if (comparison > 0) {
            System.out.println(cat1.getName() + " старше, чем " + cat2.getName());
        } else if (comparison < 0) {
            System.out.println(cat2.getName() + " старше, чем " + cat1.getName());
        } else {
            System.out.println(cat1.getName() + " и " + cat2.getName() + " одного возраста");
        }
    }
}

class Shaper {
    private String name;
    private int age;

    public Shaper(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Shaper(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

interface Greetings {
    default void greet() {
        System.out.println("Мяу!");
    }
}
@FunctionalInterface
interface Purrer {
    String purr();
}

interface OwnerInfo {
    String getOwnerName();
}

interface AgeInfo {
    int getAge();
}

interface Constants {
    int CAT_LEG_COUNT = 4;
}

interface Overloads extends Greetings, OwnerInfo, AgeInfo {
    String getName();
    default void showGreet() {
        greet();
        System.out.printf("Меня зовут %s. Мне %d года(лет). Мой владелец - %s.\n",
                getName(), getAge(), getOwnerName());
    }
}

class Cat extends Shaper implements Overloads, Constants, Comparable<Cat> {
    private Owner owner;

    public Cat(String name, int age, Owner owner) {
        super(name, age);
        this.owner = owner;
    }

    @Override
    public String getOwnerName() {
        return owner.getName();
    }
    @Override
    public int compareTo(Cat o) {
        return Integer.compare(this.getAge(), o.getAge());
    }
}

class Owner implements OwnerInfo {
    private String name;

    public Owner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getOwnerName() {
        return name;
    }
}