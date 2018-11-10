package sistemaCine.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

public final class DateUtils {

	public static final Date getDateSinHora(Date fecha) {
		Calendar date = Calendar.getInstance();
		date.setTime(fecha);
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return new Date(date.getTimeInMillis());
	}

	public static final Date getFechaSql(java.util.Date fecha) {
		Calendar date = Calendar.getInstance();
		date.setTime(fecha);
		return new Date(date.getTimeInMillis());
	}

	public static final Date getDateConHora(int anio, int mes, int dia, int hora, int min) {
		Calendar date = Calendar.getInstance();
		date.setTime(new java.util.Date());
		date.set(anio, mes - 1, dia, hora, min);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		return new Date(date.getTimeInMillis());
	}

	public static final int get(Date fecha, int calendarInt) {
		Calendar date = Calendar.getInstance();
		date.setTime(fecha);
		if (Calendar.MONTH == calendarInt) {
			return date.get(calendarInt) + 1;
		}
		return date.get(calendarInt);
	}

//	public static final Date getDateSql(java.util.Date fecha) {		
//		return (Date) fecha;
//	}
	public static String getHoraString(Date fechaYHora) {
		Calendar date = Calendar.getInstance();
		date.setTime(fechaYHora);
		return ((date.get(Calendar.HOUR_OF_DAY) < 10) ? "0" + date.get(Calendar.HOUR_OF_DAY)
				: date.get(Calendar.HOUR_OF_DAY)) + ":"
				+ ((date.get(Calendar.MINUTE) < 10) ? "0" + date.get(Calendar.MINUTE) : date.get(Calendar.MINUTE));
	}

}
