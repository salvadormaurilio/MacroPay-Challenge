package com.example.macropay.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.macropay.R
import com.example.macropay.core.ui.empty
import com.example.macropay.ui.theme.MicroPayChallengeTheme
import com.example.macropay.ui.theme.Space16


@Composable
fun EmailTextField(modifier: Modifier = Modifier, email: String, error: Int? = null, onValueChange: (String) -> Unit) {
    val messageError = error?.run { stringResource(id = this) }.orEmpty()

    OutlinedTextField(
        value = email,
        label = { Text(text = stringResource(id = R.string.email)) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        isError = messageError.isNotEmpty(),
        supportingText = {
            if (messageError.isNotEmpty()) {
                Text(
                    text = messageError,
                    color = Color.Red
                )
            }
        },
        onValueChange = { onValueChange(it) },
    )
}

@Composable
fun PasswordTextField(modifier: Modifier = Modifier, password: String, error: Int? = null, onValueChange: (String) -> Unit) {
    val messageError = error?.run { stringResource(id = this) }.orEmpty()

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        label = { Text(text = stringResource(id = R.string.password)) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = String.empty()
                )
            }
        },
        isError = messageError.isNotEmpty(),
        supportingText = {
            if (messageError.isNotEmpty()) {
                Text(
                    text = messageError,
                    color = Color.Red
                )
            }
        },
        onValueChange = { onValueChange(it) },
    )
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    MicroPayChallengeTheme {
        var email by rememberSaveable { mutableStateOf(String.empty()) }

        Box(modifier = Modifier.padding(Space16)) {
            EmailTextField(
                email = email,
                onValueChange = { email = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldErrorPreview() {
    MicroPayChallengeTheme {
        var email by rememberSaveable { mutableStateOf(String.empty()) }

        Box(modifier = Modifier.padding(Space16)) {
            EmailTextField(
                email = email,
                error = R.string.error_email_invalid,
                onValueChange = { email = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    MicroPayChallengeTheme {
        var password by rememberSaveable { mutableStateOf(String.empty()) }

        Box(modifier = Modifier.padding(Space16)) {
            PasswordTextField(
                password = password,
                onValueChange = { password = it }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PasswordTextFieldErrorPreview() {
    MicroPayChallengeTheme {
        var password by rememberSaveable { mutableStateOf(String.empty()) }

        Box(modifier = Modifier.padding(Space16)) {
            PasswordTextField(
                password = password,
                error = R.string.error_password_invalid,
                onValueChange = { password = it }
            )
        }
    }
}
