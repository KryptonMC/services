package org.kryptonmc.service

import org.kryptonmc.service.loader.ProviderMetadata
import org.kryptonmc.service.loader.ProviderMethod

class DummyProvider(
    override val id: String,
    override val version: String,
    override val className: String,
    override val methods: List<ProviderMethod>
) : ProviderMetadata

class DummyMethod(
    override val name: String,
    override val parameters: Array<String>,
    override val returnType: String,
    override val componentName: String?
) : ProviderMethod
