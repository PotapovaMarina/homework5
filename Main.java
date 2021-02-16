import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int half = (int) (size / 2);

    public static void main(String[] args) {
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = 1;
        }
        long timeBefore = System.currentTimeMillis();
        fillArray(array,0);
        System.out.println("First time = " + (System.currentTimeMillis()-timeBefore)+ " mili sec");
        long timeBefore2 = System.currentTimeMillis();
        float[] array1 = new float[half];
        float[] array2 = new float[half];
        System.arraycopy(array, 0, array1, 0, half);
        System.arraycopy(array, half, array2, 0, half);
        Thread threadArray1 = new Thread(()->fillArray(array1,0));

        Thread threadArray2 = new Thread(()->fillArray(array2,half));
        threadArray1.start();
        threadArray2.start();
        try {
            threadArray1.join();
            threadArray2.join();
            System.arraycopy(array1, 0, array, 0, half);
            System.arraycopy(array2, 0, array, half, half);
            System.out.println("Second time= " + (System.currentTimeMillis()-timeBefore2)+ " mili sec");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void fillArray(float[] array, int h) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i+h) / 5) * Math.cos(0.2f + (i+h) / 5) * Math.cos(0.4f + (i+h) / 2));
        }
    }

}
