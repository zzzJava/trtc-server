package com.zzz.videoserver.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
    private static Random random = new Random();
    private static Set<Integer> set = new HashSet<>();

    /**
     * 生成指定长度的随机正整数。
     * @param len： 数字长度
     * @return
     */
    public static Integer genRandomInt(int len) {
        int base = (int) Math.pow(10, len-1); // len长度的最小值
        int rs = random.nextInt(base * 10); // 生成随机数，上限为base*10
        if (rs < base) {
            rs = rs + base;
        }
        if (set.contains(rs)) { // 如果当前房间号已经存在，重新生成
            rs = genRandomInt(len);
        }
        return rs;
    }

    public static void removeInt(int n) {
        if (set.contains(n)) {
            set.remove(n);
        }
    }
}
