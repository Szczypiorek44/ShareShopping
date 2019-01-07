package pl.karolmichalski.shareshopping.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty


class Product(@JsonProperty("ListId")
			  @get:JsonIgnore
			  val listId: String,
			  @JsonProperty("ProductId")
			  @get:JsonProperty("Key")
			  val id: String,
			  @JsonProperty("ProductName")
			  @get:JsonIgnore
			  val name: String) {

	@set:JsonProperty("isBought")
	@get:JsonProperty("Value")
	var isBought: Boolean = false
}