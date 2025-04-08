package com.uiel.keep.feature.home.component

import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.uiel.keep.R
import com.uiel.keep.model.AppInfo

@Composable
fun CategoryBottomSheetContent(
    modifier: Modifier = Modifier,
    storeSelectApps: Set<String>,
    onComplete: (Set<String>) -> Unit,
) {
    val context = LocalContext.current
    val packageManager = context.packageManager
    val apps = getInstalledApps(packageManager)
    val selectedAppPackages by remember { mutableStateOf(storeSelectApps.toMutableSet()) }
    var isSelectAll by remember(storeSelectApps) { mutableStateOf(apps.map { it.packageName }.toSet() == storeSelectApps.toSet()) }
    var searchContent by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
    ) {
        Spacer(modifier = Modifier.padding(top = 40.dp))
        Text(
            text = stringResource(R.string.activity_selection),
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
        )
        Spacer(modifier = Modifier.height(12.dp))
        SearchTextField(
            value = { searchContent },
            hint = stringResource(R.string.search),
            onValueChange = { searchContent = it }
        )
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(shape = RoundedCornerShape(12.dp), color = Color.DarkGray),
        ) {
            if (searchContent.isEmpty()) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Checkbox(
                            checked = isSelectAll,
                            onCheckedChange = { checked ->
                                isSelectAll = checked
                                selectedAppPackages.apply {
                                    clear()
                                    if (checked) addAll(apps.map { it.packageName })
                                }
                            },
                        )
                        Image(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = painterResource(R.drawable.app_icon),
                            contentDescription = null,
                        )
                        Text(
                            text = stringResource(R.string.all_apps),
                        )
                    }
                }
            }
            items(
                items = apps.filter { it.appName.contains(searchContent, ignoreCase = true) },
                key = { it.packageName }
            ) { app ->
                var isCheck by remember(isSelectAll, selectedAppPackages) {
                    mutableStateOf(
                        isSelectAll || selectedAppPackages.contains(app.packageName)
                    )
                }
                AppItem(
                    image = app.appIcon.toBitmap().asImageBitmap(),
                    name = app.appName,
                    checked = isCheck,
                    onCheckedChange = {
                        selectedAppPackages.apply {
                            if (contains(app.packageName)) {
                                remove(app.packageName)
                                isCheck = false
                            } else {
                                add(app.packageName)
                                isCheck = true
                            }
                        }
                        isSelectAll = apps.map { it.packageName }.toSet() == selectedAppPackages.toSet()
                    }
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp, bottom = 24.dp),
            onClick = { onComplete(selectedAppPackages) },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonColors(
                containerColor = Color(0xFFFE9E0B),
                contentColor = Color.White,
                disabledContainerColor = Color(0xFFFE9E0B),
                disabledContentColor = Color.White,
            ),
            contentPadding = PaddingValues(vertical = 18.dp)
        ) {
            Text(
                text = stringResource(R.string.selection_complete),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

private fun getInstalledApps(packageManager: PackageManager): List<AppInfo> {
    val apps = packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        .filter { packageManager.getLaunchIntentForPackage(it.packageName) != null && it.packageName != "com.uiel.keep" }
    return apps.map { app ->
        AppInfo(
            packageName = app.packageName,
            appName = packageManager.getApplicationLabel(app).toString(),
            appIcon = packageManager.getApplicationIcon(app),
            isChecked = false,
        )
    }
}

@Preview
@Composable
private fun CategoryBottomSheetContentPreview() {
    CategoryBottomSheetContent(
        storeSelectApps = emptySet(),
        onComplete = { },
    )
}