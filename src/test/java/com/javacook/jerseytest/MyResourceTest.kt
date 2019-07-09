package com.javacook.jerseytest

import assertk.Assert
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.glassfish.hk2.utilities.binding.AbstractBinder
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test
import javax.ws.rs.ApplicationPath
import javax.ws.rs.core.Response

class YourResourceTest : JerseyTest() {

//    class MyKotlinConfig : ResourceConfig() {
//        init {
//            register(MyResource::class.java)
//            register(object : AbstractBinder() {
//                override fun configure() {
//                    // bindAsContract(MyServiceImpl.class);
//                    bind(MyServiceImpl::class.java).to(MyService::class.java)
//                }
//            })
//        }
//    }

    override fun configure() = object: ResourceConfig() {
        init {
            register(MyResource::class.java)
            register(object : AbstractBinder() {
                override fun configure() {
                    // bindAsContract(MyServiceImpl.class);
                    bind(MyServiceImpl::class.java).to(MyService::class.java)
                }
            })
        }
    }

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
}
