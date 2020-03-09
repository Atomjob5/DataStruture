package com.atom;

import java.io.*;

/**
 * 普通数组 ==》 稀疏数组
 * @author Atom
 * @version 1.0
 * @date 2020/3/8 - 23:07
 */
public class SpareArrayImpl {
    public static void main(String[] args) {
//        int[][] arr = initArray();
        int[][] arr = new int[0][];
        try {
            arr = fileInput("d:\\cheese.data");
        } catch (IOException e) {
            e.printStackTrace();
        }
        printArray(arr);
        System.out.println("==================");
        int[][] parseArray = parseArray(arr);
        printArray(parseArray);

        try {
            fileOutput(parseArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    /*
       初始化数据
    */
    public static int[][] initArray() {
        int[][] arr = new int[11][11];
        arr[1][2] = 2;
        arr[2][3] = 3;
        return arr;
    }

    /*
        打印数组
     */
    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                System.out.print(String.format(new String("%d\t"), array[i][j]));
            }
            System.out.println();
        }
    }

    /*
        转化为稀疏数组
     */
    public static int[][] parseArray(int[][] array) {
        int[][] sparese = null;
        int num = 0;
        for (int[] i : array) {
            for (int j : i) {
                if (j != 0){
                    num++;  //统计不为0的数
                }
            }
        }
        sparese = new int[num + 1][3];
        sparese[0][0] = array.length;  //原数组的行数
        sparese[0][1] = array[0].length;  //原数组的列数
        sparese[0][2] = num;

        int count = 0;
        for (int z = 0; z < array.length; z++) {
            for (int x = 0; x < array[0].length; x++) {
                if (array[z][x] != 0){
                    count++;
                    sparese[count][0] = z;
                    sparese[count][1] = x;
                    sparese[count][2] = array[z][x];
                }
            }
        }
        return sparese;
    }

    /*
        将稀疏数组存储为文件
     */
    public static void fileOutput(int[][] array) throws IOException {
        File file = new File("d:\\cheese.data");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        for (int[] row : array) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int val : row) {
                stringBuffer.append(val+"\t");
            }
            stringBuffer.append("\n");
            fileOutputStream.write(stringBuffer.toString().getBytes());
        }

        fileOutputStream.flush();
        fileOutputStream.close();
    }

    /*
        读取文件
     */
    public static int[][] fileInput(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()){
            throw new RuntimeException("文件不文件");
        }
        InputStreamReader inputStreamReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        boolean flag = true; //第一次读取
        int[][] array = null;
        while ((line = bufferedReader.readLine()) != null){
            String[] split = line.split("\t");
            if (flag){
                array = new int[Integer.parseInt(split[0])][Integer.parseInt(split[1])];
                flag = false;
            }else{
                array[Integer.parseInt(split[0])][Integer.parseInt(split[1])] = Integer.parseInt(split[2]);
            }
        }

        return array;
    }


}
