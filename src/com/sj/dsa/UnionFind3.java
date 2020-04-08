package com.sj.dsa;

/**
 * v3, 基于树大小size的优化
 * 记录subSize, 让元素个数小的树根指向个数多的树的根
 * 让节点之间形成一个树结构(合并之前是森林结构)，树中节点之间的关系是孩子指向父亲
 */
public class UnionFind3 implements UF {
    private int[] parent;
    // subSize[i] 表示以i为根的集合中元素个数
    private int[] subSize;

    public UnionFind3(int size) {
        parent = new int[size];
        subSize = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            subSize[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找过程，查找元素p所对应的集合编号
    // O(h)复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // 根据两个元素所在树的元素的个数不同判断合并方向
        // 将元素个数少的集合合并到元素个数多的集合上(元素多，一般高度比较高)
        if (subSize[pRoot] < subSize[qRoot]) {
            parent[pRoot] = qRoot;
            subSize[qRoot] += subSize[pRoot];
        } else {
            parent[qRoot] = pRoot;
            subSize[pRoot] += subSize[qRoot];
        }
    }
}
