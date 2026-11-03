package com.rodrigo.dextcg.ui

import com.rodrigo.domain.model.Pokemon

val Pokemon.gridDisplayName: String
    get() = displayName.trim()
        .replace(" Mega", "\nMega")
        .replace(" Gmax", "\nGmax")