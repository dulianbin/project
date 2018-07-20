package com.zhaopin.report.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 * @fileName MD5Util.java
 * @date 2013-8-13
 * @time 上午10:56:54
 * @author wst
 *
 */
public final class MD5Util {
	
	private final static char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	/**
	 * @date 2013-8-13
	 * @time 上午10:57:03
	 * @author wst
	 * @param s
	 * @return String
	 */
	public static final String MD5(String s) {
		byte[] btInput = s.getBytes();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	
	
	/**
     * 转换字节数组为16进制字串
     * @param b 字节数组
     * @return 16进制字串
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) {
            resultSb.append(byteToHexString(aB));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return String.valueOf(hexDigits[d1]) + String.valueOf(hexDigits[d2]);
    }

    /**
     * MD5编码
     * @param origin 原始字符串
     * @return 经过MD5加密之后的结果
     */
    public static String MD5Encode(String origin) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }

    /**
     * 获取该输入流的MD5值
     *
     * @param is
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5(InputStream is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] dataBytes = new byte[1024];
        int nread = 0;
        while ((nread = is.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        // convert the byte to hex format
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }

    /**
     * 获取该输入流的MD5值
     *
     * @param is
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5(byte[] is) throws NoSuchAlgorithmException, IOException {
        StringBuffer md5 = new StringBuffer();
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(is);
        byte[] mdbytes = md.digest();
        for (int i = 0; i < mdbytes.length; i++) {
            md5.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return md5.toString();
    }

    /**
     * 获取该文件的MD5值
     *
     * @param file
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5(File file) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(file);
        return getMD5(fis);
    }

    /**
     * 获取指定路径文件的MD5值
     *
     * @param path
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static String getMD5(String path) throws NoSuchAlgorithmException, IOException {
        FileInputStream fis = new FileInputStream(path);
        return getMD5(fis);
    }

    /**
     * 校验该输入流的MD5值
     *
     * @param is
     * @param toBeCheckSum
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static boolean md5CheckSum(InputStream is, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(is).equals(toBeCheckSum);
    }

    /**
     * 校验该文件的MD5值
     *
     * @param file
     * @param toBeCheckSum
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static boolean md5CheckSum(File file, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(file).equals(toBeCheckSum);
    }

    /**
     * 校验指定路径文件的MD5值
     *
     * @param path
     * @param toBeCheckSum
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException
     */
    public static boolean md5CheckSum(String path, String toBeCheckSum) throws NoSuchAlgorithmException, IOException {
        return getMD5(path).equals(toBeCheckSum);
    }

    public static void main(String[] args) {
		System.out.println(MD5Encode("123456"));
	}
}
