package org.kryptonmc.service.ap

import org.kryptonmc.service.loader.ProviderMethod

class SerializedMethod(
    override val name: String,
    override val parameters: Array<String>,
    override val returnType: String,
    override val componentName: String?
) : ProviderMethod
