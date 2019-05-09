package com.joyner.hack.jvm.chapter9_demo;

/**
 * Bytes数组处理工具
 * @author
 */
public class ByteUtils {

    public static int bytes2Int(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int) b[i]) & 0xff;
            /**
             * 例如第一个字节是：
             * 0000 0011
             * 第二个字节是：
             * 0001 0010
             * 因为是两个字节所以，最后的结果是：0000 0011 | 0001 0010
             * 那么这里的逻辑是：取到第一个字节，往前挪8个bit（(--len) * 8）。得到
             * 0000 0011|0000 0000  取这个的int值int1
             * 取第二个字节：往前挪0个bit位，因为--len位0了。得到
             * 0001 0010 取这个int值int2.
             * 最后相加，就得到真正的int值。
             *
             */
            n <<= (--len) * 8;//左移动高位丢弃，低位补零
            sum = n + sum;
        }
        return sum;
    }

    public static byte[] int2Bytes(int value, int len) {
        byte[] b = new byte[len];
        for (int i = 0; i < len; i++) {
            b[len - i - 1] = (byte) ((value >> 8 * i) & 0xff);
        }
        return b;
    }

    public static String bytes2String(byte[] b, int start, int len) {
        return new String(b, start, len);
    }

    public static byte[] string2Bytes(String str) {
        return str.getBytes();
    }

    public static byte[] bytesReplace(byte[] originalBytes, int offset, int len, byte[] replaceBytes) {
        byte[] newBytes = new byte[originalBytes.length + (replaceBytes.length - len)];
        System.arraycopy(originalBytes, 0, newBytes, 0, offset);
        System.arraycopy(replaceBytes, 0, newBytes, offset, replaceBytes.length);
        System.arraycopy(originalBytes, offset + len, newBytes, offset + replaceBytes.length, originalBytes.length - offset - len);
        return newBytes;
    }
}

