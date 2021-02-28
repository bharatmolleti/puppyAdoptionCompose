/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModel = viewModel<DataViewModel>()
    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = Routes.PUPPY_LIST_SCREEN) {
            composable(Routes.PUPPY_LIST_SCREEN) {
                PuppyListScreen(
                    puppies = viewModel.puppies,
                    onItemClicked = {
                        viewModel.onPuppyClicked(it)
                        navController.navigate(Routes.PUPPY_DETAILS_SCREEN)
                    }
                )
            }
            composable(Routes.PUPPY_DETAILS_SCREEN) {
                PuppyDetailsScreen(
                    puppy = viewModel.clickedPuppy,
                    onBackPressed = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

object Routes {
    const val PUPPY_LIST_SCREEN = "puppyList"
    const val PUPPY_DETAILS_SCREEN = "puppyDetails"
}

@Composable
fun PuppyListScreen(puppies: List<Puppy>, onItemClicked: (puppy: Puppy) -> Unit) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.secondary)) {
        WelcomeSection()
        PuppyList(
            puppies = puppies,
            modifier = Modifier.background(
                color = MaterialTheme.colors.primary
            ),
            onItemClicked = {
                onItemClicked(it)
            }
        )
    }
}

@Composable
fun WelcomeSection(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp, vertical = 32.dp)) {
        Row {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontSize = 18.sp,
                    fontFamily = FontFamily.Serif
                )
            )
        }
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = stringResource(R.string.header_line_1),
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.subtitle1.copy(
                fontSize = 18.sp,
                fontFamily = FontFamily.SansSerif,
                fontStyle = FontStyle.Italic
            )
        )
    }
}

@Composable
fun PuppyList(
    puppies: List<Puppy>,
    onItemClicked: (puppy: Puppy) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp),
        content = {
            items(puppies) { puppy ->
                PuppyItem(
                    puppy = puppy,
                    modifier = Modifier.clickable {
                        onItemClicked(puppy)
                    }
                )
                Divider()
            }
        }
    )
}

@Composable
fun PuppyItem(puppy: Puppy, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = MaterialTheme.colors.primaryVariant)
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        PuppyImage(image = puppy.imageUrl)
        Spacer(modifier = Modifier.size(16.dp))
        PuppyInfo(puppy = puppy)
    }
}

@Composable
fun PuppyImage(image: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(120.dp)
            .background(color = Color.Gray, shape = RoundedCornerShape(size = 16.dp))
            .clip(shape = RoundedCornerShape(size = 16.dp))
    ) {
        GlideImage(
            data = image,
            contentDescription = "Image",
            fadeIn = true,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

@Composable
fun PuppyInfo(puppy: Puppy) {
    Column {
        Text(
            text = puppy.name,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.h6
        )
        Text(
            text = puppy.character,
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(
                id = R.string.puppy_body_info_n,
                puppy.ageInMonth.toString(),
                puppy.height.toString()
            ),
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = displayGender(gender = puppy.gender),
            color = MaterialTheme.colors.surface,
            style = MaterialTheme.typography.subtitle2
        )
        Spacer(modifier = Modifier.size(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Location Icon")
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = stringResource(id = R.string.Location, puppy.location),
                color = MaterialTheme.colors.surface,
                style = MaterialTheme.typography.caption
            )
        }
    }
}

@Composable
fun displayGender(gender: Gender): String {
    return when (gender) {
        Gender.MALE -> stringResource(id = R.string.gender_n, stringResource(id = R.string.male))
        Gender.FEMALE -> stringResource(
            id = R.string.gender_n,
            stringResource(id = R.string.female)
        )
    }
}

@Composable
fun PuppyDetailsScreen(puppy: Puppy, onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(elevation = 0.dp, backgroundColor = MaterialTheme.colors.secondary) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = rememberRipple(bounded = false)
                        ) {
                            onBackPressed()
                        }
                        .padding(8.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.background(color = MaterialTheme.colors.primaryVariant)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 40.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            ) {
                GlideImage(
                    data = puppy.imageUrl,
                    contentDescription = "Image",
                    fadeIn = true,
                    contentScale = ContentScale.Inside
                )
            }
            Column(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colors.primary
                    )
                    .weight(1f)
            ) {

                Row() {
                    Column(
                        modifier = Modifier.background(
                            color = MaterialTheme.colors.primary
                        )
                    ) {
                        Text(
                            text = puppy.name,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = puppy.character,
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            text = displayGender(gender = puppy.gender),
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        FloatingActionButton(
                            modifier = Modifier
                                .padding(16.dp),
                            onClick = {
                                //
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.adopt),
                                modifier = Modifier.padding(horizontal = 80.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.size(12.dp))
                Characteristics(puppy)
                Spacer(modifier = Modifier.size(12.dp))
                Text(
                    text = stringResource(R.string.description),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.size(12.dp))
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = puppy.description,
                        style = MaterialTheme.typography.body1.copy(lineHeight = 24.sp),
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .verticalScroll(rememberScrollState())
                    )
                }
            }
        }
    }
}

@Composable
fun Characteristics(puppy: Puppy) {
    Row(modifier = Modifier.horizontalScroll(state = rememberScrollState())) {
        CharacteristicChip(text = stringResource(id = R.string.age, puppy.ageInMonth.toString()))
        CharacteristicChip(
            text = stringResource(
                id = R.string.height,
                puppy.height.toString()
            )
        )
        CharacteristicChip(text = stringResource(id = R.string.color, puppy.color))
    }
}

@Composable
private fun CharacteristicChip(text: String) {
    Surface(
        modifier = Modifier.padding(horizontal = 12.dp),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.primaryVariant,
        border = BorderStroke(width = 1.dp, color = Color.Gray)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.surface
        )
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
