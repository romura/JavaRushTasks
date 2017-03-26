package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
//        solution.createExpression(1231);
//        solution.createExpression(1232);
//        solution.createExpression(1233);
//        solution.createExpression(1234);
//        solution.createExpression(1235);
//        solution.createExpression(2);
//        solution.createExpression(5);
    }

    public void createExpression(int number) {
        StringBuilder sb = new StringBuilder(Integer.toString(number, 3));
        String[] array = sb.reverse().toString().split("");
        StringBuilder result = new StringBuilder().append(number).append(" =");

        int buffer = 0;
        for (int i = 0; i < array.length; i++){
            int n = Integer.parseInt(array[i]);
            n += buffer;

            switch (n){
                case 3:
                    buffer = 1;
                    break;
                case 2:
                    result.append(" - ").append((int)Math.pow(3, i));
                    buffer = 1;
                    break;
                case  1:
                    result.append(" + ").append((int)Math.pow(3, i));
                    buffer = 0;
                    break;
                default:
                    buffer = 0;
                    break;
            }

            if (i == array.length - 1 && buffer == 1){
                result.append(" + ").append((int)Math.pow(3, i + 1));
            }
        }

        System.out.println(result.toString());

    }
}