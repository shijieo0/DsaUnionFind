package com.sj.dsa;

/**
 * �Զ��岢�鼯�ӿ�
 * @author ShiJie
 * @since 2020-04-04
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
