package org.kryptonmc.service.loader

import com.google.inject.Injector
import java.lang.invoke.MethodHandles
import java.lang.invoke.MethodType
import java.util.LinkedList

class ServiceLoader {

    private val loadedServices = mutableMapOf<String, MutableMap<Class<Any>, ServiceInstance>>()
    private val lookup = MethodHandles.publicLookup()

    @Suppress("UNCHECKED_CAST")
    fun load(loader: ClassLoader, injector: Injector, providers: List<ProviderMetadata>) = providers.forEach { provider ->
        val metaKey = "${provider.id}:${provider.version}"
        if (loadedServices.containsKey(metaKey)) return@forEach // Already loaded
        val clazz = loader.loadClass(provider.className)
        val instance = injector.getInstance(clazz)

        provider.methods.forEach methods@{ method ->
            val existing = loadedServices[metaKey] ?: emptyMap()
            val returnType = loader.loadClass(method.returnType) as Class<Any>
            val parameterTypes = method.parameters.map { loader.loadClass(it) }
            val type = MethodType.methodType(returnType, parameterTypes)
            val handle = lookup.findVirtual(clazz, method.name, type)
            val parameters = parameterTypes.mapTo(LinkedList()) { existing[it]!!.instance }.apply { addFirst(instance) }
            loadedServices.getOrPut(metaKey) { mutableMapOf() }[returnType] = ServiceInstance(
                provider.id,
                provider.version,
                returnType,
                handle.invokeWithArguments(parameters),
                method.componentName
            )
        }
    }

    fun createModule() = ServicesModule(loadedServices.values)
}
