package com.aliucord.manager.patcher.steps.download

import com.aliucord.manager.R
import com.aliucord.manager.manager.PathManager
import com.aliucord.manager.patcher.steps.base.DownloadStep
import org.koin.core.component.inject

class DownloadWintryXposed : DownloadStep() {
    private val paths: PathManager by inject()

    override val localizedName = R.string.downloading_wintryxposed
    override val targetFile = paths.patchingDownloadDir.resolve("xposed.apk")
    override val targetUrl = "https://github.com/wtcord/wt-xposed/releases/download/1002/app-release.apk"
}
