package com.example.imageredctor;

import java.util.Iterator;
import java.util.Map;

public class FortyPercent {

    public byte[][] calculateFortyPercent(int width, int length, Map sortedMap,double[][] result,double percents){
        int pixelsTotal = width * length;
        double pixelsBlack = pixelsTotal * percents;
        int pixelCount = 0;

        Iterator iterator = sortedMap.entrySet().iterator();;
        Map.Entry entry = (Map.Entry) iterator.next();

        double sumOfKeys = 0;
        byte[][] resultCopy = new byte[width][length];

        while (iterator.hasNext() && pixelCount < pixelsBlack){
            entry = (Map.Entry) iterator.next();
            pixelCount += (int) entry.getValue();

        }
        double thresholdValue = Double.parseDouble(entry.getKey().toString());


        Iterator i = sortedMap.entrySet().iterator();;
        Map.Entry ent = (Map.Entry) iterator.next();
        ent = (Map.Entry) i.next();
        while(i.hasNext()) {
            ent = (Map.Entry) i.next();
            if (thresholdValue > (double) ent.getKey()) {
                ent.setValue("1");
            } else
            {
                ent.setValue("0");
            }
        }

        for(int index = 0; index < width;index++){
            for(int j = 0;j < length;j++){
                if(result[index][j] == (double)entry.getKey()){
                    resultCopy[index][j] = Byte.parseByte(entry.getValue().toString());
                }
            }
        }

        return resultCopy;
    }
}




