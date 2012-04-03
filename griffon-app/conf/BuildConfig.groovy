griffon.project.dependency.resolution = {
    inherits "global"
    log "warn"
    repositories {
        griffonHome()
        mavenCentral()
    }
    dependencies {
        String jalarmsVersion = '1.6.1'
        compile("net.sf.jalarms:jalarms-core:$jalarmsVersion",
                "net.sf.jalarms:jalarms-channels:$jalarmsVersion") {
            excludes 'spring-context-support', 'memcached', 'org.apache.servicemix.bundles.jsendnsca-core', 'jml'        
        }
    }
}

griffon {
    doc {
        logo = '<a href="http://griffon.codehaus.org" target="_blank"><img alt="The Griffon Framework" src="../img/griffon.png" border="0"/></a>'
        sponsorLogo = "<br/>"
        footer = "<br/><br/>Made with Griffon (@griffon.version@)"
    }
}

