package com.aliucord.manager.patcher.steps.download

import com.aliucord.manager.R

class DownloadDiscordRNABaseStep : DownloadDiscordStep() {
    override val targetUrl = "https://tracker.vendetta.rocks/tracker/download/$version/base"
    override val targetFile = paths.discordApkVersionCache(version).resolve("base.apk")
    override val localizedName = R.string.downloading_base_apk
}
