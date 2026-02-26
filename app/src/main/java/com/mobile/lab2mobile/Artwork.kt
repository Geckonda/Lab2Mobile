package com.mobile.lab2mobile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Artwork (
    @DrawableRes val pictureResId: Int,
    @StringRes val titleResId: Int,
    @StringRes val authorResId: Int
)