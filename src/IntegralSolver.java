import java.util.function.Function;

public class IntegralSolver {


    public double leftRectanglesMethod(double bottomLimit, double topLimit, int n, Function<Double, Double> func){
        double result = 0;
        double h = (topLimit - bottomLimit) /n;
        double x = bottomLimit;

        for(int i = 0; i < n; i ++){
            result += func.apply(x);
            x += h;
        }
        result *= h;
        return result;
    }

    public double rightRectanglesMethod(double bottomLimit, double topLimit, int n, Function<Double, Double> func){
        double result = 0;
        double h = (topLimit - bottomLimit) /n;
        double x = bottomLimit;
        for(int i =0; i < n; i++){
            x += h;
            result += func.apply(x);
        }
        result *= h;
        return result;

    }

    public double middleRectanglesMethod(double bottomLimit, double topLimit, int n, Function<Double, Double> func){
        double result = 0;
        double h = (topLimit - bottomLimit) /n;
        double x = bottomLimit - h/2;
        for(int i = 0; i < n; i++){
            x += h;
            result += func.apply(x);
        }
        result *= h;
        return result;
    }

    public double trapezeMethod(double bottomLimit, double topLimit, int n, Function<Double, Double> func){
        double result = 0;
        double h = (topLimit-bottomLimit) /n;
        double x = bottomLimit;
        for(int i = 0; i < n-1; i++){
            x += h;
            result += func.apply(x);

        }
        result = h/2 * (func.apply(bottomLimit) + 2 * result + func.apply(topLimit));
        return result;

    }

    public void solveWithAccuracyLRM(double bottomLimit, double topLimit, Function<Double, Double> func, double accuracy){
        double I0;
        double I;
        int n = 2;

        I0 = leftRectanglesMethod(bottomLimit, topLimit, n, func);

        do{
            I = I0;
            n *= 2;
            I0 = leftRectanglesMethod(bottomLimit, topLimit, n, func);

        }while(Math.abs(I0 - I) > accuracy);

        System.out.printf("Интеграл решён методом левых прямоугольников:%nЗначение интеграла:%3f%n" +
                "Число разбиения интервала интегрирования:%3d%n", I, n);
    }

    public void solveWithAccuracyRRM(double bottomLimit, double topLimit, Function<Double, Double> func, double accuracy){
        double I0;
        double I;
        int n = 2;

        I0 = rightRectanglesMethod(bottomLimit, topLimit, n, func);

        do{
            I = I0;
            n *= 2;
            I0 = rightRectanglesMethod(bottomLimit, topLimit, n, func);

        }while(Math.abs(I0 - I) > accuracy);

        System.out.printf("%nИнтеграл решён методом правых прямоугольников:%nЗначение интеграла:%3f%n" +
                "Число разбиения интервала интегрирования:%3d%n", I, n);
    }

    public void solveWithAccuracyMRM(double bottomLimit, double topLimit, Function<Double, Double> func, double accuracy){
        double I0;
        double I;
        int n = 2;

        I0 = middleRectanglesMethod(bottomLimit, topLimit, n, func);

        do{
            I = I0;
            n *= 2;
            I0 = middleRectanglesMethod(bottomLimit, topLimit, n, func);

        }while(Math.abs(I0 - I) > accuracy);

        System.out.printf("%nИнтеграл решён методом средних прямоугольников:%nЗначение интеграла:%3f%n" +
                "Число разбиения интервала интегрирования:%3d%n", I, n);
    }

    public void solveWithAccuracyTM(double bottomLimit, double topLimit, Function<Double, Double> func, double accuracy){
        double I0;
        double I;
        int n = 2;

        I0 = trapezeMethod(bottomLimit, topLimit, n, func);

        do{
            I = I0;
            n *= 2;
            I0 = trapezeMethod(bottomLimit, topLimit, n, func);

        }while(Math.abs(I0 - I) > accuracy);

        System.out.printf("Интеграл решён методом трапеций:%nЗначение интеграла:%3f%n" +
                "Число разбиения интервала интегрирования:%3d%n", I, n);

    }


}
