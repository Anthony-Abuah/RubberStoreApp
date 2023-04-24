package com.example.rubberstoreapp.feature_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.navigation.compose.rememberNavController
import com.example.rubberstoreapp.feature_app.presentation.theme.RubberStoreAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RubberStoreAppTheme {
                //CustomerNavGraph()

                val navController = rememberNavController()

                MainScreen(navController = navController)








                /*var name by remember {
                    mutableStateOf("")
                }
                var email by remember {
                    mutableStateOf("")
                }
                var password by remember {
                    mutableStateOf("")
                }

                val viewModel: RegisterViewModel = hiltViewModel()
                val state = viewModel.registerResponse.value
                val scaffoldState = rememberScaffoldState()

                LaunchedEffect(key1 = true){
                    viewModel.eventFlow.collectLatest {event->
                        when(event){
                            is RegisterViewModel.UIEvent.ShowSnackBar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                Scaffold(
                    scaffoldState = scaffoldState,
                ){paddingValues->
                    Box(modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .padding(paddingValues)) {
                        if (state.isLoading){
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(value = name,
                                onValueChange = { text -> name = text },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Name") })

                            Spacer(modifier = Modifier.height(16.dp))

                            TextField(value = email,
                                onValueChange = { text -> email = text },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Email") })

                            Spacer(modifier = Modifier.height(16.dp))

                            TextField(value = password,
                                onValueChange = { text -> password = text },
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = { Text(text = "Password") })

                            Spacer(modifier = Modifier.height(16.dp))

                            Button(
                                onClick = {
                                    viewModel.register(name,email, password)
                                          },
                                modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)) {
                                Text(text = "Register")
                            }
                        }

                        }
                    }*/
                }
            }
        }
    }

