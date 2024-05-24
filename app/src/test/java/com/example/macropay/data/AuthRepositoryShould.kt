package com.example.macropay.data

import com.example.macropay.core.assertThatEquals
import com.example.macropay.core.assertThatIsInstanceOf
import com.example.macropay.data.datasource.exception.AuthException
import com.example.macropay.data.datasource.remote.AuthRemoteDataSource
import com.example.macropay.fakedata.ANY_PASSWORD
import com.example.macropay.fakedata.ANY_USER_EMAIL
import com.example.macropay.fakedata.ANY_USER_ID
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AuthRepositoryShould {

    private val authRemoteDataSource = mock<AuthRemoteDataSource>()

    private lateinit var authRepository: AuthRepository

    @Before
    fun setup() {
        authRepository = AuthRepository(authRemoteDataSource)
    }

    @Test
    fun `Return UserId when signIn is called and signIn in datasource is success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in datasource is failure`() = runTest {
        val resultSignInException: Result<String> = Result.failure(AuthException.SignInException())

        whenever(authRemoteDataSource.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignInException))

        val result = authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRemoteDataSource).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}
