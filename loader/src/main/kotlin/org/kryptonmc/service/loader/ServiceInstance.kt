package org.kryptonmc.service.loader

class ServiceInstance(
    val id: String,
    val version: String,
    val type: Class<Any>,
    val instance: Any,
    val componentName: String?
)
