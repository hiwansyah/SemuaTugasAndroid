package com.example.jetpacknavigation.fakeuserexersize.model

import com.google.gson.annotations.SerializedName


data class DataItem(

		@field:SerializedName("data")
		val data: List<User>

)
