#include "BST.h"
#include <ctime>

void newArr(int *arr, int N);
const int N = 16;

int main() {
	int arr[N] = {81, 35, 85, 21, 59, 58, 45, 59, 37, 94, 9, 84, 59, 75, 46, 58};	int brr[N];
	/*newArr(arr, N);*/	newArr(brr, N);
	BST<int, int> bst;
	for (int i = 0; i < N; i++) {
		bst.put(arr[i], brr[i]);
		std::cout << arr[i] << " ";
	}
	std::cout << std::endl;
	/*int test[8] = { 19, 5, 24, 1, 18, 3, 8, 13 };
	for (int i = 0; i < 8; i++) {
		bst.put(test[i], i);
	}*/
	bst.print();
	std::cout << std::endl;

	/*std::vector<int> v = bst.range(30, 60);
	for (std::vector<int>::iterator it = v.begin(); it != v.end(); it++)
		std::cout << *it << " ";
	std::cout << std::endl;*/

	/*for (int i = 0; i < N; i++) {
		bst.deleteMin();
		bst.print();
		std::cout << std::endl;
	}*/
	
	for (int i = 0; i < N; i++) {
		bst.Delete(arr[i]);
		bst.print();
		std::cout << std::endl;
	}
}

void newArr(int *arr, int N) {
	srand(time(0));
	for (int i = 0; i < N; i++) {
		arr[i] = rand() % 100 + 1;
	}
}