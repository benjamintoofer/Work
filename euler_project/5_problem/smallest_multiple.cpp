/** Problem 5

2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
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

    int size = 20;
    int* num_array = new int[size];
    std::list<int>* list = new std::list<int>();
    int* temp = new int[size];

    for(int j = 0; j < size; j++){
        num_array[j] = 0;
    }

    for(int i = 1; i <= size; i++){

        find_prime_factor(list,i);


        for(int num : *list){

            temp[num] = temp[num] + 1;
            // std::cout << num << std::endl;
        }

        for(int j = 0; j < size; j++){

            if(temp[j] > num_array[j]){
                int n = temp[j];
                // std::cout << "n" <<" " << n<<std::endl;
                num_array[j] = n;
            }
            // num_array[j] = (temp[j] > num_array[j]) ? temp[j] : num_array[j];
            // std::cout << j <<" " << num_array[j]<<std::endl;
        }

        for(int j = 0; j < size; j++){
            temp[j] = 0;
        }

        list->clear();
    }
    std::cout << "/* message */" << std::endl;
    int prod = 1;
    for(int i = 0; i < size; i++){

        if(num_array[i] > 0){
            prod *= pow(i,num_array[i]);

        }
    }
    std::cout << prod << std::endl;

    delete[] num_array;
    delete[] temp;
    delete list;

    return 0;
}
