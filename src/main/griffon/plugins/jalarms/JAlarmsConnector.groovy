/*
 * griffon-jalarms: JAlarms Griffon plugin
 * Copyright 2010 and beyond, Andres Almiray
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * smartclient.com/license.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

package griffon.plugins.jalarms

import com.solab.alarms.AlarmSender
import griffon.spring.ApplicationContextHolder

/**
 * @author Andres Almiray
 */
@Singleton
class JAlarmsConnector implements JAlarmsProvider { 
    void sendAlarm(String message, boolean force = false) {
        force? alarmSender().sendAlarmAlways(message) : alarmSender().sendAlarm(message)
    }

    void sendAlarm(String message, String source, boolean force = false) {
        force? alarmSender().sendAlarmAlways(message, source) : alarmSender().sendAlarm(message, source)
    }

    private AlarmSender alarmSender() {
        ApplicationContextHolder.applicationContext.alarmer
    }
}
