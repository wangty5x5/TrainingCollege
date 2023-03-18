package util;

/**
 *
 */
public class discountUtil {

    public double getDiscount(String rank){
        if(rank=="S")
            return 0.8;
        else if(rank=="A")
            return 0.85;
        else if(rank=="B")
            return 0.9;
        else if(rank=="C")
            return 0.95;
        else if(rank=="D")
            return 0.98;
        else
            return 1.0;
    }
}
