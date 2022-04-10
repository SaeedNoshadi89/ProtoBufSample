package com.sn.protobufsample

import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object ProtoSerializer : Serializer<Person> {

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun readFrom(input: InputStream): Person {
       return try {
            Person.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            defaultValue
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: Person, output: OutputStream) {
        t.writeTo(output)
    }

    override val defaultValue: Person
        get() = Person.getDefaultInstance()
}