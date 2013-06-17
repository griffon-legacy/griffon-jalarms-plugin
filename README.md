
Sends alarms over several channels
----------------------------------

Plugin page: [http://artifacts.griffon-framework.org/plugin/jalarms](http://artifacts.griffon-framework.org/plugin/jalarms)


Send application notifications using a wide range of protocols via [JAlarms][1].

Usage
-----
The plugin will inject the following dynamic methods:

* `sendAlarm(String message)` - Sends an alarm through all channels, to the default users defined for each channel, as long as the message
 hasn't been already sent very recently.
 * `sendAlarm(String message, boolean force)` - Sends an alarm through all channels, to the default users defined for each channel. If `force` is
 set to `true` then the message will be sent immediately regardless of the last time the same message was sent.
* `sendAlarm(String message, String source)` - ends an alarm through all channels, to the users defined for the specified source in each channel,
 as long as the message hasn't been already sent very recently.
 * `sendAlarm(String message, String source, boolean force)` - ends an alarm through all channels, to the users defined for the specified source 
 in each channel. If `force` is set to `true` then the message will be sent immediately regardless of the last time the same message was sent.

These methods are also accessible to any component through the singleton `griffon.plugins.jalarms.JAlarmsEnhancer`.
You can inject these methods to non-artifacts via metaclasses. Simply grab hold of a particular metaclass and call
`JAlarmsEnhancer.enhance(metaClassInstance)`.

This addon adds a bean **alarmer** of type `com.solab.alarms.AlarmSender` to the application's ApplicationContext (which means this plugin
 requires [Spring][2]). Alarm channels can be configured as any other beans would, inside `src/spring/resources.groovy`, for example

        beans = {
            consoleChannel(com.solab.alarms.channels.TestChannel)
        }

Configuration
-------------
### Dynamic method injection

Dynamic methods will be added to controllers by default. You can
change this setting by adding a configuration flag in `griffon-app/conf/Config.groovy`

    griffon.jalarms.injectInto = ['controller', 'service']

Testing
-------
Dynamic methods will not be automatically injected during unit testing, because addons are simply not initialized
for this kind of tests. However you can use `JAlarmsEnhancer.enhance(metaClassInstance, jalarmsProviderInstance)` where 
`jalarmsProviderInstance` is of type `griffon.plugins.jalarms.JAlarmsProvider`. The contract for this interface looks like this

    public interface JAlarmsProvider {
        void sendAlarm(String message);
        void sendAlarm(String message, boolean force);
        void sendAlarm(String message, String source);
        void sendAlarm(String message, String source, boolean force);
    }

It's up to you define how these methods need to be implemented for your tests. For example, here's an implementation that never
fails regardless of the arguments it receives

    class MyJAlarmsProvider implements JAlarmsProvider {
        void sendAlarm(String message, boolean force = false) { }
        void sendAlarm(String message, String source, boolean force = false) { }
    }
    
This implementation may be used in the following way

    class MyServiceTests extends GriffonUnitTestCase {
        void testSmokeAndMirrors() {
            MyService service = new MyService()
            JAlarmsEnhancer.enhance(service.metaClass, new MyJAlarmsProvider())
            // exercise service methods
        }
    }


[1]: http://jalarms.sourceforge.net/index.html
[2]: /plugin/spring

