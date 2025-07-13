package com.aliucord.manager.patcher

import com.aliucord.manager.patcher.steps.download.*
import com.aliucord.manager.patcher.steps.install.*
import com.aliucord.manager.patcher.steps.patch.*
import com.aliucord.manager.ui.screens.patchopts.PatchOptions
import kotlinx.collections.immutable.persistentListOf

class WintryPatchRunner(options: PatchOptions) : StepRunner() {
    override val steps = persistentListOf(
        DownloadDiscordRNABaseStep(),
        DownloadDiscordRNALangStep(),
        DownloadDiscordRNAResourcesStep(),
        DownloadDiscordRNALibStep(),
        DownloadWintryXposed(),
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
