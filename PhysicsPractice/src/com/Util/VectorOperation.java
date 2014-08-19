package com.Util;

/**
 * Created by benjamintoofer on 8/14/14.
 */
public class VectorOperation {

    /**
     *
     * Operations
     *
     */

    //Add Vectors
    public static Vector addVector(Vector u,Vector v)
    {
        return new Vector(u.x + v.x,u.y + v.y,u.z + v.z);
    }

    //Subtract Vectors
    public static Vector subVector(Vector u,Vector v)
    {
        return new Vector(u.x - v.x,u.y - v.y,u.z - v.z);
    }

    //Multiply Vector by scalar
    public static Vector scalarMultVector(Vector u,double s)
    {
        return new Vector(u.x*s,u.y*s,u.z*s);
    }

    //Divide Vector by scalar
    public static Vector scalarDivVector(Vector u,double s)
    {
        return new Vector(u.x/s,u.y/s,u.z/s);
    }

    //Cross Product returns a vector perpendicular to the two given Vectors
    public static Vector crossProduct(Vector u, Vector v)
    {
        return new Vector((u.y*v.z - u.z*v.y),(u.z*v.x - u.x*v.z),(u.x*v.y - u.y*v.x));
    }

    //Dot Product returns the projection of a Vector onto another Vector
    public static double dotProduct(Vector u, Vector v)
    {
        return (u.x*v.x + u.y*v.y + u.z*v.z);
    }

}
