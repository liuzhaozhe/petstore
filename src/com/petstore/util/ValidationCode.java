package com.petstore.util;

import java.util.Arrays;

/**
 * Created by hezhujun on 2016/4/8.
 * 生成验证码内容
 */
public class ValidationCode {

    /**
     * 验证码难度级别，simple只包含数字，medium只包含数字和小写英文，hard包含数字和大小写英文
     */
    public enum ValidationCodeLevel {
        simple, medium, hard
    }

    ;

    /**
     * 产生默认验证码，4位中等难度
     *
     * @return
     */
    public static String getValidationCode() {
        return getValidationCode(4,ValidationCodeLevel.medium,false);
    }

    /**
     * 产生长度和难度任意的验证码
     *
     * @param length   长度
     * @param level    难度级别
     * @param isRepeat 是否能够出现重复字符
     * @return
     */
    public static String getValidationCode(int length, ValidationCodeLevel level, boolean isRepeat) {

        // 随机抽取len个字符
        int len = length;

        // 字符集合（除去易混淆的数字0，1，字母l，字母0，字母O）
        char[] codes = {
                '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        };

        // 根据不同的难度截取字符数组
        if (level == ValidationCodeLevel.simple) {
            codes = Arrays.copyOfRange(codes, 0, 8);
        } else if (level == ValidationCodeLevel.medium) {
            codes = Arrays.copyOfRange(codes, 0, 32);
        }

        // 字符集长度
        int n = codes.length;

        // 抛出运行时异常
        if (len > n && isRepeat) {
            throw new RuntimeException(
                    String.format("调用ValidationCode.getValidationCode(%1$s,%2$s,%3$s)出现异常，" +
                                    "当isRepeat为%3$s时，传入参数不能大于%4$s",
                            len, level, isRepeat, n)
            );
        }

        // 存放抽取出来的字符
        char[] result = new char[len];
        // 判断能否出现重复的字符
        if (isRepeat) {
            for (int i = 0; i < result.length; i++) {
                // 索引 0 and n-1
                int index = (int) (Math.random() * n);

                // 将result中第i个元素设置为codes[index]存放的数值
                result[i] = codes[index];
            }
        } else {
            for (int i = 0; i < result.length; i++) {
                // 索引 0 and n-1
                int index = (int) (Math.random() * n);

                // 将result中第i个元素设置为codes[index]存放的数值
                result[i] = codes[index];

                // 必须确保不会再抽取到那个字符，因为所有的字符必须不相同。
                // 将字符集数组的最后一个字符改写codes[r]，并将n减1
                codes[index] = codes[n - 1];
                n--;
            }
        }
        return String.valueOf(result);
    }
}
