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

import griffon.util.CallableWithArgs
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author Andres Almiray
 */
final class JAlarmsEnhancer {
    private static final Logger LOG = LoggerFactory.getLogger(JAlarmsEnhancer)

    private JAlarmsEnhancer() {}
    
    static void enhance(MetaClass mc, JAlarmsProvider provider = JAlarmsConnector.instance) {
        if(LOG.debugEnabled) LOG.debug("Enhancing $mc with $provider")
        mc.sendAlarm << {String message, boolean force = false ->
            provider.sendAlarm(message, force)
        }
        mc.sendAlarm << {String message, String source, boolean force = false ->
            provider.sendAlarm(message, source, force)
        }
    }
}