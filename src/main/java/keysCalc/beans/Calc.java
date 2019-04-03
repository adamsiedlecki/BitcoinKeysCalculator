package keysCalc.beans;

import org.bitcoinj.core.*;
import org.bitcoinj.params.MainNetParams;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;

public class Calc {


    @Autowired
    SHAUtility shaUtility;

    public String calcWIFFromSimple(String simple) {
        String temporary = "80" + simple; //
        //System.out.println("2. "+temporary);
        String hashed = shaUtility.getSHA(temporary);
        //System.out.println("3. "+hashed);
        String hashedTwice = shaUtility.getSHA(hashed);
        //System.out.println("4. "+hashedTwice);
        String checksum = "" + hashedTwice.toCharArray()[0] + hashedTwice.toCharArray()[1] + hashedTwice.toCharArray()[2] + hashedTwice.toCharArray()[3] + hashedTwice.toCharArray()[4] + hashedTwice.toCharArray()[5] + hashedTwice.toCharArray()[6] + hashedTwice.toCharArray()[7];
        //System.out.println("5. Checksum: "+checksum);
        String temoporary = temporary + checksum;
        String result = Base58.encode(shaUtility.hexStringToByteArray(temoporary));

        return result;
    }

    public String calcPublicFromWIF(String wif) {
        NetworkParameters params = MainNetParams.get();
        ECKey key;
        if (wif.length() == 51 || wif.length() == 52) {
            DumpedPrivateKey dumpedPrivateKey = DumpedPrivateKey.fromBase58(params, wif);
            key = dumpedPrivateKey.getKey();
        } else {
            BigInteger privKey = Base58.decodeToBigInteger(wif);
            key = ECKey.fromPrivate(privKey);
        }
        String publicKey = LegacyAddress.fromKey(params, key).toString();

        return publicKey;
    }

    public String calcSimpleFromWIF(String wif) {
        byte[] normalFormat = Base58.decode(wif);
        String temp = shaUtility.bytesToHex(normalFormat);
        temp = temp.substring(2, temp.length() - 8);
        return temp;
    }


}
