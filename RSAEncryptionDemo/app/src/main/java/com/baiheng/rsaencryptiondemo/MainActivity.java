package com.baiheng.rsaencryptiondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.jca.ProviderContext;
import org.jose4j.jwa.AlgorithmFactory;
import org.jose4j.jwa.AlgorithmFactoryFactory;
import org.jose4j.jwe.ContentEncryptionAlgorithm;
import org.jose4j.jwe.ContentEncryptionAlgorithmIdentifiers;
import org.jose4j.jwe.ContentEncryptionParts;
import org.jose4j.jwe.JsonWebEncryption;
import org.jose4j.jwe.KeyManagementAlgorithmIdentifiers;
import org.jose4j.jwx.Headers;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "MainActivity";
    private String mEncryptionContent = "你好我的2019，我好想你啊";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        KeyPair keyPair = RSAUtil.generateRSAKeyPair();
//        PublicKey publicKey = keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        try {
//            //加密
//            byte[] encryptByte = RSAUtil.encryptData(mEncryptionContent.getBytes(),publicKey);
//            String afterencrypt = Base64Utils.encode(encryptByte);
//            Log.d(TAG,"encryption data enqual" + afterencrypt);
//            //解密
//            byte[] decryptByte = RSAUtil.decryptData(Base64Utils.decode(afterencrypt),privateKey);
//            String decryptStr = new String(decryptByte);
//            Log.d(TAG, "decryption String equal" + decryptStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            String message = "这是RSA1_5加密算法的jwe示例。";
            System.out.println("需加密的明文："+message);
            JsonWebEncryption senderJwe = new JsonWebEncryption();
            //设置明文
            senderJwe.setPlaintext(message);
            //指定算法
            senderJwe.setAlgorithmHeaderValue(KeyManagementAlgorithmIdentifiers.RSA1_5);
            //指定 RSA1_5 对应的 publicKey
            RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(new BigInteger(JoseConstants.PUBLIC_MODULUS,JoseConstants.RSA_KEY_RADIX),
                    new BigInteger(JoseConstants.PUBLIC_EXPONENT,JoseConstants.RSA_KEY_RADIX));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(rsaPublicKeySpec);
            senderJwe.setKey(publicKey);
            //指定算法
            senderJwe.setEncryptionMethodHeaderParameter(ContentEncryptionAlgorithmIdentifiers.AES_128_GCM);
            //指定加密 plainText 所需的 cek 为了方便 自定义为32byte的0
            senderJwe.setContentEncryptionKey(new byte[32]);
            //指定加密 plainText 所需的 salt 为了方便 自定义为12byte的0
            senderJwe.setIv(new byte[12]);
            //设置自定义的 header
            senderJwe.setHeader("headerCustom","hhh");
            String compactSerialization = senderJwe.getCompactSerialization();
            System.out.println("加密后的结果：" + compactSerialization);


            //============================================== 库解密 =============================================================
            // 解密对象
            JsonWebEncryption receiverJwe = new JsonWebEncryption();
            // 设置算法对应的key
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(JoseConstants.PUBLIC_MODULUS,JoseConstants.RSA_KEY_RADIX),
                    new BigInteger(JoseConstants.PRIVATE_EXPONENT,JoseConstants.RSA_KEY_RADIX));
            PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
            receiverJwe.setKey(privateKey);
            // 设置密文
            receiverJwe.setCompactSerialization(compactSerialization);
            // 得到明文
            String plaintext = receiverJwe.getPlaintextString();
            System.out.println("解密结果是: " + plaintext);
            System.out.println("========================================= 分步解密 =========================================================================");
            String[] split = compactSerialization.split("\\.");

            String headerSerializa = split[0];
            String encCek = split[1];
            String encIv = split[2];
            String encText = split[3];
            String encTag = split[4];

            //先解密得到cek
            byte[] cek = cekDecryption(Base64.decode(encCek,Base64.DEFAULT), JoseConstants.RSA_KEY_RADIX, JoseConstants.PUBLIC_MODULUS, JoseConstants.PRIVATE_EXPONENT);
            //
            System.out.println(new String(Base64.decode(headerSerializa,Base64.DEFAULT)));
            //组装密文
            byte[] encTextByte = Base64.decode(encText,Base64.DEFAULT);
            byte[] encTagByte = Base64.decode(encTag,Base64.DEFAULT);

            byte[] encTextAll = new byte[encTextByte.length + encTagByte.length];
            System.arraycopy(encTextByte,0,encTextAll,0,encTextByte.length);
            System.arraycopy(encTagByte,0,encTextAll,encTextByte.length,encTagByte.length);
            byte[] aad = headerSerializa.getBytes("US-ASCII");

            byte[] s = decryptUseCekAndSalt(cek,Base64.decode(encIv,Base64.DEFAULT),encTextAll, aad,split[0]);
            System.out.println(new String(s,JoseConstants.UTF_8));

        } catch (Exception e) {

        }



    }

    /**
     *  解密cek 使用公钥modulus 与 私钥 exponent RSA
     * @param encCek
     * @param radix
     * @param publicModulus
     * @param privateExponent
     * @return
     * @throws Exception
     */
    public static byte[] cekDecryption(byte[] encCek, int radix, String publicModulus, String privateExponent) throws Exception{
        byte[] cek = null;
        try {
            RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(new BigInteger(publicModulus,radix),new BigInteger(privateExponent,radix));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(rsaPrivateKeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", BouncyCastleProvider.PROVIDER_NAME);
            cipher.init(2,privateKey);
            cek = cipher.doFinal(encCek);
        }catch (InvalidKeyException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }catch (InvalidKeySpecException e){
            e.printStackTrace();
        }catch (BadPaddingException e){
            e.printStackTrace();
        }catch (IllegalBlockSizeException e){
            e.printStackTrace();
        }catch (NoSuchPaddingException e){
            e.printStackTrace();
        }catch (NoSuchProviderException e){
            e.printStackTrace();
        }finally {
            if (encCek == null){
                throw new Exception("cekDecryption 解密失败");
            }
        }
        return cek;
    }

    /**
     *  使用 cek / salt 解密 data 对称
     * @param cek
     * @param salt
     * @param data
     * @param aad 可以为null
     * @return
     */
    public static byte[] decryptUseCekAndSalt(byte[] cek, byte[] salt, byte[] data, byte[] aad,String headerEnc) throws Exception {
        byte[] out = null;
        try {
            AlgorithmFactoryFactory factoryFactory = AlgorithmFactoryFactory.getInstance();
            AlgorithmFactory<ContentEncryptionAlgorithm> factory = factoryFactory.getJweContentEncryptionAlgorithmFactory();
            ContentEncryptionAlgorithm alg = factory.getAlgorithm("A128GCM");

            byte[] encTextByte = new byte[data.length -16];
            byte[] encTagByte = new byte[16];

            System.arraycopy(data,0,encTextByte,0,encTextByte.length);
            System.arraycopy(data,data.length-16,encTagByte,0,encTagByte.length);

            Headers headers = new Headers();
            headers.setFullHeaderAsJsonString(new String(Base64.decode(headerEnc,Base64.DEFAULT),"UTF-8"));

            ContentEncryptionParts contentEncryptionParts = new ContentEncryptionParts(salt,encTextByte,encTagByte);
            out = alg.decrypt(contentEncryptionParts, aad, cek, headers, new ProviderContext());
            return out;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out == null){
                throw new Exception("decryptUseCekAndSalt 解密失败");
            }
        }
        return null;
    }
}
