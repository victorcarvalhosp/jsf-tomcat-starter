package br.com.libercode.core.util;

import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	public static String getDataFormatada(Date data){
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}
	
	public static String getDataHoraMinutoFormatada(Date data){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data);
	}

	public static String getDataHoraMinutoSegundoFormatada(Date data){
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
	}
	
	public static String getHoraMinutoFormatada(Date data){
		return new SimpleDateFormat("HH:mm").format(data);
	}
	
	public static boolean dataFinalMaiorInicial(Date dataInicial, Date dataFinal) {
		return dataFinal.before(dataInicial);
	}
	
	public static Date parseDateTime(String dateString) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(dateString);
	}
	
	public static Date parseDate(String dateString) throws ParseException {
		return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
	}
	
	public static Date parseDateEUAFormat(String dateString) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
	}
	
	public static LocalDate parseLocalDate(String dateString) throws ParseException {
		return new LocalDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
	}
	
	public static String getDataFormatadaPostgres(Date data){
		return new SimpleDateFormat("yyyy-MM-dd").format(data);
	}
	
	public static String getDataFormatadaApenasAno(Date data){
		return new SimpleDateFormat("yy").format(data);
	}
	
	public static Date primeiroDiaAnoAtual() {
		return new LocalDate(LocalDate.now().getYear(), 1, 1).toDate();
	}
	
	public static Date ultimoDiaAnoAtual() {
		return new LocalDate(LocalDate.now().getYear(), 12, 31).toDate();
	}

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
}
