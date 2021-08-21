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
 * Binding annotation for requesting a service from a provider.
 *
 * It is recommended, though not required, to provide a version here, to avoid
 * the possibility of injecting the wrong version of a service.
 *
 * If a version is not provided, the loader will not do any version checking,
 * meaning the only available version will be used.
 * Additionally, if the string "any" (case-insensitive) is provided, the loader
 * will also skip version checks, and the provided version will be from the available
 * provider.
 *
 * @param id the ID of the provider to provide from
 * @param version the version of the provider to provide from
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@BindingAnnotation
annotation class Service(
    val id: String,
    val version: String = ""
)
