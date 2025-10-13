package com.aliucord.manager.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.shiggy.manager.BuildConfig
import dev.shiggy.manager.R

@Composable
fun ProjectHeader(
    aliucord: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val uriHandler = LocalUriHandler.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(if (aliucord) R.drawable.ic_aliucord_logo else R.drawable.ic_rounded_shiggy),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 6.dp)
                .size(88.dp),
        )

        Text(
            text = stringResource(if (aliucord) R.string.aliucord else R.string.shiggy),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 26.sp)
        )

        Text(
            text = stringResource(if (aliucord) R.string.aliucord_description else R.string.app_description),
            style = MaterialTheme.typography.titleSmall.copy(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .6f)
            ),
            textAlign = TextAlign.Center,
        )

        Row(
            horizontalArrangement = Arrangement.Center,
        ) {
            TextButton(onClick = { uriHandler.openUri("https://github.com/${if (aliucord) "Aliucord" else BuildConfig.GITHUB_ORG}") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_account_github_white_24dp),
                    contentDescription = null,
                    modifier = Modifier.padding(end = ButtonDefaults.IconSpacing),
                )
                Text(text = stringResource(R.string.github))
            }

            TextButton(onClick = { uriHandler.openUri("https://discord.gg/${if (aliucord) "EsNDvBaHVU" else BuildConfig.SUPPORT_SERVER}") }) {
                Icon(
                    painter = painterResource(R.drawable.ic_discord),
                    contentDescription = stringResource(R.string.support_server),
                    modifier = Modifier
                        .padding(end = ButtonDefaults.IconSpacing)
                        .size(22.dp),
                )
                Text(text = stringResource(R.string.discord))
            }
        }
    }
}
