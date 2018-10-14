package sistemaCine.utils;

import java.sql.Date;
import java.util.Calendar;

public final class DateSinHora {
	public static final Date getDateSinHora(Date fecha) {
		Calendar date = Calendar.getInstance();
		date.setTime(fecha);
		date.set(Calendar.HOUR_OF_DAY,0);
		date.set(Calendar.MINUTE,0);
		date.set(Calendar.SECOND,0);
		date.set(Calendar.MILLISECOND,0);
		return (Date) date.getTime();
	}
}
