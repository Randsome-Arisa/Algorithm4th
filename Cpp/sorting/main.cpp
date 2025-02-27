#include <ctime>
#include "Implementation.h"

void newArr(int* arr, int n, int range);	// 随机生成一个长度为n，范围为0~range的数组
const int N = 16;	// 数组长度

int main() {
	/*int arr[N];
	newArr(arr, N, 100);

	Selection<int> selection;
	Insertion<int> insertion;
	Shell<int> shell;
	Merge<int> merge;
	Heap<int> heap;

	heap.show(arr, N);
	heap.sort(arr, N);
	heap.show(arr, N);
	std::cout << heap.isSorted(arr, N);*/
	int arr[7] = { 39, 38, 66, 90, 75, 10, 20 };
	Quick<int> quick;
	quick.sort(arr, 7);

	std::cout << "*********" << std::endl;
	quick.show(arr, 7);
	return 0;
}

void newArr(int *arr, int n, int range) {
	srand(time(0));
	for (int i = 0; i < n; i++)
		arr[i] = rand() % range;
}