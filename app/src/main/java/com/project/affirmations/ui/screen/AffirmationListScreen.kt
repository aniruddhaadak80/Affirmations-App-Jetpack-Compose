package com.project.affirmations.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.project.affirmations.data.Datasource
import com.project.affirmations.model.Affirmation
import androidx.navigation.NavController

@Composable
fun AffirmationsApp(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(navController = navController, showBackButton = false)
        },
        content = { paddingValues ->
            AffirmationList(
                affirmationList = Datasource().loadAffirmations(),
                onClick = { affirmation ->
                    navController.navigate("details/${affirmation.stringResourceId}/${affirmation.authorResourceId}/${affirmation.imageResourceId}")
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    )
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, onClick: (Affirmation) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier
                    .padding(12.dp, 8.dp)
                    .clickable { onClick(affirmation) }
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier.padding(top = 16.dp , bottom = 8.dp , start = 16.dp , end = 16.dp)
            )
            Text(
                text = "~ " + stringResource(id = affirmation.authorResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .padding(8.dp)
            )
        }
    }
}
