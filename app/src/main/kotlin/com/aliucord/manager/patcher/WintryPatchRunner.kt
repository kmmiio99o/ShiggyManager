package com.aliucord.manager.patcher

import com.aliucord.manager.patcher.steps.base.Step
import com.aliucord.manager.patcher.steps.download.*
import com.aliucord.manager.patcher.steps.install.*
import com.aliucord.manager.patcher.steps.patch.*
import com.aliucord.manager.ui.screens.patchopts.PatchOptions
import kotlinx.collections.immutable.persistentListOf

class WintryPatchRunner(options: PatchOptions) : StepRunner() {
    override val steps = persistentListOf<Step>(
        DownloadDiscordRNABaseStep(),
        DownloadDiscordRNALangStep(),
        DownloadDiscordRNAResourcesStep(),
        DownloadDiscordRNALibStep(),
        CopyDependenciesStep(),

        PatchIconsStep(options),
        PatchManifestStep(options),
        // ReorganizeDexStep(),
        SaveMetadataStep(options),

        AlignmentStep(),
        SigningStep(),
        InstallStep(options),
        // CleanupStep(),
    )
}
