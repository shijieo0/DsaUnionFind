package com.sj.dsa;

/**
 * 自定义并查集接口
 * @author ShiJie
 * @since 2020-04-04
 */
public interface UF {
    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);
}
