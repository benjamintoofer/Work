/** Problem 10

The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
Find the sum of all the primes below two million.
*/
#include<iostream>
#include <math.h>

int main(int argc, char const *argv[]) {

    long max = 2000000;
    int new_max = 0;
    long sum = 0;
    bool* cache = new bool[max];
    bool is_prime = true;
    std::cout << sizeof(long int)<<std::endl;
    for(long i = 2; i < max; i++){

        new_max = (int)sqrt(i);


        if(cache[i])
            continue;

        is_prime = true;

        for(long j = 2; j <= new_max; j++){
            long temp = (i * j);

            if( temp < max){
                cache[i*j] = 1;
            }
            if(i % j == 0){

                is_prime = false;
                break;
            }
        }

        if(is_prime){

            sum += i;
        }

    }

    std::cout << sum << std::endl;

    return 0;
}
