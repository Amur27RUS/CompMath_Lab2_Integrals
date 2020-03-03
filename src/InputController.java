import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;

public class InputController {

    Scanner scn = new Scanner(System.in);
    double[] limits = new double[3];
    IntegralSolver Isolver = new IntegralSolver();
    String[] strFuncs = {
            "1. 4x^3 - 5x^2 + 6x - 7",
            "2. x^x",
            "3. 3x^3 - 2x^2 - 7x - 8",
            "4. x^3 - 2x^2 - 5x + 24",
            "5. x*x"
    };

    String[] strRecMethods = {
            "1. Метод левых прямоугольников",
            "2. Метод средних прямоугольников",
            "3. Метод правых прямоугольников",
            "4. Использовать все методы"
    };


    public void chooseFunction(){

        boolean inputCheacker = false;
        int func;
        int method;
        double I = 0;
        double I0 = 0;
        while(!inputCheacker) {

            System.out.println("Введите номер функции, которую программа будет решать:");
            for(int i = 0; i<5;i++){
                System.out.println(strFuncs[i]);
            }
            func = scn.nextInt();

            switch (func) {

                case 1:
                    inputCheacker = true;
                    methodStarter(x -> 4 * Math.pow(x, 3) - 5 * Math.pow(x, 2) + 6 * x - 7);
                break;

                case 2:
                    inputCheacker = true;
                    methodStarter(x -> Math.pow(x, x));
                break;

                case 3:
                    inputCheacker = true;
                    methodStarter(x -> 3*Math.pow(x, 3) - 2* Math.pow(x,2) - 7*x - 8);
                break;

                case 4:
                    inputCheacker = true;
                    methodStarter(x -> Math.pow(x, 3) - 2*Math.pow(x, 2) - 5*x + 24);
                break;

                case 5:
                    inputCheacker = true;
                    methodStarter(x -> x*x);
                break;

                default:
                    inputCheacker = false;
                    System.err.println("Неверный ввод!");
                break;
            }
        }
    }


    private void setLimits(){
        System.out.println("Введите пределы интегрирования:");
        System.out.println("Нижний:");
        limits[0] = scn.nextDouble();
        System.out.println("Верхний:");
        limits[1] = scn.nextDouble();
        System.out.println("Введите желаемую точность:");
        limits[2] = scn.nextDouble();

        if(limits[2] > 1 || limits[2] <=0.000001){
            System.err.println("Неверно введена точность!");
            System.exit(1);
        }

        if(limits[0] > limits[1]){
            double temp = limits[0];
            limits[0] = limits[1];
            limits[1] = temp;
        }

    }

    private int chooseMethod(){
        int x = 0;
        System.out.println("Выберите Метод интегрирования:");
        System.out.println("1. Метод прямоугольников.");
        System.out.println("2. Метод трапеций.");
        x = scn.nextInt();
        return x;
    }

    private void methodStarter(Function<Double, Double> function){
        setLimits();

        int method = chooseMethod();

        if (method == 1){
            System.out.println("Каким методом прямоугольников вы будете решать?");
            for(int i = 0; i< 4; i++){
                System.out.println(strRecMethods[i]);
            }
            int input = scn.nextInt();

            switch (input){
                case 1:
                    Isolver.solveWithAccuracyLRM(limits[0], limits[1], function, limits[2]);
                    break;

                case 2:
                    Isolver.solveWithAccuracyMRM(limits[0], limits[1], function, limits[2]);
                    break;

                case 3:
                    Isolver.solveWithAccuracyRRM(limits[0], limits[1], function, limits[2]);
                    break;

                case 4:
                    Isolver.solveWithAccuracyLRM(limits[0], limits[1], function, limits[2]);
                    Isolver.solveWithAccuracyMRM(limits[0], limits[1], function, limits[2]);
                    Isolver.solveWithAccuracyRRM(limits[0], limits[1], function, limits[2]);
                    break;

                default:
                    System.err.println("Вы ввели неверное значение!");
                    System.exit(1);
            }

        }else{
            Isolver.solveWithAccuracyTM(limits[0], limits[1], function, limits[2]);
        }

    }
}

