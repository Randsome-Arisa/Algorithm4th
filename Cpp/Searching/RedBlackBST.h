#pragma once
#include <iostream>

/*
 * Node *rotateLeft(Node *node)			将node的右红链接左旋转
 * void flipColor(Node *node)			将node的两个红链接转为黑色，将node的链接转为红色 
 */


const bool RED = true;
const bool BLACK = false;

template<typename Key, typename Value>
struct Node {
	Key key;
	Value val;
	Node *left;
	Node *right;
	int N;
	// 一个结点的颜色是其与其父结点的链接的颜色
	bool color;	// true表示红，false表示黑

	Node(Key _key, Value _val, int N, bool color) : key(_key), val(_val) {
		this->N = N;
		this->color = color;
	}
};

template<typename Key, typename Value>
class RedBlackBST {
private:
	Node<Key, Value> *root;

	int size(Node<Key, Value> *node) { return node ? node->N : 0; }
	bool isRed(Node<Key, Value> *node) { return node ? node->color : false; }
	// 将node结点的右红链接左旋转
	Node<Key, Value> *rotateLeft(Node<Key, Value> *node) {
		Node<Key, Value> *nodeRight = node->right;
		node->right = nodeRight->left;
		nodeRight->left = node;
		nodeRight->color = node->color;
		node->color = RED;
		nodeRight->N = node->N;
		node->N = 1 + size(node->left) + size(node->right);
		return nodeRight;
	}
	Node<Key, Value> *rotateRight(Node<Key, Value> *node) {
		Node<Key, Value> *nodeLeft = node->left;
		node->left = nodeLeft->right;
		nodeLeft->right = node;
		nodeLeft->color = node->color;
		node->color = RED;
		nodeLeft->N = node->N;
		node->N = 1 + size(node->left) + size(node->right);
		return nodeLeft;
	}
	void flipColor(Node<Key, Value> *node) {
		node->color = RED;
		node->left->color = BLACK;
		node->right->color = BLACK;
	}

	Node<Key, Value> *put(Node<Key, Value> *node, Key key, Value val) {
		if (node == nullptr)
			return new Node(key, val, 1, RED);
		if (key < node->key)
			node->left = put(node->left, key, val);
		else if (key > node->key)
			node->right = put(node->right, key, val);
		else
			node->val = val;

		// 注意下面的顺序不能颠倒，后面的每一种情况都可能是前一种情况的结果

		// 当红链接是右链接时，左旋转
		if (isRed(node->right) && !isRed(node->left))
			node = rotateLeft(node);
		// 当左子结点和它的左子结点都是红链接时，右旋转
		if (isRed(node->left) && isRed(node->left->left))
			node = rotateRight(node);
		// 当左右子结点都是红链接时
		if (isRed(node->left) && isRed(node->right))
			flipColor(node);

		node->N = 1 + size(node->left) + size(node->right);
		return node;
	}

public:
	void put(Key key, Value val) {
		root = put(root, key, val);
		root->color = BLACK;
	}
};