package util;

/**
 *
 */
public class vipUtil {

    public String getRank(int credit){
        if(credit<=99)
            return "E";
        else if(credit<=299)
            return "D";
        else if(credit<=799)
            return "C";
        else if(credit<=1499)
            return "B";
        else if(credit<=2999)
            return "A";
        else
            return "S";
    }
}
