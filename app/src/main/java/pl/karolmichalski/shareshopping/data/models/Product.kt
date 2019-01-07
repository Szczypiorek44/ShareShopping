package pl.karolmichalski.shareshopping.data.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty


class Product(@JsonProperty("isBought")
			  @get:JsonProperty("value")
			  val isBought: Boolean,
			  @JsonProperty("ListId")
			  @get:JsonIgnore
			  val listId: String,
			  @JsonProperty("ProductId")
			  @get:JsonProperty("key")
			  val id: String,
			  @JsonProperty("ProductName")
			  @get:JsonIgnore
			  val name: String) {

	@get:JsonIgnore
	var isChecked: Boolean = false
}