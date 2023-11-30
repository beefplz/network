package Test0917_1;
public abstract class Beverage{
    String description = "제목없음";  
            
    public String getDescription(){    //getDescription은 구현됐지만
        return description;            //cost는 서브클래스에서 구현되야함  
    }
    public abstract double cost();        
}