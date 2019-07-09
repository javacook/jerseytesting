package com.javacook.jerseytest

import assertk.Assert
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import javax.ws.rs.core.Response
import kotlin.reflect.KClass

class YourResourceTest : JerseyTest() {

    override fun configure() = createResourceConfig(MyResource::class,
            MyServiceImpl::class to MyService::class)

    @Test
    fun hello() {
        val response = target("/say/hello").request().get()
        assertThat(response.status).isEqualTo(200)
    }


    @Test
    fun `Mit Kotlin lassen sich noch coolere Sachen machen`() {
        val response = target("/say/hello").request().get()
        assertThat(response).hasStatus(200)
    }









    // Dies ist eine Extension Function:
    fun Assert<Response>.hasStatus(expected: Int) = given {
        actual -> assertThat(actual.status).isEqualTo(expected)
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

}
