/** Problem 3

The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143 ?
*/
#include<iostream>
#include<math.h>
#include <list>


void prime_factor(std::list<int>* list, int prime_num, long max_num){

    int new_num = sqrt(max_num);

    //if(prime_num == 0)
    for (size_t i = 2; i <= new_num; i++) {

        if(max_num % i == 0){

            list->push_front(i);
            prime_factor(list,i,max_num / i);
            return;
        }

    }
    list->push_front(max_num);
}

void find_prime_factor(std::list<int>* list, long max_num){

    prime_factor(list,-1,max_num);

}

int main(int argc, char const *argv[]) {

    long num = 600851475143;
    std::list<int>* prime_factors = new std::list<int>();

    find_prime_factor(prime_factors,num);

    for(int num : *prime_factors){
        std::cout << num << std::endl;
    }

    return 0;
}
