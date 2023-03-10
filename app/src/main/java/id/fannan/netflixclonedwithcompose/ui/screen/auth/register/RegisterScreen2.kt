//package id.fannan.netflixclonedwithcompose.ui.screen.auth.register
//
//
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Visibility
//import androidx.compose.material.icons.filled.VisibilityOff
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.input.PasswordVisualTransformation
//import androidx.compose.ui.text.input.VisualTransformation
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import id.fannan.netflixclonedwithcompose.R
//import id.fannan.netflixclonedwithcompose.ui.component.MovieAppBar
//import id.fannan.netflixclonedwithcompose.ui.screen.auth.AuthViewModel
//import id.fannan.netflixclonedwithcompose.ui.theme.Gray
//import id.fannan.netflixclonedwithcompose.ui.theme.NetflixClonedWithComposeTheme
//import id.fannan.netflixclonedwithcompose.ui.theme.Placeholder
//
//@ExperimentalMaterial3Api
//@Composable
//fun RegisterScreen(
//    navHostController: NavHostController,
//    viewModel: AuthViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AuthViewModel.Factory)
//) {
//
//    var email by rememberSaveable { mutableStateOf("") }
//    var password by rememberSaveable { mutableStateOf("") }
//    var passwordVisible by rememberSaveable { mutableStateOf(false) }
//    val userRegisterResponse by viewModel.userRegister.collectAsState()
//    LaunchedEffect(userRegisterResponse) {
//        userRegisterResponse?.let { user ->
//            navHostController.popBackStack()
//        }
//    }
//
//    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
//        MovieAppBar()
//    }) { contentPadding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(contentPadding)
//                .background(Color.Black),
//            verticalArrangement = Arrangement.Center
//        ) {
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                value = email,
//                onValueChange = { email = it },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Gray,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    textColor = Color.White
//                ),
//                label = {
//                    Text(text = stringResource(R.string.email), color = Placeholder)
//                },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Email
//                ),
//                shape = RoundedCornerShape(16.dp)
//            )
//
//            TextField(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 16.dp),
//                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
//                value = password,
//                onValueChange = { password = it },
//                colors = TextFieldDefaults.textFieldColors(
//                    containerColor = Gray,
//                    focusedIndicatorColor = Color.Transparent,
//                    unfocusedIndicatorColor = Color.Transparent,
//                    textColor = Color.White
//                ),
//                label = {
//                    Text(text = stringResource(R.string.password), color = Placeholder)
//                },
//                keyboardOptions = KeyboardOptions(
//                    keyboardType = KeyboardType.Password
//                ),
//                trailingIcon = {
//                    val image = if (passwordVisible) Icons.Filled.Visibility
//                    else Icons.Filled.VisibilityOff
//
//                    IconButton(onClick = {
//                        passwordVisible = !passwordVisible
//                    }) {
//                        Icon(imageVector = image, contentDescription = null, tint = Placeholder)
//                    }
//                },
//                shape = RoundedCornerShape(16.dp)
//            )
//
//            OutlinedButton(
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color.Transparent,
//                ),
//                shape = RoundedCornerShape(16.dp),
//                contentPadding = PaddingValues(vertical = 16.dp),
//                border = BorderStroke(
//                    width = 1.dp,
//                    color = Placeholder
//                ),
//                onClick = {
//                    viewModel.register(email, password)
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 32.dp)
//            ) {
//                Text(text = stringResource(R.string.register))
//            }
//
//            TextButton(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp)
//                    .padding(top = 64.dp),
//                colors = ButtonDefaults.textButtonColors(
//                    contentColor = Placeholder
//                ),
//                onClick = {
//                    navHostController.popBackStack()
//                }
//            ) {
//                Text(text = stringResource(id = R.string.register).uppercase())
//            }
//        }
//    }
//
//}
//
//@ExperimentalMaterial3Api
//@Preview(showBackground = true)
//@Composable
//fun LoginScreePreview() {
//    NetflixClonedWithComposeTheme() {
//        RegisterScreen(navHostController = rememberNavController())
//    }
//}