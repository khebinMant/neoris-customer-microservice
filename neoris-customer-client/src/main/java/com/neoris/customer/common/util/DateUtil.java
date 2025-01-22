package com.neoris.customer.common.util;

import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * DateUtil.
 *
 * @author Pge on 17/03/2022
 * @version 1.0
 * @since 1.0.0
 */
public class DateUtil {

    /**
     * Constructor.
     */
    private DateUtil() {
    }

    /**
     * Get current date.
     *
     * @author pge on 14/04/2023
     * @return Date
     */
    public static Date currentDate() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get current date added minutes.
     *
     * @author pge on 14/04/2023
     * @return Date
     */
    public static Date addMinutes(Date date, Long minutes){
        return DateUtils.addMinutes(date, minutes.intValue());
    }

    /**
     * Get time in minutes between two dates.
     *
     * @author pge on 17/04/2023
     * @return Date
     */
    public static Long getMinutesBetweenDates(Date endDate, Date startDate){
        long duration = endDate.getTime() - startDate.getTime();
        return  TimeUnit.MILLISECONDS.toMinutes(duration);
    }
}
