package c.s.sample;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.fisco.bcos.channel.client.PEMManager;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.ECKeyPair;
import org.fisco.bcos.web3j.crypto.Keys;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;

public class SampleTest {

    @Test
    public void test1() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        Credentials c = Credentials.create(Keys.createEcKeyPair());
        System.out.println("address is " + c.getAddress());
        System.out.println("private key is " + c.getEcKeyPair().getPrivateKey());
        System.out.println("public key is " + c.getEcKeyPair().getPublicKey());
    }

    @Test
    public void test2() {
//        0x31ca3ad629f75009cb50b4cad7469ba2cde409bc  address
//        61060911937917164353876425559627834300414702635494070316851698343831379815376  private
//        8416688701179378060033105902647540333473307574504211215788623713409920547184234936668854971012164818102785321762419496681451270330214648550598223342960480
        Credentials c = Credentials.create("61060911937917164353876425559627834300414702635494070316851698343831379815376"
//                ,"8416688701179378060033105902647540333473307574504211215788623713409920547184234936668854971012164818102785321762419496681451270330214648550598223342960480"
        );
        System.out.println("address is " + c.getAddress());
        System.out.println("private key is " + c.getEcKeyPair().getPrivateKey());
        System.out.println("public key is " + c.getEcKeyPair().getPublicKey());

//        address is 0x46d9015d8b86cfa6d3cd2b31948d3be2881f53da
//        private key is 197640514613490815380625020409859237289219433777326256669192520632166188730203718491547194230
//        public key is 10870507528896398687105578932568998069286073543578328839203844182477889776742775965411182091658230866592322578079686159605377068370469713265650065301608122

    }

    @Test
    public void test3() {
//        ECKeyPair keyPair = new ECKeyPair(new BigInteger("61060911937917164353876425559627834300414702635494070316851698343831379815376")
//        ,new BigInteger("8416688701179378060033105902647540333473307574504211215788623713409920547184234936668854971012164818102785321762419496681451270330214648550598223342960480")
//                );

        ECKeyPair keyPair = new ECKeyPair(new BigInteger("8943845161097980948432964586575850450163087254348779730977814972104536788998"),
//        0xda3d9ca7458f2a0b644a66c64f593321ea1484ac
                new BigInteger("7920664357847965522258747618148967240766487804383623838137641744135791651708708174798834359586701583358878566273440199136316932250035791997010416680156914"));
        Credentials c = Credentials.create(keyPair);
        System.out.println("address is " + c.getAddress());
        System.out.println("private key is " + c.getEcKeyPair().getPrivateKey());
        System.out.println("public key is " + c.getEcKeyPair().getPublicKey());
    }

    @Test
    public void test4() throws IOException, InvalidKeySpecException, CertificateException, NoSuchAlgorithmException, KeyStoreException, NoSuchProviderException, UnrecoverableKeyException {
        PEMManager pem = new PEMManager();
//        ByteInputStream inputStream = new ByteInputStream();
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(new String("-----BEGIN PRIVATE KEY-----\n" +
//                "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgrz/BT63fxuQnoUGguzs1\n" +
//                "CEazy/hy0diXtl96oa9AMKmhRANCAASlTQ2R27Uy8dO6QO0XdF/wF5k6j/EOo7gR\n" +
//                "sZbzLZIHG/wSAF6hRWbEVout8RXk/GHAA55jRjKA6l/PKoCZTXnT\n" +
//                "-----END PRIVATE KEY-----\n").getBytes());
//        inputStream.read();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(new String("-----BEGIN PRIVATE KEY-----\n" +
                "MIGEAgEAMBAGByqGSM49AgEGBSuBBAAKBG0wawIBAQQgE8YJdtLKtfZvqahfSPCx\n" +
                "ZleEcjYL4v02tGLLlByokAahRANCAASXO2aiJ5jUJc3HwSDEJ2MxpKJMHFl99utn\n" +
                "ohHlUV6NNUdPHvUjWjSTGR1rjWQsvU4Seo+u90EJpPjPnIuLcb7y\n" +
                "-----END PRIVATE KEY-----").getBytes());
        pem.load(inputStream);
        Credentials c = Credentials.create(pem.getECKeyPair());
        System.out.println("address is " + c.getAddress());
        System.out.println("private key is " + c.getEcKeyPair().getPrivateKey());
        System.out.println("public key is " + c.getEcKeyPair().getPublicKey());
    }
}
