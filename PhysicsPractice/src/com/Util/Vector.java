package com.Util;

/**
 * Created by benjamintoofer on 8/14/14.
 */
public class Vector {

    public double x,y,z;

    public Vector()
    {
        this(0, 0, 0);
    }

    public Vector(double x,double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //return the Magnitude of the Vector
    public double magnitude()
    {
        return Math.sqrt(x * x + y * y + z * z);
    }

    //return the Unit Vector of the vector
    public Vector normalize()
    {
        double mag = Math.sqrt(x*x + y*y + z*z);

        if(mag <= .0001)
        {
            mag = 1;
        }

        double u_X = x/mag;
        double u_Y = y/mag;
        double u_Z = z/mag;

        if(Math.abs(u_X) < .001) u_X = 0.0;
        if(Math.abs(u_Y) < .001) u_Y = 0.0;
        if(Math.abs(u_Z) < .001) u_Z = 0.0;

        return new Vector(u_X,u_Y,u_Z);
    }

    //return Reverse Vector
    public Vector reverse()
    {
        return new Vector(-x,-y,-z);
    }

    @Override
    public String toString()
    {
        return "x: "+x+" y: "+y+" z: "+z+"\nmagnitude: "+this.magnitude()+"\n";
    }

}
