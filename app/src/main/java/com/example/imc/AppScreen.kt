package com.example.imc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.imc.ui.theme.ImcTheme

@Composable
fun AppScreen(appViewModel: AppViewModel = viewModel(), modifier: Modifier = Modifier) {
    val appUiState: AppUiState by appViewModel.appUiState.collectAsState()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.weight),
            contentDescription = null
        )
        TextField(
            value = appUiState.altura,
            onValueChange = { it ->
                appViewModel.onAlturaChange(it)
            },
            label = { Text("Altura") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(
            value = appUiState.peso,
            onValueChange = { it ->
                appViewModel.onPesoChange(it)
            },
            label = { Text("Peso") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = {},
            modifier = Modifier
                .width(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text("Calcular")
        }
        OutlinedButton(
            onClick = {appViewModel.resetApp()},
            modifier = Modifier
                .width(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text("Limpiar")
        }
    }
}

@Composable
fun AppModal() {

}

@Preview(showBackground = true)
@Composable
fun AppPreviesw() {
    @OptIn(ExperimentalMaterial3Api::class)
    ImcTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    title = {
                        Text("Calculo de IMC")
                    }
                )
            },
        ) { innerPadding ->
            AppScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}