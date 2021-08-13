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
 * Marker annotation for methods to indicate that a method provides a service.
 *
 * The name of the provider is optional **if and only if** the provider only
 * provides a single service.
 *
 * @param name the name of the provider
 */
annotation class Provider(val name: String = "")
