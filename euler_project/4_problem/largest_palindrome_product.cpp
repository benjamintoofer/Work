/** Problem 4

A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

Find the largest palindrome made from the product of two 3-digit numbers.
*/
#include <iostream>


bool check_palindrome(int num){

    int temp = num;
    size_t size = 0;
    int* num_array = new int[6];

    while(temp != 0){
        num_array[size] = temp % 10;
        temp = temp/10;
        size++;
    }

    int a,b;

    // std::cout << "Number is "<< num << std::endl;
    for(a = 0,b = size-1; a < size/2; a++,b--){
        // std::cout<<num_array[a] << " " << num_array[b]<<std::endl;
        if(num_array[a] != num_array[b])
            return false;
    }

    return true;
}

int main(int argc, char const *argv[]) {

    int max = 999;
    int largest_palindrome = 0;
    bool found = false;

    for(int i = max; i > 0; i-- ){

        for(int j = i; j > 0; j--){


            found = check_palindrome(j*i);
            if(found){
                // std::cout<< i << " " << j <<std::endl;
                largest_palindrome = largest_palindrome > j*i ? largest_palindrome : j*i;
                // break;
            }
        }

        // if(found)
            // break;
    }

    std::cout << largest_palindrome << std::endl;
    return 0;
}
