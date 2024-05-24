package com.example.macropay.data.datasource.remote

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.example.macropay.data.datasource.exception.DataException
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    fun signIn(email: String, password: String): Flow<Result<String>> = callbackFlow {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                trySend(Result.success(it.user?.uid.orEmpty()))
            }
            .addOnFailureListener {
                it.printStackTrace()
                trySend(Result.failure(DataException.SignInException()))
            }
        awaitClose { close() }
    }

    suspend fun logOut() = firebaseAuth.signOut()
}
