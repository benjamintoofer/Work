package com.benjy20b;

public class Main {

    static final int MAX_POINTS = 5;

    public static void main(String[] args) {


        Point[] points = new Point[MAX_POINTS];
        Point[] doublePoints;

        //Assign points in array
        for(int i = 0; i < MAX_POINTS; i++)
        {
            Point temp = new Point(i,i);
            points[i] = temp;
            System.out.println(points[i].toString());
        }

        rev(points);
        System.out.println("\nFirst Array reversed");
        for(int i = 0; i < points.length; i++)
        {
            System.out.println(points[i].toString());
        }

        rev2(points);
        System.out.println("\n Second Array reversed");
        for(int i = 0; i < points.length; i++)
        {
            System.out.println(points[i].toString());
        }

        System.out.println("\nDoubled Array size");
        doublePoints = doubleArray(points);
        for(int i = 0; i < doublePoints.length; i++)
        {
            System.out.println(doublePoints[i].toString());
        }

        System.out.println("\nRecursive print");
        printNums(7,0);
     System.exit(0);
    }

    /*
    Reverses points in array by switching point objects
     */
    public static void rev(Point points[])
    {
        for(int i = 0; i < (points.length/2); i++)
        {
            int leftIndex = i;
            int rightIndex = points.length - 1 - leftIndex;
            Point temp =  points[i];
            points[leftIndex] = points[rightIndex];
            points[rightIndex] = temp;

        }
    }

    /*
    Reverses points in array by switching x,y values
     */
    public static void rev2(Point points[])
    {
        for(int i = 0; i < (points.length/2); i++)
        {
            int leftIndex = i;
            int rightIndex = points.length - 1 - leftIndex;
            int tempX = points[i].getX();
            int tempY = points[i].getY();
            points[leftIndex].setX( points[rightIndex].getX());
            points[leftIndex].setY( points[rightIndex].getY());
            points[rightIndex].setX(tempX);
            points[rightIndex].setY(tempY);

        }
    }

    public static Point[] doubleArray(Point points[])
    {
        Point[] newArray = new Point[2 * points.length];

        for(int i = 0; i < newArray.length; i++)
        {
            if(i < (newArray.length/2))
            {
                newArray[i] = points[i];
            }else{
                newArray[i] = new Point();
            }

        }

        return newArray;
    }

    public static void printNums(int num, int index)
    {
        if(num != index)
        {
            System.out.print(index + " ");
            index += 1;
            printNums(num,index);
        }


    }
}
