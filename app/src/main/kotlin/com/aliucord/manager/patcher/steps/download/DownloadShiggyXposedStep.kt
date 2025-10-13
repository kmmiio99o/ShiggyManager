package com.aliucord.manager.patcher.steps.download

import com.aliucord.manager.manager.PathManager
import com.aliucord.manager.network.services.ShiggyGithubService
import com.aliucord.manager.network.utils.SemVer
import com.aliucord.manager.network.utils.getOrThrow
import com.aliucord.manager.patcher.StepRunner
import com.aliucord.manager.patcher.steps.base.DownloadStep
import dev.shiggy.manager.R
import org.koin.core.component.inject

class DownloadShiggyXposedStep : DownloadStep() {
    private val paths: PathManager by inject()
    private val shiggyGithubService: ShiggyGithubService by inject()

    override val localizedName = R.string.patch_step_dl_wtxposed
    override val targetFile get() = paths.cachedShiggyXposed(targetVersion)
    override lateinit var targetUrl: String

    lateinit var targetVersion: SemVer
        private set

    override suspend fun execute(container: StepRunner) {
        val latestRelease = shiggyGithubService.getLatestXposedRelease().getOrThrow()
        container.log("Latest ShiggyXposed release is ${latestRelease.name}")

        targetVersion = SemVer.parse(latestRelease.name)
        targetUrl = latestRelease.assets.first { it.name == "app-release.apk" }.browserDownloadUrl
        super.execute(container)
    }
}
