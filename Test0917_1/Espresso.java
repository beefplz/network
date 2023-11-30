package Test0917_1;

public class Espresso extends Beverage{
    public Espresso(){
        description = "에스프레소"; //Beverage에서 상속받음
    }
    public double cost(){
        return 1.99;
    }
}
