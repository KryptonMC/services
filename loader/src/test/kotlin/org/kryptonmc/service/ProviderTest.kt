package org.kryptonmc.service

import com.google.inject.Guice
import org.kryptonmc.service.loader.ServiceLoader
import kotlin.test.Test

class ProviderTest {

    private val provider = DummyProvider(
        "test-provider",
        "1.0",
        "org.kryptonmc.service.TestProvider",
        listOf(DummyMethod(
            "dateFormatter",
            arrayOf(),
            "java.time.format.DateTimeFormatter",
            "formatter"
        ), DummyMethod(
            "timeNow",
            arrayOf("java.time.format.DateTimeFormatter"),
            "java.lang.String",
            "timeNow"
        ))
    )

    @Test
    fun test() {
        val injector = Guice.createInjector()
        val loader = ServiceLoader()
        loader.load(javaClass.classLoader, injector, listOf(provider))
        val module = loader.createModule()
        val childInjector = injector.createChildInjector(module)
        childInjector.getInstance(TestInjection::class.java)
    }
}
