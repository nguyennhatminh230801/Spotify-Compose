package com.nguyennhatminh614.core.designsystem.annotations

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, apiLevel = 33, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, apiLevel = 33, uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class DarkLightPreview
