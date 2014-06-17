package my.sample.project1;

import java.util.Date;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class Utils {
	
    public Date parseDate(String dateToParse) {
		Date date = null;
		try {
			DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss");
			date = fmt.parseDateTime(dateToParse).toDate();
		} catch (Exception e) {
		}
		return date;
	}
}
