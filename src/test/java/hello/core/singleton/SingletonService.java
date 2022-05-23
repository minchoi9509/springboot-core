package hello.core.singleton;

public class SingletonService {

    // static으로 하면 class 레벨에 올라가기 때문에 딱 1개
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    private SingletonService() {
        
    }
    
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
