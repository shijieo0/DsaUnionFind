package com.sj.dsa.leetcode;

/**
 * @author ShiJie
 * @since 2020-04-08
 */
public class FriendCircles {
    class UnionFind {
        private int count;
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            count = n;
        }

        public int find(int p) {
            if (p < 0 || p >= parent.length) {
                throw new IllegalArgumentException("i out of bound.");
            }
            while(p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);

            if (pRoot == qRoot) {
                return;
            }

            parent[pRoot] = qRoot;
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    public int findCircleNum(int[][] M) {
        UnionFind uf = new UnionFind(M.length);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.getCount();
    }
}
