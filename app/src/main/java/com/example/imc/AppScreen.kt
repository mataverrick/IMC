package com.example.imc

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.imc.ui.theme.ImcTheme

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
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
            value = "",
            onValueChange = {},
            label = { Text("Altura") },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            label = { Text("Peso") },
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Button(onClick = {}, modifier = Modifier.width(350.dp).padding(start = 20.dp, end = 20.dp)) {
            Text("Calcular")
        }
        OutlinedButton(onClick = {}, modifier = Modifier.width(350.dp).padding(start = 20.dp, end = 20.dp)) {
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