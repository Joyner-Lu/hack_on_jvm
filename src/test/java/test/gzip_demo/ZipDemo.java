package test.gzip_demo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipDemo {


    public static void main(String[] args) throws Exception {
        //1.获取数据
        ZipDemo demo = new ZipDemo();
        String data = demo.mockQueryDataFromDB();
        byte[] dataBytes = data.getBytes("UTF-8");

        //2.压缩
        ZipDemo gzipDemo = new ZipDemo();
        byte[] compressResult = gzipDemo.compress(dataBytes);

        //3.解压缩
        byte[] deCompressResult = gzipDemo.deCompress(compressResult);
        System.out.println("解压结果：" + new String(deCompressResult));
    }

    /***
     * 解压Zip
     *
     * @param data
     * @return
     */
    public byte[] deCompress(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ZipInputStream zip = new ZipInputStream(bis);
            while (zip.getNextEntry() != null) {
                byte[] buf = new byte[1024];
                int num = -1;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((num = zip.read(buf, 0, buf.length)) != -1) {
                    baos.write(buf, 0, num);
                }
                b = baos.toByteArray();
                baos.flush();
                baos.close();
            }
            zip.close();
            bis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public byte[] compress(byte[] data) throws Exception {
        System.out.println("开始压缩");

        System.out.println("压缩前大小：" + data.length);

        //2.压缩到
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        ZipEntry zipEntry = new ZipEntry("root");
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(data);

        //3.获取压缩数据


        zipOutputStream.flush();
        zipOutputStream.finish();
        byteArrayOutputStream.flush();
        zipOutputStream.close();
        byte[] compressResult = byteArrayOutputStream.toByteArray();
        System.out.println("压缩后大小：" + compressResult.length);
        return compressResult;
    }


    public String mockQueryDataFromDB() {
        //从resultset里面流式里面获取到数据
        String result = "{\n" +
                "  \"datas\" : [\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"},\n" +
                "    {\"je\":100,\"kprq\":\"2018-8-3\",\"zt\":\"03\",\"is_xf\":\"Y\"}\n" +
                "  ]\n" +
                "}";

        return result;
    }

}
