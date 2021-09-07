package com.yody.common.utility;

import static java.math.BigDecimal.TEN;
import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public final class Numbers {

    public static boolean isNumeric(String v) {
        if (v == null) {
            return false;
        }
        return v.matches(("-?\\d+(\\.\\d+)?"));
    }

    public static boolean isPositiveInt(String v) {
        try {
            return Integer.parseInt(v) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean compare(String s1, String s2){
        if(!isNumeric(s1) || !isNumeric(s2)){
            return false;
        }
        BigDecimal v1 = new BigDecimal(s1);
        BigDecimal v2 = new BigDecimal(s2);
        return v1.compareTo(v2) > 0;
    }




    /**
     * BigDecimal
     */
    public static BigDecimal null2zero(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }

    public static boolean isZero(final BigDecimal v) {
        return v == null ? false : v.signum() == 0;
    }

    public static boolean isZeroOrNull(BigDecimal v) {
        return v == null ? true : v.signum() == 0;
    }

    public static boolean isPositive(BigDecimal v) {
        return v == null ? false : v.compareTo(ZERO) > 0;
    }

    public static boolean isNegative(BigDecimal v) {
        return v == null ? false : v.compareTo(ZERO) < 0;
    }

    public static boolean isZeroOrPositive(BigDecimal v) {
        return v == null ? false : v.compareTo(ZERO) >= 0;
    }

    public static boolean isZeroOrNegative(BigDecimal v) {
        return v == null ? false : v.compareTo(ZERO) <= 0;
    }

    public static boolean isNullOrPositive(BigDecimal r) {
        return (r == null ? true : r.compareTo(ZERO) > 0);
    }

    public static boolean isNullOrNegative(BigDecimal r) {
        return (r == null ? true : r.compareTo(ZERO) < 0);
    }

    public static boolean eq(BigDecimal r1, BigDecimal r2) {
        return r1.compareTo(r2) == 0;
    }

    public static boolean gt(BigDecimal r1, BigDecimal r2) {
        return r1.compareTo(r2) > 0;
    }

    public static boolean get(BigDecimal r1, BigDecimal r2) {
        return r1.compareTo(r2) >= 0;
    }

    public static boolean lt(BigDecimal r1, BigDecimal r2) {
        return r1.compareTo(r2) < 0;
    }

    public static boolean let(BigDecimal r1, BigDecimal r2) {
        return r1.compareTo(r2) <= 0;
    }

    public static BigDecimal add(BigDecimal v1, String v2) throws Exception {
        if (!isNumeric(v2)) {
            throw new Exception("Required Numeric type");
        }
        return add(v1, new BigDecimal(v2));
    }

    public static final int scale(BigDecimal v, boolean strip) {
        if (strip) {
            v = v.stripTrailingZeros();
        }
        return v.scale();
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2, BigDecimal v3) {
        return add(add(v1, v2), v3);
    }

    public static BigDecimal round(BigDecimal v, byte scale, RoundingMode mode) {
        return v == null ? null : v.setScale(scale, mode);
    }


    public static BigDecimal div(BigDecimal v, byte scale, RoundingMode mode) {
        return v == null ? null : v.divide(TEN.pow(scale), scale, mode);
    }

    public static final BigDecimal min(final BigDecimal v1, final BigDecimal v2) {
        if (v1 == null && v2 == null) {
            return null;
        }
        if (v2 == null) {
            return v1;
        }
        return (v1 == null ? v2 : v1.min(v2));
    }

    public static final BigDecimal max(final BigDecimal v1, final BigDecimal v2) {
        if (v1 == null && v2 == null) {
            return null;
        }
        if (v2 == null) {
            return v1;
        }
        return (v1 == null ? v2 : v1.max(v2));
    }

    public static final BigDecimal add(final BigDecimal v1, final BigDecimal v2) {
        if (v1 == null && v2 == null) {
            return null;
        }
        if (v2 == null) {
            return v1;
        }
        return (v1 == null ? v2 : v1.add(v2));
    }

    public static final BigDecimal sub(final BigDecimal a, final BigDecimal b) {
        if (a == null && b == null) {
            return null;
        }
        if (b == null) {
            return a;
        }
        return (a == null ? null : a.subtract(b));
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale, RoundingMode mode) {
        if (v1 == null || v2 == null) {
            return null;
        }
        return ((Numbers.isZero(v2)) ? null : v1.divide(v2, scale, mode));
    }

    public static boolean compare(Long n1, Long n2) {
        if(n1 == null && n2 == null) {
            return  true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    public static boolean compare(Integer n1, Integer n2) {
        if(n1 == null && n2 == null) {
            return  true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        return n1.equals(n2);
    }

    public static boolean compare(BigDecimal n1, BigDecimal n2) {
        if(n1 == null && n2 == null) {
            return  true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        return n1.compareTo(n2) == 0;
    }

    public static boolean compare(Float n1, Float n2) {
        if(n1 == null && n2 == null) {
            return  true;
        }
        if(n1 == null || n2 == null) {
            return false;
        }
        return n1.compareTo(n2) == 0;
    }

    public static BigDecimal stringToDecimal(String s) {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        if (NumberUtils.isCreatable(s)) {
            return new BigDecimal(s).setScale(2, RoundingMode.HALF_UP);
        }
        try {
            return new BigDecimal(new BigInteger(Base64.getDecoder().decode(s)), 2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
