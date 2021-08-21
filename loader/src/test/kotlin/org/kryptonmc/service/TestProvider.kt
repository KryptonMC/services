package org.kryptonmc.service

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@ServiceProvider("test-provider", "1.0")
class TestProvider {

    @Provider("formatter")
    fun dateFormatter(): DateTimeFormatter = DateTimeFormatter.ISO_DATE

    @Provider("timeNow")
    fun timeNow(formatter: DateTimeFormatter): String = formatter.format(LocalDateTime.now())
}
