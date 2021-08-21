package org.kryptonmc.service.loader

/**
 * Metadata for a service provider.
 */
interface ProviderMetadata {

    /**
     * The ID of the provider.
     */
    val id: String

    /**
     * The version of the provider.
     */
    val version: String

    /**
     * The name of the provider class.
     */
    val className: String

    /**
     * The indexed methods from the provider class.
     */
    val methods: List<ProviderMethod>
}
