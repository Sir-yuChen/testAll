package com.zy.pagehelper.lambda;

/**
 * @author Administrator
 * @date 2020/11/17 17:34
 **/
/**
    区别类关系
    1.普通类
    2.局部内部类
    3.匿名内部类，没有类的名称。必须借助接口或者父类
    4、lamda表达式
 */
public class NewClass {
    //1、静态内部类，同样实现ILike接口
    static class Like2 implements ILike{
        @Override
        public void lamda() {
            System.out.println("i like lamda2");
        }
    }

    public static void main(String[] args) {
        ILike like = new Like();
        like.lamda();
        like = new Like2();
        like.lamda();

        //2、局部内部类
        class Like3 implements ILike{
            @Override
            public void lamda() {
                System.out.println("i like lamda3");
            }
        }
        like = new Like3();
        like.lamda();

        //3、匿名内部类，没有类的名称。必须借助接口或者父类
        like = new ILike() {
            @Override
            public void lamda() {
                System.out.println("i like lamda4");
            }
        };
        like.lamda();

        //4、lamda表达式
        like = ()->{
            System.out.println("i like lamda5");
        };
        like.lamda();
    }
}


//1、定义一个只有一个抽象方法的接口
interface ILike{
    abstract void lamda();
}

//2、实现类
class Like implements ILike{

    @Override
    public void lamda() {
        System.out.println("i like lamda1");
    }


}
