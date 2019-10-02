package cn.itcast;

import cn.itcast.POJO.User;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.junit.Test;

import java.sql.PreparedStatement;

public class ImageTest {

//    @Test
//    public void uploadTest(){
//        //构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(Zone.zone0());
//        //...其他参数参考类注释
//        UploadManager uploadManager = new UploadManager(cfg);
//        //...生成上传凭证，然后准备上传
//        String accessKey = "0hbuKqPV_UHiRy-Qt7Lf5qmf7IcViWm-GkeagGrP";
//        String secretKey = "LGWtNZfj3QIwo5TnjDfFlnpXbBmzPhCodCeioTw6";
//        String bucket = "health";
//        //如果是Windows情况下，格式是 D:\\qiniu\\test.png
//        String localFilePath = "D:\\tupian\\14.jpg";
//        //默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = "14.jpg";
//        Auth auth = Auth.create(accessKey, secretKey);
//        String upToken = auth.uploadToken(bucket);
//        try {
//            Response response = uploadManager.put(localFilePath, key, upToken);
//            //解析上传成功的结果
//            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//            System.out.println(putRet.key);
//            System.out.println(putRet.hash);
//        } catch (
//                QiniuException ex) {
//            Response r = ex.response;
//            System.err.println(r.toString());
//            try {
//                System.err.println(r.bodyString());
//            } catch (QiniuException ex2) {
//                //ignore
//            }
//        }
//    }
//    @Test
//    public void deleteImageTest(){
//        //构造一个带指定Zone对象的配置类
//        Configuration cfg = new Configuration(Zone.zone0());
//        //...其他参数参考类注释
//        String accessKey = "0hbuKqPV_UHiRy-Qt7Lf5qmf7IcViWm-GkeagGrP";
//        String secretKey = "LGWtNZfj3QIwo5TnjDfFlnpXbBmzPhCodCeioTw6";
//        String bucket = "health";
//        String key = "14.jpg";
//        Auth auth = Auth.create(accessKey, secretKey);
//        BucketManager bucketManager = new BucketManager(auth, cfg);
//        try {
//            bucketManager.delete(bucket, key);
//        } catch (QiniuException ex) {
//            //如果遇到异常，说明删除失败
//            System.err.println(ex.code());
//            System.err.println(ex.response.toString());
//        }
//    }
//
//    @Test
//    public void imgTest(){
//        String fileName = "a.jpg";
//        fileName = fileName.substring(fileName.lastIndexOf("."));
//        System.out.println(fileName);
//    }




}
