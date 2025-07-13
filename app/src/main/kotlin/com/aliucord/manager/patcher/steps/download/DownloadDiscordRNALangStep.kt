package com.aliucord.manager.patcher.steps.download

import com.aliucord.manager.R
import com.aliucord.manager.manager.PathManager
import com.aliucord.manager.patcher.steps.base.DownloadStep
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DownloadDiscordRNALangStep : DownloadStep(), KoinComponent {
    private val paths: PathManager by inject()

    override val targetUrl = "https://tracker.vendetta.rocks/tracker/download/$version/config.en"
    override val targetFile = paths.discordApkVersionCache(version).resolve("config.en.apk")
    override val localizedName = R.string.download_libs_apk
}
