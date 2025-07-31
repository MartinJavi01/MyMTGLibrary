var cardSidesMap = new Map();

var filtersOpened = false;
var subTypeFilter = "";
var descriptionFilter = "";
var typeFiltersMap = new Map();
var colorsFilterMap = new Map();

function checkToReloadPage() {
    window.onpageshow = function(event) {
		if (event.persisted) {
			window.location.reload();
		}
	};
}

function checkFiltersOnLoad() {

}

function manageFilters() {
	filtersOpened = !filtersOpened;
	if (filtersOpened) {
		document.getElementById("openCloseFiltersButton").innerHTML = "-";
		document.getElementById("filtersDiv").style.height = "auto";
	} else {
		document.getElementById("openCloseFiltersButton").innerHTML = "+";
		document.getElementById("filtersDiv").style.height = "0";
	}
}

function toogleTypeFilterButton(filterName) {
	if (typeFiltersMap.size > 0 && typeFiltersMap.has(filterName)) {
		typeFiltersMap.delete(filterName);
	} else {
		typeFiltersMap.set(filterName, filterName);
	}
}

function toogleColorFilterButton(color) {
	if (colorsFilterMap.size > 0 && colorsFilterMap.has(color)) {
		colorsFilterMap.delete(color);
	} else {
		colorsFilterMap.set(color, color);
	}
}


function applyFilters() {
	subTypeFilter = document.getElementById("subTypeFilterInput").value;
	descriptionFilter = document.getElementById("descriptionFilterInput").value;

	console.log(buildFinalUrl());
}

function buildFinalUrl() {
	var baseUrl = window.location.href.split("?");
	var parametersUrl = "";
	var typesParameter="";
	var colorsParameter="";

	typeFiltersMap.forEach( type => {
		typesParameter += (type + ",");
	});
	typesParameter = typesParameter.substring(0, typesParameter.length-1);

	colorsFilterMap.forEach( color => {
		colorsParameter += (color + ",");
	});
	colorsParameter = colorsParameter.substring(0, colorsParameter.length-1);

	if (!parameterEmpty(typesParameter)) {
		parametersUrl = addParameter(parametersUrl, "type=" + typesParameter);
	}
	if (!parameterEmpty(subTypeFilter)) {
		parametersUrl = addParameter(parametersUrl, "subType= " + subTypeFilter);
	}
	if(!parameterEmpty(colorsParameter)) {
		parametersUrl = addParameter(parametersUrl, "color=" + colorsParameter);
	}
	if(!parameterEmpty(descriptionFilter)) {
		parametersUrl = addParameter(parametersUrl, "description=" + descriptionFilter);
	}

	window.location.replace(baseUrl + "?" + parametersUrl);
}

function parameterEmpty(parameter) {
	return parameter.replaceAll(" ", "").length == 0;
}

function addParameter(parametersUrl, parameter) {
	if (parametersUrl.length > 1) {
		parametersUrl += "&";
	}
	return parametersUrl + parameter;
}

function changeSideOfCardById(cardId, url0, url1) {
	cardId = cardId.substring(1, cardId.length-1);
	url0 = url0.substring(1, url0.length-1);
	url1 = url1.substring(1, url1.length-1);
	if (cardSidesMap.has(cardId)) {
		var currentFace = cardSidesMap.get(cardId);
		if (currentFace == 1) {
			cardSidesMap.set(cardId, 0);
			document.getElementById(cardId).src = url0;
		} else {
			cardSidesMap.set(cardId, 1);
			document.getElementById(cardId).src = url1;
		}
	} else {
		cardSidesMap.set(cardId, 1);
		document.getElementById(cardId).src = url1;
	}
}