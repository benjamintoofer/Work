/** Problem 9

A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

a2 + b2 = c2
For example, 32 + 42 = 9 + 16 = 25 = 52.

There exists exactly one Pythagorean triplet for which a + b + c = 1000.
Find the product abc.
*/
#include<iostream>
#include<math.h>


int main(int argc, char const *argv[]) {

    for(long a = 0; a < 1000; a++){

            for(long b = 0; b < 1000; b++){

                    double c = sqrt((pow(a,2) + pow(b,2)));
                    // std::cout <<"c = " <<c << std::endl;
                    if((float)a + (float)b + (float)c == 1000.0){
                        std::cout << a << " "<< b <<"  "<<c<<std::endl;
                        break;
                    }
            }
    }


    return 0;
}
