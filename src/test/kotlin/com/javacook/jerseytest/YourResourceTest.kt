package com.javacook.jerseytest

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.glassfish.jersey.test.JerseyTest
import org.junit.Test

class YourResourceTest : JerseyTest() {

    override fun configure() = createResourceConfig(
                    MyResource::class,
                    MyServiceImpl::class to MyService::class)

    @Test
    @Deprecated("Look down there")
    fun testGetRequestSayHello() {
        val response = target("/say/hello").request().get()
        assertThat(response.status).isEqualTo(200)
    }


    @Test
    fun `Mit Kotlin lassen sich noch coolere Sachen machen`() {
        val response = target("/say/hello").request().get()
        assertThat(response).hasStatus(200)
        assertThat(response).hasContent("Hello Schnucki")
    }

}
