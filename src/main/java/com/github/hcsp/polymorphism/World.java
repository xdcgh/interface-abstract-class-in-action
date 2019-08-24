package com.github.hcsp.polymorphism;

import java.util.Arrays;
import java.util.List;

public class World {
    public static List<Object> objects =
            Arrays.asList(new 麻雀(), new 喜鹊(), new 蝴蝶(), new 飞机(), new 救护车(), new 猫(), new 狗());

    public static void 会飞的东西飞() {
        for (Object obj : objects) {
            if (obj instanceof 会飞的东西) {
                ((会飞的东西) obj).飞();
            }
        }
    }

    public static void 会叫的东西叫() {
        for (Object obj : objects) {
            if (obj instanceof 会叫的东西) {
                ((会叫的东西) obj).叫();
            }
        }
    }

    public static void 动物都能新陈代谢() {
        for (Object obj : objects) {
            if (obj instanceof 动物) {
                ((动物) obj).新陈代谢();
            }
        }
    }

    static class 麻雀 extends 鸟类 { }

    static class 喜鹊 extends 鸟类 { }

    static class 蝴蝶 extends 会飞的动物 {
        public void 飞() {
            System.out.println("蝴蝶飞");
        }
    }

    static class 飞机 implements 会飞的东西 {
        public void 飞() {
            System.out.println("飞机飞");
        }
    }

    static class 救护车 implements 会叫的东西 {
        public void 叫() {
            System.out.println("哇呜哇呜");
        }
    }

    static class 猫 extends 会叫的动物 {
        public void 叫() {
            System.out.println("喵喵喵");
        }
    }

    static class 狗 extends 会叫的动物 {
        public void 叫() {
            System.out.println("汪汪汪");
        }
    }

    interface 会飞的东西 {
        void 飞();
    }

    interface 会叫的东西 {
        void 叫();
    }

    static class 动物 {
        public void 新陈代谢() {
            System.out.println("新陈代谢");
        }
    }

    static abstract class 会飞的动物 extends 动物 implements 会飞的东西 { }

    static abstract class 会叫的动物 extends 动物 implements 会叫的东西 { }


    static class 鸟类 extends 动物 implements 会飞的东西, 会叫的东西 {
        public void 飞() {
            System.out.println("鸟儿飞");
        }

        public void 叫() {
            System.out.println("叽叽喳喳");
        }
    }
}
