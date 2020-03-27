package com.baiheng.fontaligndemo; /**
 * 验签工具类，调用getVivoSign可生成验签
 * Signature tool class, call getVivoSign to generate signature verification
 */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VivoSignUtils {

    private static final String TAG = VivoSignUtils.class.getSimpleName();

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static final String CHARSET_UTF8 = "UTF-8";

    private final static String KEY_SIGN = "sign";
    private final static String KEY_SIGN_TYPE = "signType";

    private static final String QSTRING_EQUAL = "=";
    private static final String QSTRING_SPLIT = "&";

    /**
     * 验签(Verify signature)
     *
     * @param para Parameter set
     * @param key  AppSecret
     * @return Verify signature result
     */
    public static boolean verifySignature(Map<String, String> para, String key) {
        // Remove null values and signature parameters
        Map<String, String> filteredReq = paraFilter(para);
        // get signature
        String signature = getVivoSign(filteredReq, key);
        // Get the signature value in the parameter
        String respSignature = para.get(KEY_SIGN);
        // Compare signature values
        if (null != respSignature && respSignature.equals(signature)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取签名(get vivo sign)
     *
     * @param para Signature elements <key,value>
     * @param key  AppSecret
     * @return signature result
     */
    public static String getVivoSign(Map<String, String> para, String key) {
        // Remove null values and signature parameters
        Map<String, String> filteredReq = paraFilter(para);

        String prestr = createLinkString(filteredReq, true, false); // Get the string to be signed
        // 需要对map进行sort，不需要对value进行URL编码

        prestr = prestr + QSTRING_SPLIT + md5Hex(key);

        return md5Hex(prestr);
    }

    /**
	 * 移除请求要素中的空值和签名要素
     * Remove null and signature parameters from request elements
     *
     * @param para request elements
     * @return Request elements with null values and signature parameters removed
     */
    private static Map<String, String> paraFilter(Map<String, String> para) {
        Map<String, String> result = new HashMap<String, String>();

        if (para == null || para.size() <= 0) {
            return result;
        }

        for (String key : para.keySet()) {
            String value = para.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase(KEY_SIGN) || key.equalsIgnoreCase
					(KEY_SIGN_TYPE)) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /**
     * 把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * Request elements stitched into a string
     *
     * @param para   Request elements
     * @param sort   Whether to sort in ascending order based on the key value
     * @param encode whether need url coding
     * @return 拼接成的字符串
     */
    private static String createLinkString(Map<String, String> para, boolean sort, boolean encode) {
        List<String> keys = new ArrayList<String>(para.keySet());

        if (sort)
            Collections.sort(keys);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = para.get(key);

            if (encode) {
                try {
                    value = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                }
            }

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                sb.append(key).append(QSTRING_EQUAL).append(value);
            } else {
                sb.append(key).append(QSTRING_EQUAL).append(value).append(QSTRING_SPLIT);
            }
        }

        return sb.toString();
    }

    private static String md5Hex(String data) {
        if (data == null || data.trim().equals("")) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] md5Data = digest.digest(data.getBytes(CHARSET_UTF8));
            return byteToHexString(md5Data);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String byteToHexString(byte[] data) {
        char[] buf = new char[data.length * 2];
        int index = 0;
        for (byte b : data) { // Conversion using bit operations
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }

}
