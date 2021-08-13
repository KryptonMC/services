/*
 * This file is part of the Services project, licensed under the MIT license.
 *
 * Copyright (C) 2021 KryptonMC and contributors.
 *
 * This project is licensed under the terms of the MIT license.
 * For more details, please reference the LICENSE file in the top-level directory.
 */
package org.kryptonmc.service

/**
 * Marker annotation for provider classes that provide services.
 *
 * @param id the ID of the provider
 * @param version the version of the provider
 */
annotation class ServiceProvider(
    val id: String,
    val version: String
)
