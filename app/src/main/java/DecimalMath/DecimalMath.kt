package DecimalMath;

private fun dtr (d: Double): Double { return (d * Math.PI) / 180.0; }
private fun rtd (r: Double): Double { return (r * 180.0) / Math.PI;}
private fun fix (num: Double, mod: Double): Double {
    val a: Double = num - mod * (Math.floor(num/mod));

    return if (a < 0) a + mod else a
}

fun sin(d: Double): Double { return Math.sin(dtr(d));}
fun cos(d: Double): Double { return Math.cos(dtr(d));}
fun tan(d: Double): Double { return Math.tan(dtr(d));}

fun asin(d: Double): Double { return rtd(Math.asin(d));}
fun acos(d: Double): Double { return rtd(Math.acos(d));}
fun atan(d: Double): Double { return rtd(Math.atan(d));}

fun acot(x: Double): Double { return rtd(Math.atan(1/x)); }
fun atan2(y: Double, x: Double): Double { return rtd(Math.atan2(y, x))}

fun fixAngle (a: Double): Double { return fix(a, 360.0); }
fun fixHour  (a: Double): Double { return fix(a, 24.0 ); }
