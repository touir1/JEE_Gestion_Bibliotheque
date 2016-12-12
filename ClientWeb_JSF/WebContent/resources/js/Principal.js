function removePanier(form, idx) {
	form.elements[1].click();
}

function passerCommande(form) {
	var e = document.getElementById("selectPay");
	var idx = e.selectedIndex;
	var x = form.elements[3];
	x.value = idx;
	form.elements[1].click();
	alert("Votre commande a été effectuer");
}

function validate(form, idx) {
	//var val = form.elements[1].value;
	//if (val > 0 && val < 10) {
		form.elements[1].click();
		alert("le livre a été ajouté au panier");
	//}

	//else
		//alert("la quantité doit être entre 1 et 9");
}
