package com.aliucord.manager.patcher

import com.aliucord.manager.patcher.steps.download.*
import com.aliucord.manager.patcher.steps.install.*
import com.aliucord.manager.patcher.steps.patch.*
import com.aliucord.manager.patcher.steps.prepare.*
import com.aliucord.manager.ui.screens.patchopts.PatchOptions
import kotlinx.collections.immutable.persistentListOf

class WintryPatchRunner(options: PatchOptions) : StepRunner() {
    override val steps = persistentListOf(
        FetchDiscordRNAStep(options),
        DowngradeCheckStep(options),
        RestoreDownloadsStep(),

        DownloadDiscordRNABaseStep(),
        DownloadDiscordRNALangStep(),
        DownloadDiscordRNAResourcesStep(),
        DownloadDiscordRNALibStep(),
        DownloadWintryXposedStep(),
        CopyDependenciesStep(),

        PatchIconsStep(options),
        PatchWintryManifestStep(options),
        SaveMetadataStep(options),

        AlignmentStep(),
        SigningStep(),
        InjectWintryXposedStep(),

        InstallStep(options),
        CleanupStep(),
    )
}
