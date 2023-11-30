package Test0917_1;
public class StarbuzzCoffee {
    public static void main(String[] args) {
    Beverage beverage = new Espresso();
    System.out.println(beverage.getDescription()+"$"+beverage.cost());

    Beverage  beverage2 = new HouseBlend(); //HousBlend 객체 생성
    beverage2 = new Mocha(beverage2); //Mocha로 감쌈
    beverage2 = new Mocha(beverage2); //한번 더 감쌈
    beverage2 = new Whip(beverage2);  //Whip으로 감쌈
    System.out.println(beverage2.getDescription() + "$" + beverage2.cost());
    }
}
