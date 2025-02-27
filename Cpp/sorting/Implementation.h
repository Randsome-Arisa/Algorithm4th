#pragma once
#include "Sort.h"
#include <algorithm>

template <typename T>
class Selection : public Sort<T> {
public:
	virtual void sort(T *arr, int n) {
		for (int i = 0; i < n; i++) {
			int min = i;
			for (int j = i + 1; j < n; j++) {
				if (Sort<T>::less(arr[j], arr[min]))
					min = j;
			}
			Sort<T>::exch(arr, i, min);
		}
	}
};

template<typename T>
class Insertion : public Sort<T> {
public:
	virtual void sort(T *arr, int n) {
		for (int i = 1; i < n; i++)
			for (int j = i; j > 0 && Sort<T>::less(arr[j], arr[j - 1]); j--)
				Sort<T>::exch(arr, j, j - 1);
	}
};

template<typename T>
class Shell : public Sort<T> {
public:
	virtual void sort(T *arr, int n) {
		int h = 1;
		// 使用递增序列：从N/3开始每次除3减至1
		while (h < n / 3)
			h = 3 * h + 1;
		// 从h有序数组逐渐到整体有序
		while (h >= 1) {
			for (int i = h; i < n; i++)
				for (int j = i; j > 0 && Sort<T>::less(arr[j], arr[j - h]); j-= h)
					Sort<T>::exch(arr, j, j - h);
			h /= 3;
		}
	}
};

template<typename T>
class Merge : public Sort<T> {
private:
	int *aux = nullptr;	// 辅助(auxiliary)数组

	// 以mid为界，arr两边的数组是各自有序的，将其归并
	void merge(T* arr, int lo, int mid, int hi) {
		int i = lo, j = mid + 1;
		for (int k = lo; k <= hi; k++)
			aux[k] = arr[k];
		// 原地归并
		for (int k = lo; k <= hi; k++) {
			// 左边用尽
			if (i > mid)
				arr[k] = aux[j++];
			// 右边用尽
			else if (j > hi)
				arr[k] = aux[i++];
			else if (Sort<T>::less(aux[i], aux[j]))
				arr[k] = aux[i++];
			else
				arr[k] = aux[j++];
		}
		// 如果按降序将a[]的后半部分复制到aux[]，就可以省去内循环中某半边是否用尽的代码
		// 但这样排序的结果是不稳定的
		/*
		int i = 0, j = hi
		for(int k = lo; k < mid+1; k++)
			aux[k] = a[k];
		for(int k = hi, midk = mid+1; k > mid; k--, midk++)
			aux[midk] = a[k];
		for(int k = lo; k <= hi; k++) {
			if(less(aux[j], aux[i]))   //右半边小
				a[k] = aux[j--];
			else    //左半边小
				a[k] = aux[i++];
		*/
	}
	// 排序闭区间[lo, hi]
	void sort(T *arr, int lo, int hi) {
		if (lo >= hi)
			return;
		int mid = lo + (hi - lo) / 2;
		sort(arr, lo, mid);
		sort(arr, mid + 1, hi);
		merge(arr, lo, mid, hi);
	}
public:
	virtual void sort(T *arr, int n) {
		aux = new int[n];
		sort(arr, 0, n - 1);
		delete aux;
	}
	// Bottom-Up自底向上归并
	void sortBU(T *arr, int n) {
		aux = new int[n];
		// sz表示最小数组的长度，从两两到四四到最大归并
		for (int sz = 1; sz < n; sz <<= 1)
			for (int lo = 0; lo < n - sz; lo += (sz << 1))
				merge(arr, lo, lo + sz - 1, std::min(lo + sz + sz - 1, n - 1));
		delete aux;
	}
};

template<typename T>
class Quick : public Sort<T> {
private:
	int partition(T *arr, int lo, int hi) {
		int i = lo, j = hi + 1;
		T base = arr[lo];	// 切分点的基数
		/*
		 * 这个版本的切分和大纲不一样
		 * 过程（每一趟的暂时结果）会不一样
		 * 为了考试还是记下面那个版本吧
		while (true) {
			while (Sort<T>::less(arr[++i], base))
				if (i == hi)
					break;
			while (Sort<T>::less(base, arr[--j]))
				// 可以省去，因为base就是a[lo]，不会小于a[lo]
				if (j == lo)
					break;
			if (i >= j)
				break;
			// 两边都停在了不符合要求的元素处
			Sort<T>::exch(arr, i, j);
		}
		// 将基数放到正确的位置上并返回切分点
		Sort<T>::exch(arr, lo, j);
		return j;
		*/
		while (lo < hi) {
			while (lo < hi && arr[hi] >= base)
				hi--;
			arr[lo] = arr[hi];
			while (lo < hi && arr[lo] < base)
				lo++;
			arr[hi] = arr[lo];
		}
		arr[lo] = base;
		return lo;
	}
	void sort(T *arr, int lo, int hi) {
		// this->show(arr, 7);	// 观察每趟的结果
		if (lo >= hi)
			return;
		int j = partition(arr, lo, hi);
		sort(arr, lo, j - 1);
		sort(arr, j + 1, hi);
	}
	// 三向切分，适合有大量重复元素的数组
	void sort3way(T *arr, int lo, int hi) {
		if (lo >= hi)
			return;
		// 从lo到hi依次遍历
		// 保证arr[lt, gt] == base，arr[lo, lt-1] < base，arr[gt+1, hi] > base
		int lt = lo, gt = hi, i = lo + 1;	// lessTag, greaterTag
		T base = arr[lo];
		while (i <= gt) {
			if (arr[i] == base)
				i++;
			else if (Sort<T>::less(arr[i], base))
				Sort<T>::exch(arr, lt++, i++);
			else
				Sort<T>::exch(arr, gt--, i);
		}
		sort3way(arr, lo, lt - 1);
		sort3way(arr, gt + 1, hi);
	}
public:
	virtual void sort(T *arr, int n) {
		sort(arr, 0, n - 1);
	}
};

template<typename T>
class Heap : public Sort<T> {
private:
	// 下沉第k个元素
	void sink(T *arr, int n, int k) {
		// k的左子节点序号为2*k
		while (2 * (k + 1) <= n) {
			int j = 2 * k + 1;
			// 将k的两个子节点中较大的那个与其交换
			if (j + 1 < n && Sort<T>::less(arr[j], arr[j + 1]))
				j++;
			if (!Sort<T>::less(arr[k], arr[j]))
				break;
			Sort<T>::exch(arr, k, j);
			k = j;
		}
	}
public:
	virtual void sort(T *arr, int n) {
		// 构造堆
		for (int k = n / 2 - 1; k >= 0; k--)
			sink(arr, n, k);

		Sort<T>::show(arr, n);
		// 排序
		while (n > 0) {
			Sort<T>::exch(arr, 0, --n);
			sink(arr, n, 0);
		}
	}
};