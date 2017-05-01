import java.io.IOException;
import java.util.StringTokenizer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.*;
import java.util.Calendar;



class test{
    
    public static void main(String[] args)
    {
    	try{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			Date date = format.parse("2014-04-13T18:49:34.026Z");
            Calendar sessionTime = Calendar.getInstance();
            sessionTime.setTime(date);
            // String temp = Integer.toString(sessionTime.get(Calendar.MONTH));        
            // System.out.println("month is : " + temp);
            // Date date = format.parse(tokens[1]);
            // String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);            
            System.out.println((sessionTime.get(Calendar.DAY_OF_WEEK)));
        }
        catch(Exception e)
        {

        }
    }
}


