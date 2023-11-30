package Test0917_2;

public interface Subject {
    public void registerObserver(Observer o); //옵저버 등록
    public void removeObserver(Observer o);   //옵저버 제거
    public void notifyObservers();   //상태 변경시 모든 옵저버게 내용 전달
}
