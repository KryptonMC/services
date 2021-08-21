package org.kryptonmc.service.loader

/**
 * A service providing method in a service provider.
 */
interface ProviderMethod {

    /**
     * The method name.
     */
    val name: String

    /**
     * The method's parameters, as an array of the fully qualified class names
     * of their types.
     */
    val parameters: Array<String>

    /**
     * The fully qualified class name of the method's return type.
     */
    val returnType: String

    /**
     * The custom name given to the provider.
     */
    val componentName: String?
}
