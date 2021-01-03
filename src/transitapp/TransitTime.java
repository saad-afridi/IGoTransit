package transitapp;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class TransitTime {
	private LocalDateTime time;

	/**
	 * Initializes the current Time object.
	 * 
	 * Precondition: currTime must be in the format yyyy:MM:dd:HH:mm.
	 * 
	 * @param time of this time.
	 */
	public TransitTime() {
		// 2017:01:17:09:30
		this.time = LocalDateTime.MIN;
	}

	/**
	 * Initializes the current Time object.
	 * 
	 * Precondition: currTime must be in the format yyyy:MM:dd:HH:mm.
	 * 
	 * @param time of this time.
	 */
	public TransitTime(String currTime) {
		// 2017:01:17:09:30
		parseTime(currTime);
	}

	public boolean isAfter(TransitTime t2) {
		return this.time.isAfter(t2.time);
	}

	/**
	 * Sets the current time.
	 * 
	 * Precondition: timeStr must be in the format yyyy:MM:dd:HH:mm
	 */
	public void setTime(String timeStr) {
		parseTime(timeStr);
	}

	/**
	 * Adding the given amount of minutes to the current time.
	 * 
	 * @param minutes to be added.
	 */
	public void addMinutes(long minutes) {
		this.time = this.time.plusMinutes(minutes);
	}

	/**
	 * Adding the given amount of hours to the current time.
	 * 
	 * @param minutes to be added.
	 */
	public void addHours(long hours) {
		this.time = this.time.plusHours(hours);
	}

	/**
	 * Stores the combination of the time in t1 and t2 in t1 excluding the years of
	 * t1 and t2.
	 * 
	 * @param t1 representation of the first time.
	 * @param t2 representation of the second time.
	 */
	public void addTimes(TransitTime timeToAdd) {
		this.time = this.time.plusMonths(timeToAdd.getTime().getMonthValue());
		this.time = this.time.plusDays(timeToAdd.getTime().getDayOfMonth());
		this.time = this.time.plusHours(timeToAdd.getTime().getHour());
		this.time = this.time.plusMinutes(timeToAdd.getTime().getMinute());
	}

	/**
	 * Returns the LocalTime object of this object.
	 * 
	 * @return LocalTime time object.
	 */
	public LocalDateTime getTime() {
		return this.time;
	}

	/**
	 * Sets LocalTime object of this TransitTime object.
	 * 
	 * @param givenTime to be set.
	 */
	public void setTime(LocalDateTime givenTime) {
		this.time = givenTime;
	}

	/**
	 * Returns a clone of this TransitTime object.
	 * 
	 * @return a clone of this TransitTime object.
	 */
	public TransitTime clone() {
		TransitTime transitTime = new TransitTime();
		LocalDateTime timeClone = this.getTime();		
		transitTime.setTime(LocalDateTime.of(timeClone.getYear(), timeClone.getMonth(), timeClone.getDayOfMonth(),
				timeClone.getHour(), timeClone.getMinute()));
		return transitTime;
	}

	/**
	 * Returns a formatted representation of this LocalDate object in the form
	 * Month, Day, Year, Hour:Minute AM/PM.
	 * 
	 * @return a formatted representation of this LocalDate object in the form
	 *         Month, Day, Year, Hour:Minute AM/PM.
	 */
	public String getFormattedTime() {
		StringBuilder formattedTime = new StringBuilder();

		formattedTime.append(this.getTime().getMonth().toString() + " " + this.getTime().getDayOfMonth() + ", "
				+ this.getTime().getYear());

		// If the minute is a single digit
		String minutes;
		if (this.getTime().getMinute() < 10) {
			minutes = "0" + this.getTime().getMinute();
		} else {
			minutes = String.valueOf(this.getTime().getMinute());
		}

		// Distinguishing whether it is AM or PM.
		if (getTime().getHour() > 1 && getTime().getHour() < 12) {
			formattedTime.append(", " + this.getTime().getHour() + ":" + minutes + " am");
		} else {
			formattedTime.append(", " + (this.getTime().getHour() - 12) + ":" + minutes + " pm");
		}

		return formattedTime.toString();

	}

	@Override
	public String toString() {
		return this.time.toString().replace("T", " ") + ":00";
	}

	/**
	 * Returns the difference between the time of t1 and t2 in the form of {a, b, c,
	 * d, e} where a = difference between years of t1 and t2 where b = difference
	 * between months of t1 and t2 where c = difference between days of t1 and t2.
	 * where d = difference between hours of t1 and t2. where e = difference between
	 * minutes of t1 and t2.
	 * 
	 * Precondition: t1 takes place before t2.
	 * 
	 * @param t1 representing the first time object.
	 * @param t2 representing the second time object.
	 * @return the difference between the time of t1 and t2.
	 */
	public static int getDifference(TransitTime t1, TransitTime t2) {
		return (int) Math.abs(t1.getTime().until(t2.getTime(), ChronoUnit.MINUTES));
	}

	/**
	 * Returns True iff the two dates are exactly the same regardless of the hours,
	 * minutes and second.
	 * 
	 * e.g., 2020-07-14 and 2020-07-14 are the same since 2020 - 2020 = 0 and 07 -
	 * 07 = 0 and 14 - 14 = 0.
	 * 
	 * @param t1
	 * @param t2
	 * @return whether the two dates are exactly the same regardless of the hours,
	 *         minutes and second.
	 */
	public static boolean copmpareDates(TransitTime t1, TransitTime t2) {
		if (t1.getTime().until(t2.getTime(), ChronoUnit.YEARS) == 0
				&& t1.getTime().until(t2.getTime(), ChronoUnit.MONTHS) == 0
				&& t1.getTime().until(t2.getTime(), ChronoUnit.DAYS) == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns true iff the given amount of hour(s) have passed starting between t1
	 * and t2.
	 * 
	 * @param t1    representing the first time object.
	 * @param t2    representing the second time object.
	 * @param hours an integer representing the amount of hours
	 * @return whether the given amount of hour(s) have passed starting between t1
	 *         and t2.
	 */
	public static Boolean hasHourPassed(TransitTime t1, TransitTime t2, int hours) {
		int timeDifference = TransitTime.getDifference(t1, t2);
		return timeDifference >= hours * 60;
	}

	/**
	 * Creates a LocalDateTime object out of the given time in the form of a string.
	 * 
	 * @param timeStr that is to be converted into a LocalDateTime.
	 */
	private void parseTime(String timeStr) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy:MM:dd:HH:mm");
		this.time = LocalDateTime.parse(timeStr, timeFormatter);
	}

	public String revert() {
		String year = new Integer(this.getTime().getYear()).toString();
		String month, day, hour, minute;
		
		Integer monthInt = new Integer(this.getTime().getMonthValue());
		if (monthInt < 10) {
			month = "0" + monthInt.toString();
		}
		else {
			month = new Integer(monthInt).toString();

		}
		
		Integer dayInt = new Integer(this.getTime().getDayOfMonth());
		if (dayInt < 10) {
			day = "0" + dayInt.toString();
		}
		else {
			day = dayInt.toString();
		}
		
		Integer hourInt = new Integer(this.getTime().getHour());
		if (hourInt < 10) {
			hour = "0" + hourInt.toString();
		}
		else {
			hour = hourInt.toString();
		}
		
		Integer minInt = new Integer(this.getTime().getMinute());
		if (minInt < 10) {
			minute = "0" + minInt.toString();
		}
		else {
			minute = minInt.toString();
		}
		return year + ":" + month + ":" + day + ":" + hour + ":" + minute;
	}
	
	/**
	 * Example of how this class is used.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TransitTime currTime = new TransitTime("2020:04:17:08:31");

		System.out.println(currTime.toString()); // 2020-04-17 08:31:00
		currTime.addMinutes(45);
		System.out.println(currTime.toString()); // 2020-04-17 09:16:00
		currTime.addHours(4);
		System.out.println(currTime.toString()); // 2020-04-17 13:16:00

		// Using getDifference and hasHourPassed
		TransitTime t1 = new TransitTime("2020:04:17:04:05");
		TransitTime t2 = new TransitTime("2020:04:17:09:15");

		System.out.println("===============");
		int diffs = TransitTime.getDifference(t1, t2);
		System.out.println("Minutes: " + diffs % 60);
		System.out.println("Hours: " + (int) diffs / 60);
		// 5 hours and 10 minute difference
		System.out.println("===============");

		System.out.println(TransitTime.hasHourPassed(t1, t2, 2)); // true
		System.out.println(TransitTime.hasHourPassed(t1, t2, 8)); // false

		// adding two dates
		TransitTime t3 = new TransitTime("2020:04:17:04:05");
		TransitTime t4 = new TransitTime("2020:04:17:09:15");
		t3.addTimes(t4);
		System.out.println(t3);

	}
}
