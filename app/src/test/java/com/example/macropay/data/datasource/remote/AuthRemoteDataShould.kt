package mx.android.storichallenge.data.datasource.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.test.runTest
import com.example.macropay.core.collectAndCancel
import com.example.macropay.data.datasource.remote.AuthRemoteDataSource
import com.example.macropay.fakedata.ANY_PASSWORD
import com.example.macropay.fakedata.ANY_USER_EMAIL
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRemoteDataShould {

    private val firebaseAuth = mock<FirebaseAuth>()
    private val authResultTask = mock<Task<AuthResult>>()

    private lateinit var authRemoteDataSource: AuthRemoteDataSource

    @Before
    fun setup() {
        authRemoteDataSource = AuthRemoteDataSource(firebaseAuth)
    }

    @Test
    fun `Call signInWithEmailAndPassword when signIn is called`() = runTest {
        whenever(firebaseAuth.signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(authResultTask)
        whenever(authResultTask.addOnSuccessListener(any())).thenReturn(authResultTask)

        authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD).collectAndCancel()

        verify(firebaseAuth).signInWithEmailAndPassword(ANY_USER_EMAIL, ANY_PASSWORD)
    }
}