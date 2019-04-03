package keysCalc.beans;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;


@Component
public class SHAUtility {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public String getSHA(String value) {
        byte[] hexByte = hexStringToByteArray(value);
        String hash = DigestUtils.sha256Hex(hexByte);
        return hash;
    }

    public byte[] hexStringToByteArray(String hex) {
        if (!(hex.length() % 2 == 0)) {
            hex = "0" + hex;
        }
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    public String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
