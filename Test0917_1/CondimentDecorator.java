package Test0917_1;
public abstract class CondimentDecorator extends Beverage{
     Beverage beverage;  //각 데코레이터가 감싼음료를 나타내는 객체를 지정
    public abstract String getDescription();
}