#pragma once
#include <iostream>

/*
 * 所有排序算法的基类
 * 后续算法只需覆写sort函数即可
 */
template <typename T>
class Sort {
public:
	virtual void sort(T *arr, int n) = 0 {}
	// a<b返回true
	bool less(T a, T b) {
			return a < b;
	}
	// T数组中i和j号换位
	void exch(T *arr, int i, int j) {
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	// 按序显示T数组
	void show(T *arr, int n) {
		for (int i = 0; i < n; i++)
			std::cout << arr[i] << " ";
		std::cout << std::endl;
	}
	// 检查是否为升序
	bool isSorted(T* arr, int n) {
		for (int i = 1; i < n; i++) {
			if (less(arr[i], arr[i - 1]))
				return false;
		}
		return true;
	}
};