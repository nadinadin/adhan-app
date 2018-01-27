package PrayerTimesCalculator
/*

import DecimalMath.fixHour
import java.util.*

// convert hours to day portions
fun dayPortion(times: Times<Double>): Times<Double> {
    return Times(
			fajr = times.fajr/24,
			imsak = times.imsak/24,
			sunrise = times.sunrise/24,
			sunset = times.sunset/24,
			maghrib = times.maghrib/24,
			isha = times.isha/24,
			dhuhr = times.dhuhr/24,
			asr = times.asr/24,
			midnight = times.midnight/24
	)
}


//---------------------- Time Zone Functions -----------------------


// get local time zone
fun getTimeZone(date: Date): Double {
	return TimeZone.getDefault().rawOffset/(60*60*1000.0)
}


// get daylight saving for a given date
fun getDstOffset(date: Date): Boolean {
    return TimeZone.getDefault().inDaylightTime(date)
}


// GMT offset for a given date
fun gmtOffset(date: Date): Double {
    return TimeZone.getDefault().getOffset(date.time)/(60*60*1000.0)
}

class Coordinates(val lat: Double, val lon: Double, val elv: Double?)

class PrayTimes(private val setting: Setting) {
	//----------------------- Public Functions ------------------------

	// return prayer times for a given date
	fun getTimes(date: Date, coords: Coordinates): Times<Double> {
		val lat = coords.lat;
		val lng = coords.lon;
		val elv = coords.elv ?: 0;
		val rawTZ = getTimeZone(date);
		val dst = getDstOffset(date);
		val actualTZ = gmtOffset(date);
		val jDate = this.julian(date) - lng/ (15* 24);

		return this.computeTimes(lng, lat, actualTZ, elv, jDate, date);
	}


	// convert float time to the given format (see timeFormats)
	fun getFormattedTime(timeRaw: Int?, today: Date?): Date? {
		if (timeRaw == null || today == null)
			return null;

		val time = fixHour(timeRaw + 0.5/60);  // add 0.5 minutes to round
		val hours = Math.floor(time);
		val minutes = Math.floor((time- hours)* 60);

		val copy = Date()
        copy.time = today.time
		copy.hours = hours.toInt()
		copy.minutes = minutes.toInt()
		copy.setSeconds(0)

		return copy
	}


	//---------------------- Calculation Functions -----------------------




	// convert Gregorian date to Julian day
	// Ref: Astronomical Algorithms by Jean Meeus
	fun julian(year_in: Int, month_in: Int, day: Int): Double {
        var the_year = year_in;
		var the_month = month_in;
		if (the_month <= 2) {
			the_year -= 1;
			the_month += 12;
		}
		val A = Math.floor(the_year/ 100.0);
		val B = 2- A+ Math.floor(A/ 4);

		val JD = Math.floor(365.25 * (the_year+ 4716))+ Math.floor(30.6001* (the_month+ 1))+ day+ B- 1524.5;
		return JD;
	}


	//---------------------- Compute Prayer Times -----------------------




	// compute prayer times
	fun computeTimes(lng: Double, lat: Double, timeZone: Int, elv: Double, jDate: Double, today: Date?) {
		// default times
		val times_initial = Times<Int>(
			imsak= 5, fajr= 5, sunrise= 6, dhuhr= 12,
			asr= 13, sunset= 18, maghrib= 18, isha= 18,
			midnight= 0
		);


		// main iterations
		const times_computed = repeat(1, this.setting.numIterations).reduce((acc, _) => TimesComputer.computePrayerTimes(acc, elv, lat, jDate, this.setting), times_initial)
		const times_adjusted = this.adjustTimes(times_computed, lng, timeZone);

		// add midnight time
		const midnight_close_bound: TimesGetter<number> = this.setting.midnight == "Jafari" ? fajr : sunrise
		const times_midnight = {...times_adjusted,
			midnight: times_adjusted.sunset + diffTime(times_adjusted.sunset, midnight_close_bound(times_adjusted))/ 2
		}

		return this.modifyFormats(this.tuneTimes(times_midnight), today);
	}


	// adjust times
	adjustTimes(times: TimesFloaty, lng: number, timeZone: number) {
		var params = this.setting;
		for (var i in times)
			times[i] += timeZone- lng/ 15;

		if (params.highLats)
			times = adjustHighLats(params.highLats, this.setting)(times);

		if (isMins(params.imsak))
			times.imsak = times.fajr- getAmount(params.imsak)/ 60;
		if (isMins(params.maghrib))
			times.maghrib = times.sunset + getAmount(params.maghrib)/ 60;
		if (isMins(params.isha))
			times.isha = times.maghrib + getAmount(params.isha)/ 60;
		times.dhuhr += getAmount(params.dhuhr)/ 60;

		return times;
	}


	// apply offsets to the times
	tuneTimes(times: TimesFloaty) {
		for (var i in times)
			times[i] += this.setting.offset[i]/ 60;
		return times;
	}


	// convert times to given time format
	modifyFormats(times: TimesFloaty, today: Date|null): TimesDaty {
		return {
			fajr: this.getFormattedTime(times.fajr, today),
			imsak: this.getFormattedTime(times.imsak, today),
			sunrise: this.getFormattedTime(times.sunrise, today),
			dhuhr: this.getFormattedTime(times.dhuhr, today),
			asr: this.getFormattedTime(times.asr, today),
			sunset: this.getFormattedTime(times.sunset, today),
			maghrib: this.getFormattedTime(times.maghrib, today),
			isha: this.getFormattedTime(times.isha, today),
			midnight: this.getFormattedTime(times.midnight, today),
		}
	}




}
*/
