var map = new Map();

function checkToReloadPage() {
    window.onpageshow = function(event) {
		if (event.persisted) {
			window.location.reload();
		}
	};
}

function changeSideOfCardById(cardId, url0, url1) {
	cardId = cardId.substring(1, cardId.length-1);
	url0 = url0.substring(1, url0.length-1);
	url1 = url1.substring(1, url1.length-1);
	if (map.has(cardId)) {
		var currentFace = map.get(cardId);
		if (currentFace == 1) {
			map.set(cardId, 0);
			document.getElementById(cardId).src = url0;
		} else {
			map.set(cardId, 1);
			document.getElementById(cardId).src = url1;
		}
	} else {
		map.set(cardId, 1);
		document.getElementById(cardId).src = url1;
	}
}