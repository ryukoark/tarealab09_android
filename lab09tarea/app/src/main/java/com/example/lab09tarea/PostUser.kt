package com.example.lab09tarea

import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search

@Composable
fun ScreenUsers(navController: NavHostController, servicio: UserApiService) {
    val users = remember { mutableStateListOf<UserModel>() }
    var errorMessage by remember { mutableStateOf<String?>(null) }  // Para capturar errores
    var isLoading by remember { mutableStateOf(true) }  // Variable para mostrar si está cargando

    LaunchedEffect(Unit) {
        try {
            val response = servicio.getAllUsers()
            users.addAll(response.users)
            isLoading = false  // Cambiar a falso cuando los datos se hayan cargado
        } catch (e: Exception) {
            Log.e("Error", "Failed to fetch users: ${e.message}")
            errorMessage = "Failed to load users: ${e.message}"
            isLoading = false
        }
    }

    // Mostrar un indicador de carga mientras los datos se obtienen
    if (isLoading) {
        CircularProgressIndicator(Modifier.padding(16.dp))
    } else if (errorMessage != null) {
        Text(text = errorMessage!!, color = Color.Red, modifier = Modifier.padding(16.dp))
    } else {
        LazyColumn {
            items(users) { user ->
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(text = user.id.toString(), Modifier.weight(0.05f), textAlign = TextAlign.End)
                    Spacer(Modifier.padding(horizontal = 1.dp))
                    Text(text = "${user.firstName} ${user.lastName}", Modifier.weight(0.7f))
                    IconButton(
                        onClick = {
                            navController.navigate("userDetails/${user.id}")
                        },
                        Modifier.weight(0.1f)
                    ) {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Ver detalles")
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenUser(navController: NavHostController, servicio: UserApiService, id: Int) {
    var user by remember { mutableStateOf<UserModel?>(null) }
    LaunchedEffect(Unit) {
        try {
            user = servicio.getUserById(id)
        } catch (e: Exception) {
            Log.e("Error", "Failed to fetch user details: ${e.message}")
        }
    }

    // Create a scroll state
    val scrollState = rememberScrollState()

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(scrollState) // Enable scrolling
    ) {
        if (user != null) {
            // First Name
            OutlinedTextField(
                value = user!!.firstName,
                onValueChange = {},
                label = { Text("First Name") },
                readOnly = true
            )
            // Last Name
            OutlinedTextField(
                value = user!!.lastName,
                onValueChange = {},
                label = { Text("Last Name") },
                readOnly = true
            )
            // Age
            OutlinedTextField(
                value = user!!.age.toString(),
                onValueChange = {},
                label = { Text("Age") },
                readOnly = true
            )
            // Gender
            OutlinedTextField(
                value = user!!.gender,
                onValueChange = {},
                label = { Text("Gender") },
                readOnly = true
            )
            // Email
            OutlinedTextField(
                value = user!!.email,
                onValueChange = {},
                label = { Text("Email") },
                readOnly = true
            )
            // Phone
            OutlinedTextField(
                value = user!!.phone,
                onValueChange = {},
                label = { Text("Phone") },
                readOnly = true
            )
            // Username
            OutlinedTextField(
                value = user!!.username,
                onValueChange = {},
                label = { Text("Username") },
                readOnly = true
            )
            // Birth Date
            OutlinedTextField(
                value = user!!.birthDate,
                onValueChange = {},
                label = { Text("Birth Date") },
                readOnly = true
            )
            // Image
            OutlinedTextField(
                value = user!!.image,
                onValueChange = {},
                label = { Text("Image URL") },
                readOnly = true
            )
            // Blood Group
            OutlinedTextField(
                value = user!!.bloodGroup,
                onValueChange = {},
                label = { Text("Blood Group") },
                readOnly = true
            )
            // Height
            OutlinedTextField(
                value = user!!.height.toString(),
                onValueChange = {},
                label = { Text("Height (cm)") },
                readOnly = true
            )
            // Weight
            OutlinedTextField(
                value = user!!.weight.toString(),
                onValueChange = {},
                label = { Text("Weight (kg)") },
                readOnly = true
            )
            // Eye Color
            OutlinedTextField(
                value = user!!.eyeColor,
                onValueChange = {},
                label = { Text("Eye Color") },
                readOnly = true
            )
            // Hair Color
            OutlinedTextField(
                value = user!!.hair.color,
                onValueChange = {},
                label = { Text("Hair Color") },
                readOnly = true
            )
            // Hair Type
            OutlinedTextField(
                value = user!!.hair.type,
                onValueChange = {},
                label = { Text("Hair Type") },
                readOnly = true
            )
        } else {
            Text("Loading user data...")
        }
    }
}
