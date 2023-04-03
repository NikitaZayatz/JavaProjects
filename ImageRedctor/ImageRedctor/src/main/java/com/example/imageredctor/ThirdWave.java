package com.example.imageredctor;

import java.util.HashMap;
import java.util.HashSet;

public class ThirdWave {

    HashMap<Double,Integer> gistogram;

    public HashMap<Double, Integer> getGistogram() {
        return gistogram;
    }

    public double[][] CalculateWaves(byte[][] array_wave, int width, int height, int countOfWave, double[] arRadius){
        double[][] result = new double[width][height];
        for(int i = 0 ; i < width;i++)
        {
            for(int j = 0; j < height;j++){
                if(array_wave[i+countOfWave][j+countOfWave] == 1){
                  result[i][j] = 1;
                }else if(array_wave[i+countOfWave][j+countOfWave] == 0){
                    result[i][j] = 0;
                }
            }
        }
        for(int i = 1;i <= countOfWave; i++){
            iterations(i,countOfWave,width,height,array_wave,arRadius,result);
        }
         System.out.println(sortValues(result,width,height));
        return result;
    }
    public void iterations(int countOFIterations,int countOfLastIteration,int width,int height,byte[][]array_wave,double[] arRadius,double result[][])
    {
        int count;
        System.out.println();
        for(int i = countOfLastIteration; i < (width + (countOfLastIteration));i++){
            for(int j = countOfLastIteration; j < (height + (countOfLastIteration));j++){
                count =(array_wave[i+countOFIterations][j] + array_wave[i][j+countOFIterations] + array_wave[i+countOFIterations][j+countOFIterations]
                        + array_wave[i][j-countOFIterations] + array_wave[i-countOFIterations][j]+array_wave[i-countOFIterations][j-countOFIterations]
                        + array_wave[i-countOFIterations][j+countOFIterations] + array_wave[i +countOFIterations][j-countOFIterations]);
                result[i-countOfLastIteration][j-countOfLastIteration] += count * arRadius[countOFIterations - 1];
            }

        }
    }
     public HashMap sortValues(double [][]result,int width ,int height){
        gistogram = new HashMap<Double, Integer>();
        gistogram.put(result[0][0],1);
        for(int i = 0; i < width;i++){
            for (int j = 0; j < height; j++)
            {
                if(gistogram.containsKey(result[i][j])){
                    if(j!=0 && i!=0){
                        gistogram.put(result[i][j],gistogram.get(result[i][j]) + 1);
                    }
                }
                else {
                    gistogram.put(result[i][j],1);
                }
            }
        }
        return gistogram;
    }
}
