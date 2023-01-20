package com.ifs.desafiomovies.presentation.extensions

import com.ifs.desafiomovies.R
import com.ifs.desafiomovies.databinding.MainActivityBinding


fun MainActivityBinding.favorite(){
    this.btnFavorita.setImageResource(R.drawable.favorite_full_24)
}

fun MainActivityBinding.disfavor(){
    this.btnFavorita.setImageResource(R.drawable.favorite_empty_24)
}
