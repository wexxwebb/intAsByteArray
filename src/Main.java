import java.io.*;

public class Main {

    public static byte[] intAsByteArray(int integer) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) ((integer << 24) >>> 24);
        bytes[2] = (byte) ((integer << 16) >>> 24);
        bytes[1] = (byte) ((integer << 8) >>> 24);
        bytes[0] = (byte) (integer >>> 24);
        return bytes;
    }

    public static int byteArrayAsInt(byte[] bytes) {

        return ((bytes[0] & 0xFF) << 24) + ((bytes[1] & 0xFF) << 16) + ((bytes[2] & 0xFF) << 8) + (bytes[3] & 0xFF);
    }

    public static void main(String[] args) {


        int integer = 100_000;
        System.out.println("Write and read integer test value = " + integer);
        testWriteAndReadToFileInt(integer);


    }

    public static void testWriteAndReadToFileInt(int integer) {
        File file = new File("intarraytest");
        try {
            FileOutputStream writer = new FileOutputStream(file);
            //writer.write(ByteBuffer.allocate(4).putInt(integer).array());
            writer.write(intAsByteArray(integer));
            writer.flush();
            writer.close();
            FileInputStream reader = new FileInputStream(file);
            byte[] bytes = new byte[4];
            reader.read(bytes);
            System.out.println(byteArrayAsInt(bytes));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
