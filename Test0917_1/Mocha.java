package Test0917_1;
public class Mocha extends CondimentDecorator{ //데코레이터이기 때문에
    public Mocha(Beverage beverage){           //CondimentDecorator를 확장
        this.beverage = beverage;
    }
    public String getDescription(){
            return beverage.getDescription() + ", 모카";
    }
        
    public double cost(){
        return beverage.cost() + .28;
    }
}