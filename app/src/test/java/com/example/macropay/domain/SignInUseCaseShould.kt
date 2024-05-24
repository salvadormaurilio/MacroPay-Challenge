package com.example.macropay.domain

import com.example.macropay.core.assertThatEquals
import com.example.macropay.core.assertThatIsInstanceOf
import com.example.macropay.data.AuthRepository
import com.example.macropay.data.datasource.exception.AuthException
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

class SignInUseCaseShould {

    private val authRepository = mock<AuthRepository>()

    private lateinit var signInUseCase: SignInUseCase

    @Before
    fun setup() {
        signInUseCase = SignInUseCase(authRepository)
    }

    @Test
    fun `Return UserId when signIn is called and signIn repository are success`() = runTest {
        val resultUserId = Result.success(ANY_USER_ID)

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultUserId))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatEquals(result?.getOrNull(), ANY_USER_ID)
    }

    @Test
    fun `Get SignInException when signIn is called and signIn in repository is failure`() = runTest {
        val resultSignUpException: Result<String> = Result.failure(AuthException.SignInException())

        whenever(authRepository.signIn(ANY_USER_EMAIL, ANY_PASSWORD)).thenReturn(flowOf(resultSignUpException))

        val result = signInUseCase.signIn(ANY_USER_EMAIL, ANY_PASSWORD).lastOrNull()

        verify(authRepository).signIn(ANY_USER_EMAIL, ANY_PASSWORD)
        assertThatIsInstanceOf<AuthException.SignInException>(result?.exceptionOrNull())
    }
}