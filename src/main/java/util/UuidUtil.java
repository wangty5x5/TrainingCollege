package util;

import java.util.Random;
import java.util.UUID;

/**
 *
 */
public class UuidUtil {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getCode(){
        return String.valueOf(new Random().nextInt(8999)+1000);
    }
}
