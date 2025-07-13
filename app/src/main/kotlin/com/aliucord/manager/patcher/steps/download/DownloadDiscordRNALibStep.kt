package com.aliucord.manager.patcher.steps.download

import android.os.Build
import com.aliucord.manager.R
import com.aliucord.manager.manager.PathManager
import com.aliucord.manager.patcher.steps.base.DownloadStep
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DownloadDiscordRNALibStep : DownloadStep(), KoinComponent {
    private val paths: PathManager by inject()
    private val arch = Build.SUPPORTED_ABIS.first().replace("-v", "_v")

    override val targetUrl = "https://tracker.vendetta.rocks/tracker/download/$version/config.$arch"
    override val targetFile = paths.discordApkVersionCache(version).resolve("config.$arch.apk")
    override val localizedName = R.string.downloading_libs_apk
}
