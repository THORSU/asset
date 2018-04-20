package asset.utils;

import java.util.UUID;

/**
 * Created by weixin on 17-8-3.
 */
public class RandomAccessUtil {
    public static  String getRandom(String type){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return type+uuid;
    }
}
