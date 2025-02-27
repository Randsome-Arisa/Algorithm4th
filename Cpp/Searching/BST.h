#pragma once
#include <iostream>
#include <vector>
/*
 * Key min()							返回最小键
 * Key max()							返回最大键
 * Key floor(Key key)					返回key向下取整的键
 * Key celling(Key key)					返回key向上取整了键
 * Key select(int k)					返回排名为k的键（有k个键比它小）
 * int rank(Key key)					返回key的排名
 * void deleteMin()						删除最小键
 * void Delete(Key key)					删除任意键
 * void print()							中序遍历，按升序打印键
 * vector<Key> &range(Key lo, Key hi)	返回一个存储范围内键的vector
 */

template<typename Key, typename Value>
struct Node {
	Key key;
	Value val;
	Node *left;
	Node *right;
	int N;	// 以该结点为根的树中结点的总数
	Node(Key key, Value val, int N) {
		this->key = key;
		this->val = val;
		this->N = N;
		this->left = nullptr;
		this->right = nullptr;
	}
};

template<typename Key, typename Value>
class BST {
private:
	Node<Key, Value> *root;
	int size(Node<Key, Value> *root) {
		if (root == nullptr)
			return 0;
		else
			return root->N;
	}
	Value get(Node<Key, Value> *node, Key key) {
		if (node == nullptr)
			return nullptr;
		if (node->key == key)
			return node->val;
		else if (key < node->key)
			return get(node->left, key);
		else
			return get(node->right, key);
	}
	Node<Key, Value> *put(Node<Key, Value> *node, Key key, Value val) {
		// 不存在时，建立新结点
		if (node == nullptr) {
			return new Node<Key, Value>(key, val, 1);
		}
		// 如果存在，则更新
		if (node->key == key)
			node->val = val;
		// 否则查找
		else if (key < node->key)
			node->left = put(node->left, key, val);
		else
			node->right = put(node->right, key, val);
		updateN(root);
		return node;
	}
	Node<Key, Value> *select(Node<Key, Value> *node, int k) {
		if (k < size(node->left))
			return select(node->left, k);
		else if (k > size(node->left))
			return select(node->right, k - size(node->left) - 1);
		else
			return node;
	}
	int *rank(Node<Key, Value> *node, Key key) {
		if (key == node->key)
			return size(node->left);
		else if (key < size(node->left))
			return rank(node->left, key);
		else
			return rank(node->right, key) + size(node->left) + 1;
	}
	int updateN(Node<Key, Value> *node) {
		if (node == nullptr)
			return 0;
		else
			node->N = updateN(node->left) + updateN(node->right) + 1;
	}
	void print(Node<Key, Value> *node) {
		if (node == nullptr)
			return;
		print(node->left);
		std::cout << node->key << " ";
		print(node->right);
	}
	void range(std::vector<Key> &arr, Node<Key, Value> *node, Key lo, Key hi) {
		if (node == nullptr)
			return;
		if (lo < node->key)
			range(arr, node->left, lo, hi);
		if (lo <= node->key && node->key <= hi)
			arr.push_back(node->key);
		if (node->key < hi)
			range(arr, node->right, lo, hi);
	}
	Node<Key, Value> *Delete(Node<Key, Value> *node, Key key) {
		if (node == nullptr)
			return nullptr;
		if (key < node->key)
			node->left = Delete(node->left, key);
		else if (key > node->key)
			node->right = Delete(node->right, key);
		else if (key == node->key) {
			if (node->left != nullptr && node->right != nullptr) {
				// 左右子结点都不为空
				Node<Key, Value> *DeletedRight = node->right;
				// 找到后继结点sub(subsequent)
				Node<Key, Value> *sub = DeletedRight;
				Node<Key, Value> *pre = node;
				while (sub->left != nullptr) {
					pre = sub;
					sub = sub->left;
				}
				// 让后继结点代替deleted的位置
				// sub和右结点是同一结点时需要特殊处理
				if (sub == DeletedRight) {
					sub->left = node->left;
					delete node;
					return sub;
				}
				else {
					sub->right = DeletedRight;
					sub->left = node->left;
					pre->left = nullptr;
					delete node;
					return sub;
				}
			}
			else if (node->left == nullptr && node->right == nullptr) {
				delete node;
				return nullptr;
			}
			else if (node->left == nullptr) {
				Node<Key, Value> *newRight = node->right;
				delete node;
				return newRight;
			}
			else if (node->right == nullptr) {
				Node<Key, Value> *newLeft = node->left;
				delete node;
				return newLeft;
			}
		}
		return node;
	}
	// 用于析构函数
	void destructor(Node<Key, Value> *node) {
		if (node == nullptr)
			return;
		destructor(node->left);
		destructor(node->right);
		delete node;
	}
public:
	BST() {
		root = nullptr;
	}
	~BST() {
		destructor(root);
	}
	int size() {
		return size(root);
	}
	Value get(Key key) {
		return get(root, key);
	}
	void put(Key key, Value val) {
		root = put(root, key, val);
	}
	Key min() {
		Node<Key, Value> *cur = root;
		while (cur->left != nullptr)
			cur = cur->left;
		return cur->key;
	}
	Key max() {
		Node<Key, Value> *cur = root;
		while (cur->right != nullptr)
			cur = cur->right;
		return cur->key;
	}
	Key floor(Key key) {
		Node<Key, Value> *cur = root;
		while (cur->left != nullptr && key < cur->key)
			cur = cur->left;
		if (cur->right != nullptr && cur->right->key <= key)
			return cur->right->key;
		else
			return cur->key;
	}
	Key celling(Key key) {
		Node<Key, Value> *cur = root;
		while (cur->right != nullptr && key > cur->key)
			cur = cur->right;
		if (cur->left != nullptr && cur->left->key >= key)
			return cur->left->key;
		else
			return cur->key;
	}
	Key select(int k) {
		return select(root, k)->key;
	}
	int rank(Key key) {
		return rank(root, key);
	}
	void deleteMin() {
		Node<Key, Value> *pre = root;
		Node<Key, Value> *cur = root->left;
		if (cur == nullptr) {
			if (root->right != nullptr) {
				root = root->right;
				delete pre;
			}
			else {
				delete root;
				root = nullptr;
			}
			updateN(root);
			return;
		}
		while (cur->left != nullptr) {
			cur = cur->left;
			pre = pre->left;
		}
		pre->left = cur->right;
		delete cur;
		updateN(root);
	}
	void Delete(Key key) {
		root = Delete(root, key);
		updateN(root);
		/*Node<Key, Value> *deleted = root;
		Node<Key, Value> *pre = root;
		if (deleted == nullptr)
			return;
		bool isLeft = true;	// 用于被删除结点只有一个子结点的情况
		while (key != deleted->key) {
			pre = deleted;
			if (key < deleted->key)
				deleted = deleted->left;
			else {
				deleted = deleted->right;
				isLeft = false;
			}
			if (deleted == nullptr)
				return;
		}
		// 此时deleted指向被删除结点
		// deleted有左右两个子结点
		if (deleted->left != nullptr && deleted->right != nullptr) {
			// 找到后继结点sub
			Node<Key, Value> *sub = deleted->right;	// subsequent
			while (sub->left != nullptr)
				sub = sub->left;
			// 让后继结点代替deleted的位置
			deleted->right->left = sub->right;
			sub->right = deleted->right;
			sub->left = deleted->left;
			if (deleted == root)
				root = sub;
			else if (isLeft)
				pre->left = sub;
			else
				pre->right = sub;
		}
		// 只有左子结点
		else if (deleted->left != nullptr) {
			if (deleted == root)
				root = deleted->left;
			else if (isLeft)
				pre->left = deleted->left;
			else
				pre->right = deleted->left;
		}
		// 只有右子结点
		else {
			if (deleted == root)
				root = deleted->right;
			else if (isLeft)
				pre->left = deleted->right;
			else
				pre->right = deleted->right;
		}
		delete deleted;
		updateN(root);*/
	}
	void print() {
		print(root);
	}
	std::vector<Key> range(Key lo, Key hi) {
		std::vector<Key> arr;
		range(arr, root, lo, hi);
		return arr;
	}
};