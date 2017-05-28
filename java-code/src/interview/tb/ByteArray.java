package interview.tb;

/**
 * Created by yechen on 5/24/17.
 */
public class ByteArray {

    public static void main(String[] argv) {

        String example = "This is an example";
        byte[] bytes = example.getBytes();

        System.out.println("Text : " + example);
        System.out.println("Text [Byte Format] : " + bytes);
        System.out.println("Text [Byte Format] : " + bytes.toString());

        String s = new String(bytes);
        System.out.println("Text Decryted : " + s);

        for (Byte b : bytes) {

        }
        //7E0 idx = 2 return 0; charArray = F070 idx = 3, return 2; charArray = 0EEEE0, idx = 5, return 4

    }
}
