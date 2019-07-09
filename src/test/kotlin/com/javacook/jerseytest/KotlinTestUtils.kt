package com.javacook.jerseytest

import assertk.Assert
import assertk.assertions.isEqualTo
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.core.Response
import kotlin.reflect.KClass

// Dies ist eine Extension Function:
fun Assert<Response>.hasStatus(expected: Int) = given {
    response -> assertThat(response.status).isEqualTo(expected)
}

fun Assert<Response>.hasContent(expected: String) = given {
    response ->
        val content = response.readEntity(String::class.java)
        assertThat(content).isEqualTo(expected)
}


fun createResourceConfig(resource: KClass<*>,
                         vararg bindings: Pair<KClass<*>, KClass<*>>): ResourceConfig {
    return object: ResourceConfig() {
        init {
            register(resource.java)
            register(object : AbstractBinder() {
                override fun configure() {
                    bindings.forEach {
                        bind(it.first.java).to(it.second.java)
                    }
                }
            })
        }
    }
}