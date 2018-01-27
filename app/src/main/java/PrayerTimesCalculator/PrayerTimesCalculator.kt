package PrayerTimesCalculator;

import DecimalMath.*

/*

class Setting(
    val maghrib: Mins,
    val isha: MinsOrDegrees,
    val fajr: MinsOrDegrees,
    val imsak: MinsOrDegrees,
    val dhuhr: Mins,
    val numIterations: Int,
    val asr: AsrParam,
    val offset: Times<Double>
)


class SunPositions(val declination: Double, val equation: Double)
class Degs(val deg: Double) {
    companion object {
        val zero: Degs = Degs(0.0)
    }
}
class Mins(val mins: Int)

class MinsOrDegrees {
    var degs: Degs? = null
        get

    constructor(degs: Degs) {
        this.degs = degs
    }

    constructor(degs: Mins) {

    }

}

class Direction private constructor(private val isAnticlockwise: Boolean) {
    companion object {
        fun clockwise(): Direction {return Direction(false)}
        fun anticlockwise(): Direction {return Direction(true)}
    }

    fun normaliseAngle(theta: Double): Double {
        return if (isAnticlockwise) -theta else theta
    }
}

class AsrParam private constructor(val shadowLength: Int) {
    companion object {
        fun standard(): AsrParam {return AsrParam(1)}
        fun hanafi(): AsrParam {return AsrParam(2)}
    }
}


class PrayerTimesCalculator {
    // compute mid-day time
    private fun midDay(time: Double, jDate: Double): Double {
        val eqt = sunPosition(jDate + time).equation;
        val noon = fixHour(12.0 - eqt);
        return noon;
    }


    // compute declination angle of sun and equation of time
    // Ref: http://aa.usno.navy.mil/faq/docs/SunApprox.php
    private fun sunPosition(jd: Double): SunPositions {
        val D = jd - 2451545.0;
        val g = fixAngle(357.529 + 0.98560028* D);
        val q = fixAngle(280.459 + 0.98564736* D);
        val L = fixAngle(q + 1.915 * sin(g) + 0.020 * sin(2*g));

        val e = 23.439 - 0.00000036 * D;

        val RA = atan2(cos(e) * sin(L), cos(L)) / 15;
        val eqt = q/15 - fixHour(RA);
        val decl = asin(sin(e) * sin(L));

        return SunPositions(decl, eqt);
    }

    // compute the time at which sun reaches a specific angle below horizon
    private fun sunAngleTime(angle: Degs, time: Double, jDate: Double, lat: Double, direction: Direction): Double {
        val decl = sunPosition(jDate+ time).declination;
        val noon = midDay(time, jDate);
        val t = 1/15 * acos((-sin(angle.deg) - sin(decl) * sin(lat))/
                (cos(decl) * cos(lat)));
        return noon + direction.normaliseAngle (t);
    }


    // compute asr time
    private fun asrTime(factor: AsrParam, time: Double, lat: Double, jDate: Double): Double {
        val decl = sunPosition(jDate + time).declination;
        val angle = -acot(factor.shadowLength + tan(Math.abs(lat- decl)));
        return sunAngleTime(Degs(angle), time, jDate, lat, Direction.clockwise());
    }


    // return sun angle for sunset/sunrise
    private fun riseSetAngle(elv: Double): Degs {
        //var earthRad = 6371009; // in meters
        //var angle = DMath.arccos(earthRad/(earthRad+ elv));
        val angle = 0.0347 * Math.sqrt(elv); // an approximation
        return Degs(0.833 + angle);
    }

    // compute prayer times at given julian date
    fun computePrayerTimes (times_day: Times<Double>, elv: Double, lat: Double, jDate: Double, params: Setting): Times<Double> {
        val times = PrayTimes.dayPortion(times_day);

        val imsak   = sunAngleTime(params.imsak.degs ?: Degs.zero, times.imsak, jDate, lat, Direction.anticlockwise());
        val fajr    = sunAngleTime(params.fajr.degs ?: Degs.zero, times.fajr, jDate, lat, Direction.anticlockwise());
        val sunrise = sunAngleTime(riseSetAngle(elv), times.sunrise, jDate, lat, Direction.anticlockwise());
        val dhuhr   = midDay(times.dhuhr, jDate);
        val asr     = asrTime(params.asr, times.asr, lat, jDate)
        val sunset  = sunAngleTime(riseSetAngle(elv), times.sunset, jDate, lat, Direction.clockwise());
        val maghrib = sunAngleTime(Degs.zero, times.maghrib, jDate, lat, Direction.clockwise());
        val isha    = sunAngleTime(params.isha.degs ?: Degs.zero, times.isha, jDate, lat, Direction.clockwise());

        return Times(
            fajr, imsak, sunrise, sunset, maghrib, isha, dhuhr, asr, 0.0
        )
    }
}
         */