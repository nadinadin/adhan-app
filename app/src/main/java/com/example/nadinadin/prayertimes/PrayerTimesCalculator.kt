package com.example.nadinadin.prayertimes

import java.util.*

class Times<A> (val imsak: A,
                val fajr: A,
                val sunrise: A,
                val dhuhr: A,
                val asr: A,
                val sunset: A,
                val maghrib: A,
                val isha: A)

class Settings (val latitude: Double, val longitude: Double, val elevation: Double)

private fun dateWithHHMM(date: Date, hh: Int, mm: Int): Date {
    val out = Date(date.time)

    out.hours = hh
    out.minutes = mm
    out.seconds = 0

    return out
}

fun calculatePrayerTimes(date: Date, settings: Settings): Times<Date> {
    return Times<Date>(
            imsak = dateWithHHMM(date, 3, 55),
            fajr = dateWithHHMM(date, 4, 0),
            sunrise = dateWithHHMM(date, 6, 0),
            dhuhr = dateWithHHMM(date, 12, 0),
            asr = dateWithHHMM(date, 15, 0),
            sunset = dateWithHHMM(date, 18, 0),
            maghrib = dateWithHHMM(date, 18, 0),
            isha = dateWithHHMM(date, 20, 0)
    )
}

sealed class Adhan<out T> {
    class Fajr<T>(val date: T): Adhan<T>()
    class Dhuhr<T>(val date: T): Adhan<T>()
    class Asr<T>(val date: T): Adhan<T>()
    class Maghrib<T>(val date: T): Adhan<T>()
    class Isha<T>(val date: T): Adhan<T>()
}

fun getNextAdhan(date: Date, times: Times<Date>): Adhan<Date> {
    return Adhan.Maghrib(times.maghrib)
}

fun getAdhanName(adhan: Adhan<Date>): String {
    return when (adhan) {
        is Adhan.Fajr -> "Subuh"
        is Adhan.Dhuhr -> "Lohor"
        is Adhan.Asr -> "Ashar"
        is Adhan.Maghrib -> "Maghrib"
        is Adhan.Isha -> "Isya'"
    }
}
