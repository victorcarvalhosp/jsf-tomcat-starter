package br.com.libercode.core.util;

public class Conversion {
    private static String[] hexDigits = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public Conversion() {
    }

    public static String byteToHexString(byte b) {
        int n = b;
        if (b < 0) {
            n = 256 + b;
        }

        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";

        for(int i = 0; i < b.length; ++i) {
            result = result + byteToHexString(b[i]);
        }

        return result;
    }

    public static String byteArrayToBase64String(byte[] b, int len) {
        String s = "";
        int n = len / 3;
        int m = len % 3;

        int i;
        for(i = 0; i < n; ++i) {
            i = i * 3;
            s = s + toBase64(b[i], b[i + 1], b[i + 2]);
        }

        if (m == 1) {
            s = s + toBase64(b[len - 1]);
        } else if (m == 2) {
            s = s + toBase64(b[len - 2], b[len - 1]);
        }

        String result = "";
        len = s.length();
        n = len / 64;
        m = len % 64;

        for(i = 0; i < n; ++i) {
            result = result + s.substring(i * 64, (i + 1) * 64) + "\n";
        }

        if (m > 0) {
            result = result + s.substring(n * 64, len) + "\n";
        }

        return result;
    }

    public static String byteArrayToBase64String(byte[] b) {
        return byteArrayToBase64String(b, b.length);
    }

    private static String toBase64(byte b1, byte b2, byte b3) {
        int[] digit = new int[]{(b1 & 252) >>> 2, (b1 & 3) << 4, 0, 0};
        digit[1] |= (b2 & 240) >> 4;
        digit[2] = (b2 & 15) << 2;
        digit[2] |= (b3 & 192) >> 6;
        digit[3] = b3 & 63;
        String result = "";

        for(int i = 0; i < digit.length; ++i) {
            result = result + base64Digit(digit[i]);
        }

        return result;
    }

    private static String toBase64(byte b1, byte b2) {
        int[] digit = new int[]{(b1 & 252) >>> 2, (b1 & 3) << 4, 0};
        digit[1] |= (b2 & 240) >> 4;
        digit[2] = (b2 & 15) << 2;
        String result = "";

        for(int i = 0; i < digit.length; ++i) {
            result = result + base64Digit(digit[i]);
        }

        result = result + "=";
        return result;
    }

    private static String toBase64(byte b1) {
        int[] digit = new int[]{(b1 & 252) >>> 2, (b1 & 3) << 4};
        String result = "";

        for(int i = 0; i < digit.length; ++i) {
            result = result + base64Digit(digit[i]);
        }

        result = result + "==";
        return result;
    }

    private static char base64Digit(int i) {
        if (i < 26) {
            return (char)(65 + i);
        } else if (i < 52) {
            return (char)(97 + (i - 26));
        } else if (i < 62) {
            return (char)(48 + (i - 52));
        } else {
            return (char)(i == 62 ? '+' : '/');
        }
    }

    public static byte[] base64StringToByteArray(String s) throws NumberFormatException {
        String t = "";

        int len;
        for(len = 0; len < s.length(); ++len) {
            char c = s.charAt(len);
            if (c != '\n') {
                if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '+' && c != '/') {
                    if (c != '=') {
                        throw new NumberFormatException();
                    }
                    break;
                }

                t = t + c;
            }
        }

        len = t.length();
        int n = 3 * (len / 4);
        switch(len % 4) {
            case 1:
                throw new NumberFormatException();
            case 2:
                len += 2;
                ++n;
                t = t + "==";
                break;
            case 3:
                ++len;
                n += 2;
                t = t + "=";
        }

        byte[] b = new byte[n];

        for(int i = 0; i < len / 4; ++i) {
            byte[] temp = base64ToBytes(t.substring(4 * i, 4 * (i + 1)));

            for(int j = 0; j < temp.length; ++j) {
                b[3 * i + j] = temp[j];
            }
        }

        return b;
    }

    private static byte[] base64ToBytes(String s) {
        int len = 0;

        for(int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '=') {
                ++len;
            }
        }

        int[] digit = new int[len];

        for(int i = 0; i < len; ++i) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                digit[i] = c - 65;
            } else if (c >= 'a' && c <= 'z') {
                digit[i] = c - 97 + 26;
            } else if (c >= '0' && c <= '9') {
                digit[i] = c - 48 + 52;
            } else if (c == '+') {
                digit[i] = 62;
            } else if (c == '/') {
                digit[i] = 63;
            }
        }

        byte[] b = new byte[len - 1];
        switch(len) {
            case 4:
                b[2] = (byte)((digit[2] & 3) << 6 | digit[3]);
            case 3:
                b[1] = (byte)((digit[1] & 15) << 4 | (digit[2] & 60) >>> 2);
            case 2:
                b[0] = (byte)(digit[0] << 2 | (digit[1] & 48) >>> 4);
            default:
                return b;
        }
    }
}
