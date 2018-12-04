package pl.karolmichalski.shareshopping.data.models

import com.fasterxml.jackson.annotation.JsonProperty

class ProductList(@JsonProperty("ListId")
				  val id: String,
				  @JsonProperty("ListName")
				  val name: String)

