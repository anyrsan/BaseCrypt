import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class JavaDemo {

    public static class A extends BaseBean<List<DemoBean>> {

    }

    public static class BaseBean<T>{
        public BaseBean(){
            Type type  = ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            System.out.println("type : " + ((Class)type).getName());
        }
    }


    public static class B extends BaseBean<DemoBean>{

    }


    public static void main(String[] args){
//        new A();
        new B();
    }

}
