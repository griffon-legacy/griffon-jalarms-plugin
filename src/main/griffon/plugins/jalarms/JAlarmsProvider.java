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

package griffon.plugins.jalarms;

import java.util.Map;
import groovy.lang.Closure;
import griffon.util.CallableWithArgs;

/**
 * @author Andres Almiray
 */
public interface JAlarmsProvider {
    void sendAlarm(String message);
    
    void sendAlarm(String message, boolean force);

    void sendAlarm(String message, String source);

    void sendAlarm(String message, String source, boolean force);
}
