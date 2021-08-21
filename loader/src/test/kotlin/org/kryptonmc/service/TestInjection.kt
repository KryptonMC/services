package org.kryptonmc.service

import com.google.inject.Inject
import java.time.format.DateTimeFormatter

class TestInjection @Inject constructor(
    @Component(Service("test-provider", "1.0"), "formatter") val formatter: DateTimeFormatter,
    @Component(Service("test-provider", "1.0"), "timeNow") val timeNow: String
)
