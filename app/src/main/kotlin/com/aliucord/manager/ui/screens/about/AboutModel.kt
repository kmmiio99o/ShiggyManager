package com.aliucord.manager.ui.screens.about

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.aliucord.manager.network.models.Contributor
import com.aliucord.manager.network.services.HttpService
import com.aliucord.manager.network.utils.fold
import com.aliucord.manager.ui.util.toUnsafeImmutable
import com.aliucord.manager.util.launchIO
import io.ktor.client.request.url
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import kotlinx.collections.immutable.persistentListOf

class AboutModel(
    private val http: HttpService,
) : StateScreenModel<AboutScreenState>(AboutScreenState.Loading) {
    init {
        fetchContributors()
    }

    fun fetchContributors() = screenModelScope.launchIO {
        mutableState.value = AboutScreenState.Loading

        // Use a curated static list of GitHub usernames (profiles + static avatar URLs).
        // This avoids pulling from a specific repo and lets us include people
        // who are not contributors to any particular repository.
        val staticContributors = persistentListOf(
            Contributor(
                username = "rushiiMachine",
                avatarUrl = "https://github.com/rushiiMachine.png",
                commits = 0,
                repositories = persistentListOf<com.aliucord.manager.network.models.Contributor.Repository>()
            ),
            Contributor(
                username = "maisymoe",
                avatarUrl = "https://github.com/maisymoe.png",
                commits = 0,
                repositories = persistentListOf<com.aliucord.manager.network.models.Contributor.Repository>()
            ),
            Contributor(
                username = "kmmiio99o",
                avatarUrl = "https://github.com/kmmiio99o.png",
                commits = 0,
                repositories = persistentListOf<com.aliucord.manager.network.models.Contributor.Repository>()
            ),
            Contributor(
                username = "pylixonly",
                avatarUrl = "https://github.com/pylixonly.png",
                commits = 0,
                repositories = persistentListOf<com.aliucord.manager.network.models.Contributor.Repository>()
            )
        )

        mutableState.value = AboutScreenState.Loaded(staticContributors.toUnsafeImmutable())
    }
}
