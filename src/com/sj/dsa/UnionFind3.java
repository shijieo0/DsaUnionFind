package com.sj.dsa;

/**
 * v3, ��������Сsize���Ż�
 * ��¼subSize, ��Ԫ�ظ���С������ָ�����������ĸ�
 * �ýڵ�֮���γ�һ�����ṹ(�ϲ�֮ǰ��ɭ�ֽṹ)�����нڵ�֮��Ĺ�ϵ�Ǻ���ָ����
 */
public class UnionFind3 implements UF {
    private int[] parent;
    // subSize[i] ��ʾ��iΪ���ļ�����Ԫ�ظ���
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

    // ���ҹ��̣�����Ԫ��p����Ӧ�ļ��ϱ��
    // O(h)���Ӷȣ�hΪ���ĸ߶�
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while(p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    // �鿴Ԫ��p��Ԫ��q�Ƿ�����һ������
    // O(h)���Ӷȣ�hΪ���ĸ߶�
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // �ϲ�Ԫ��p��Ԫ��q�����ļ���
    // O(h)���Ӷȣ�hΪ���ĸ߶�
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        // ��������Ԫ����������Ԫ�صĸ�����ͬ�жϺϲ�����
        // ��Ԫ�ظ����ٵļ��Ϻϲ���Ԫ�ظ�����ļ�����(Ԫ�ض࣬һ��߶ȱȽϸ�)
        if (subSize[pRoot] < subSize[qRoot]) {
            parent[pRoot] = qRoot;
            subSize[qRoot] += subSize[pRoot];
        } else {
            parent[qRoot] = pRoot;
            subSize[pRoot] += subSize[qRoot];
        }
    }
}