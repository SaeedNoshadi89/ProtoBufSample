package com.sn.protobufsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.sn.protobufsample.ui.theme.ProtoBufSampleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setContent {

            val collectFirstName = viewModel.protoFirstName
            val collectLastName = viewModel.protoLastName
            val collectAge = viewModel.protoAge
            val scope = rememberCoroutineScope()

            var firstNameTextValue by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var lastNameTextValue by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var ageTextValue by remember {
                mutableStateOf(TextFieldValue(""))
            }
            ProtoBufSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = collectFirstName)
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = collectLastName)
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(text = collectAge.toString())
                        Spacer(modifier = Modifier.size(34.dp))
                        TextField(value = firstNameTextValue, onValueChange = {
                            firstNameTextValue = it
                        })
                        Spacer(modifier = Modifier.size(8.dp))
                        TextField(value = lastNameTextValue, onValueChange = {
                            lastNameTextValue = it
                        })
                        Spacer(modifier = Modifier.size(8.dp))
                        TextField(value = ageTextValue, onValueChange = {
                            ageTextValue = it
                        })
                        
                        Spacer(modifier = Modifier.size(24.dp))
                        Button(onClick = {
                            scope.launch {
                                viewModel.updateProtoValues(firstName = firstNameTextValue.text, lastName = lastNameTextValue.text, age = ageTextValue.text.toInt())
                            }
                        }) {
                            Text(text = "Save")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProtoBufSampleTheme {
        Greeting("Android")
    }
}