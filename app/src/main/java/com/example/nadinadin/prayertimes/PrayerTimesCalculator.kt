package com.example.nadinadin.prayertimes

import java.util.*

class Times<A> (val imsak: A, val fajr: A, val sunrise: A, val dhuhr: A, val asr: A, val sunset: A, val maghrib: A, val isha: A)

class Settings (val latitude: Double, val longitude: Double, val elevation: Double)

private fun dateWithHHMM(date: Date, hh: Int, mm: Int): Date {
    val out = Date(date.time)

    out.hours = hh
    out.minutes = mm
    out.seconds = 0

    return out
}

fun calculaterPrayerTimes(date: Date, settings: Settings): Times<Date> {
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

sealed class Athan<out T> {
    class Fajr<T>(val date: T): Athan<T>()
    class Dhuhr<T>(val date: T): Athan<T>()
    class Asr<T>(val date: T): Athan<T>()
    class Maghrib<T>(val date: T): Athan<T>()
    class Isha<T>(val date: T): Athan<T>()
}

fun getNextAthan(date: Date, times: Times<Date>): Athan<Date> {
    return Athan.Maghrib(times.maghrib)
}
