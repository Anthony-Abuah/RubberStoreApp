package com.example.rubberstoreapp.feature_app.presentation.composables.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.rubberstoreapp.feature_app.presentation.theme.ButtonBackground
import com.example.rubberstoreapp.feature_app.presentation.theme.MainTextFieldTextColor
import com.example.rubberstoreapp.feature_app.presentation.theme.TextFieldBackground

@Composable
fun AutoComplete(
    label: String,
    listItems: List<String>,
    getInfo: (listItem: String) -> Unit
) {

    var listItem by remember {
        mutableStateOf("")
    }

    val heightTextFields by remember {
        mutableStateOf(60.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {
        //Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    placeholder = {
                          Placeholder(placeholderText = label)
                    },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
                        .border(
                            width = 1.8.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = listItem,
                    onValueChange = {item ->
                        listItem = item
                        getInfo(listItem)
                        expanded = true
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = TextFieldBackground,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = MainTextFieldTextColor,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = Icons.Rounded.KeyboardArrowDown,
                                contentDescription = "arrow",
                                tint = Color.Black
                            )
                        }
                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 15.dp,
                    backgroundColor = TextFieldBackground,
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (listItem.isNotEmpty()) {
                            items(
                                listItems.filter {
                                    it.lowercase().contains(listItem.lowercase()) ||
                                    it.lowercase().contains("others")
                                }.sorted()
                            ) {
                                DropDownItems(title = it) { title ->
                                    listItem = title
                                    getInfo(title)
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                listItems.sorted()
                            ) {
                                DropDownItems(title = it) { title ->
                                    listItem = title
                                    getInfo(title)
                                    expanded = false
                                }
                            }
                        }

                    }

                }
            }

        }

    //}


}
