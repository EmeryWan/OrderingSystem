package pers.emery.utils;

import java.util.Random;

/**
 * 生成唯一的主键
 *
 * 时间 + 随机数
 *
 * @author emery
 */
public class KeyUtil {

    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900_000) + 100_000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
