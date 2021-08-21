/*
 * This file is part of the Services project, licensed under the MIT license.
 *
 * Copyright (C) 2021 KryptonMC and contributors.
 *
 * This project is licensed under the terms of the MIT license.
 * For more details, please reference the LICENSE file in the top-level directory.
 */
package org.kryptonmc.service

import com.google.inject.BindingAnnotation

/**
 * A binding annotation that wraps the [Service] annotation and the name of a service
 * in a single annotation, as Guice does not support multiple binding annotations on
 * the same declaration.
 *
 * @param service the service provider data
 * @param name the name of the service
 */
@BindingAnnotation
annotation class Component(
    val service: Service,
    val name: String
)
