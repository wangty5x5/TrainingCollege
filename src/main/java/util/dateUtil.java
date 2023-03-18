package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date类型格式转化工具类;
 */
public class dateUtil {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public String changeStr(String str){
        Date date = new Date();
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format.format(date);
    }

    public String dateToStr(Date date){
        return format.format(date);
    }

    /**
     * d1<d2
     * @param d1
     * @param d2
     * @return
     */
    public int distance(Date d1, Date d2){
        return (int) (d2.getTime()-d1.getTime());
    }
}
