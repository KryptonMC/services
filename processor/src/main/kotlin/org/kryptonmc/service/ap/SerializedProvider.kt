package org.kryptonmc.service.ap

import org.kryptonmc.service.loader.ProviderMetadata
import org.kryptonmc.service.loader.ProviderMethod

class SerializedProvider(
    override val id: String,
    override val version: String,
    override val className: String,
    override val methods: List<ProviderMethod>,
) : ProviderMetadata
