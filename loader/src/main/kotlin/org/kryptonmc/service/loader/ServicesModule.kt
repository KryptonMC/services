package org.kryptonmc.service.loader

import com.google.inject.AbstractModule
import org.kryptonmc.service.Components

class ServicesModule internal constructor(private val serviceMaps: Collection<Map<Class<Any>, ServiceInstance>>) : AbstractModule() {

    override fun configure() = serviceMaps.forEach { services ->
        services.values.forEach {
            if (it.componentName == null) {
                bind(it.type).toInstance(it.instance)
                bind(it.type).annotatedWith(Components.service(it.id, it.version)).toInstance(it.instance)
            }
            bind(it.type).annotatedWith(Components.of(it.id, it.version, it.componentName.orEmpty())).toInstance(it.instance)
        }
    }
}
