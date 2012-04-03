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

import griffon.core.GriffonClass
import griffon.core.GriffonApplication
import com.solab.alarms.AlarmSender
import com.solab.alarms.AlarmChannel
import griffon.plugins.jalarms.JAlarmsEnhancer

/**
 * @author Andres Almiray
 */
class JalarmsGriffonAddon {
    void addonPostInit(GriffonApplication app) {
        def types = app.config.griffon?.jalarms?.injectInto ?: ['controller']
        for(String type : types) {
            for(GriffonClass gc : app.artifactManager.getClassesOfType(type)) {
                JAlarmsEnhancer.enhance(gc.metaClass)
            }
        }
    }

    private sendAlarm = {String... args ->
        app.applicationContext.alarmer.sendAlarm(*args)
    }

    def doWithSpring = {
        alarmer(com.solab.alarms.AlarmSenderImpl) { bean ->
            bean.scope = 'singleton'
            bean.autowire = 'byName'
        }
    }

    def whenSpringReady = { app ->
        def ctx = app.applicationContext
        Map<String, AlarmChannel> channels = ctx.getBeansOfType(AlarmChannel)

        if(channels) {
            List<AlarmChannel> chnls = []
            chnls.addAll(channels.values())
            ctx.alarmer.alarmChannels = chnls
        }
    }
}
