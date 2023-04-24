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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun AutoCompleteWithAddButton(
    label: String,
    listItems: List<String>,
    onClick: () -> Unit,
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                placeholder = {
                    Placeholder(placeholderText = label)
                },
                modifier = Modifier
                    .weight(5f)
                    .height(heightTextFields)
                    //.background(Color.LightGray, RoundedCornerShape(15.dp))
                    .border(
                        width = 1.8.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(15.dp)
                    )
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                value = listItem,
                onValueChange = { item ->
                    listItem = item
                    getInfo(listItem)
                    expanded = true
                },
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = TextFieldBackground,
                    focusedIndicatorColor = Color.DarkGray,
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

            Spacer(modifier = Modifier.width(8.dp))

            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(60.dp),
                elevation = 10.dp,
                shape = RoundedCornerShape(15.dp),
                backgroundColor = ButtonBackground,
                contentColor = Color.White
            ) {
                IconButton(
                    onClick = onClick,
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = null,
                    )
                }
            }
        }

        AnimatedVisibility(visible = expanded) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .weight(5f),
                        //.width(textFieldSize.width.dp),
                    elevation = 5.dp,
                    backgroundColor= TextFieldBackground,
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
                Spacer(modifier = Modifier.width(8.dp))
                Box(modifier = Modifier.weight(1f))
            }
        }
    }


}
