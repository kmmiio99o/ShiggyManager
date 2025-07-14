package com.aliucord.manager.patcher.steps.download

import com.aliucord.manager.R
import com.aliucord.manager.manager.PathManager
import com.aliucord.manager.network.services.WintryGithubService
import com.aliucord.manager.network.utils.SemVer
import com.aliucord.manager.network.utils.getOrThrow
import com.aliucord.manager.patcher.StepRunner
import com.aliucord.manager.patcher.steps.base.DownloadStep
import org.koin.core.component.inject

class DownloadWintryXposedStep : DownloadStep() {
    private val paths: PathManager by inject()
    private val wintryGithubService: WintryGithubService by inject()

    override val localizedName = R.string.patch_step_dl_wtxposed
    override val targetFile get() = paths.cachedWintryXposed(targetVersion)
    override lateinit var targetUrl: String

    lateinit var targetVersion: SemVer
        private set

    override suspend fun execute(container: StepRunner) {
        val latestRelease = wintryGithubService.getLatestXposedRelease().getOrThrow()
        container.log("Latest WintryXposed release is ${latestRelease.name}")

        targetVersion = SemVer.parse(latestRelease.name)
        targetUrl = latestRelease.assets.first { it.name == "app-release.apk" }.browserDownloadUrl
        super.execute(container)
    }
}
