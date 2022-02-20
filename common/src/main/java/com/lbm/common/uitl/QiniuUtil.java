package com.lbm.common.uitl;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class QiniuUtil {
    private static final String accessKey="Vdsp6CU6xnts94aMJkAxxAMrh8Vx3Ymxvi6FpDYg";
    private static final String secretKey="Sbopp0PpVdgTxEpQefIPd3a3M16f2RFiWo-OlXwa";
    private static final String url="http://cdn.libomeng.cn/";
    private static final String bucket="lbmcc-beimei";
    public boolean upload(MultipartFile file,String key) throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.autoRegion());
//...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
//...生成上传凭证，然后准备上传
//        String accessKey = "your access key";
//        String secretKey = "your secret key";
//        String bucket = "your bucket name";

//默认不指定key的情况下，以文件内容的hash值作为文件名
//        String key = null;

        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return true;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());

                try {

                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (UnsupportedEncodingException ex) {
            //ignore
        }
        return false;
    }
}
