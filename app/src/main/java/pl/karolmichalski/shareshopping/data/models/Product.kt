package pl.karolmichalski.shareshopping.data.models

import com.fasterxml.jackson.annotation.JsonProperty


class Product(@JsonProperty("IsBought")
			  val isBought: Boolean,
			  @JsonProperty("ListId")
			  val listId: String,
			  @JsonProperty("ProductId")
			  val id: String,
			  @JsonProperty("ProductName")
			  val name: String) {

	var isChecked: Boolean = false
}