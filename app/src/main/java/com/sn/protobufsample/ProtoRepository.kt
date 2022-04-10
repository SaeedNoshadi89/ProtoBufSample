package com.sn.protobufsample


import android.content.Context
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.catch
import java.io.IOException

class ProtoRepository(private val context: Context) {
    private val Context.dataStore by dataStore("my_data", serializer = ProtoSerializer)

    val readProto: kotlinx.coroutines.flow.Flow<Person> = context.dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                emit(Person.getDefaultInstance())
            } else {
                throw ex
            }
        }

    suspend fun updateValue(newFirstName: String, newLastName: String, newAge: Int) {
        context.dataStore.updateData {
            it.toBuilder().apply {
                firstName = newFirstName
                lastName = newLastName
                age = newAge
            }.build()
        }
    }
}