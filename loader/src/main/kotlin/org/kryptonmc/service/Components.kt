/*
 * This file is part of the Services project, licensed under the MIT license.
 *
 * Copyright (C) 2021 KryptonMC and contributors.
 *
 * This project is licensed under the terms of the MIT license.
 * For more details, please reference the LICENSE file in the top-level directory.
 */
package org.kryptonmc.service

import com.google.auto.value.AutoAnnotation

internal object Components {

    @JvmStatic
    fun of(serviceId: String, version: String, name: String) = of(service(serviceId, version), name)

    @JvmStatic
    @AutoAnnotation
    fun service(id: String, version: String): Service = AutoAnnotation_Components_service(id, version)

    @JvmStatic
    @AutoAnnotation
    private fun of(service: Service, name: String): Component = AutoAnnotation_Components_of(service, name)
}
