package com.aliucord.manager.ui.previews.screens.about

import android.content.res.Configuration
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.*
import com.aliucord.manager.network.models.Contributor
import com.aliucord.manager.ui.screens.about.AboutScreenContent
import com.aliucord.manager.ui.screens.about.AboutScreenState
import com.aliucord.manager.ui.theme.ManagerTheme
import com.aliucord.manager.ui.util.emptyImmutableList
import com.aliucord.manager.util.serialization.ImmutableListSerializer
import kotlinx.collections.immutable.*
import kotlinx.serialization.json.Json

// This preview has scrollable content that cannot be properly viewed from an IDE preview

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun AboutScreenLoadedPreview(
        @PreviewParameter(ContributorsProvider::class) contributors: ImmutableList<Contributor>,
) {
    ManagerTheme {
        AboutScreenContent(
                state = remember { mutableStateOf(AboutScreenState.Loaded(contributors)) },
        )
    }
}

private class ContributorsProvider : PreviewParameterProvider<ImmutableList<Contributor>> {
    private val realDataRaw =
            "[{\"username\":\"maisymoe\",\"avatarUrl\":\"https://avatars.githubusercontent.com/u/61095277?v=4\",\"repositories\":]"
    private val realData =
            Json.decodeFromString(ImmutableListSerializer(Contributor.serializer()), realDataRaw)

    override val values =
            sequenceOf(
                    emptyImmutableList<Contributor>(),
                    persistentListOf(
                            Contributor(
                                    username =
                                            "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz",
                                    avatarUrl = "UNUSED",
                                    commits = Int.MAX_VALUE,
                                    repositories =
                                            (realData[0].repositories + realData[0].repositories)
                                                    .toImmutableList(),
                            )
                    ),
                    realData,
            )
}
