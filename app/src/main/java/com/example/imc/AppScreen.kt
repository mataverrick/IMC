package com.example.imc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var showDialog by rememberSaveable { mutableStateOf(false) }

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
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Email icon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(
            value = appUiState.peso,
            onValueChange = { it ->
                appViewModel.onPesoChange(it)

            },
            label = { Text("Peso") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Email icon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(
            onClick = {
                showDialog = true
                appViewModel.calcularImc()
            },
            modifier = Modifier
                .width(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = null
                )
                Text("Calcular")
            }
        }

        OutlinedButton(
            onClick = { appViewModel.resetApp() },
            modifier = Modifier
                .width(350.dp)
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = null
                )
                Text("Limpiar")
            }
        }

        if (showDialog) {
            AppModal(onDismiss = { showDialog = false }, imc = appUiState.imc)
        }
    }
}

@Composable
fun AppModal(onDismiss: () -> Unit, imc: String?) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            if (imc == null) {
                Text(text = "Verifique sus datos de nuevo")
            } else {
                Text(text = imc)
            }
        },
        confirmButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cerrar")
            }
        }
    )
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