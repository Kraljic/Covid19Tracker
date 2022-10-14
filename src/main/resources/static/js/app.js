let covid19DataToday = null;
let covid19DataYesterday = null;

let map = null;

function getCountryName(countryCode) {
    let countryConfig = map.regions[countryCode].config;
    let countryName = countryConfig.name;
    return countryName;
}

function getCountryLookupName(countryCode) {
    let countryConfig = map.regions[countryCode].config;
    let countryName = countryConfig.lookupName || countryConfig.name;
    return countryName;
}

function getCountryInfoByCountryCode(countryCode) {
    let countryName = getCountryLookupName(countryCode);
    let countryInfo = covid19DataToday.find(c => c.country == countryName);
    return countryInfo;
}

function renderInfoView() {
    // Get country info for sorting
    let selectedCountriesInfo = [covid19DataToday.find(c => c.country == "Total:")];

    // Get data for selected country or generate new empty country dataset
    map.getSelectedRegions().forEach(countryCode => {
        let countryInfo = getCountryInfoByCountryCode(countryCode);
            console.log(countryCode);
            console.log(countryInfo);
            selectedCountriesInfo.push(countryInfo || {country: getCountryName(countryCode) + ' (no-data)'});
        }
    );

    let tableHtml = generateSelectedCountriesInfo(selectedCountriesInfo);

    $('#overview').empty();
    $('#overview').append($(tableHtml)); // Parse and append html element
}

function renderCountryInfoView(code) {
    let countryConfig = map.regions[code].config;
    let countryName = countryConfig.lookupName || countryConfig.name;
    let countryInfoToday = covid19DataToday.find(c => c.country == countryName);
    let countryInfoYesterday = covid19DataYesterday.find(c => c.country == countryName);

    let countryInfoTable = generateCountryInfoTable({
        code: code,
        countryName: countryName,
        following: map.getSelectedRegions().find(c => c == code) != undefined
    }, countryInfoToday, countryInfoYesterday);

    setTimeout(function () {
        $('#overview').empty();
        $('#overview').append($(countryInfoTable));
    }, 20);
}

function generateCountryLabel(countryCode) {
    let countryConfig = map.regions[countryCode].config;
    let countryName = countryConfig.lookupName || countryConfig.name;
    let countryInfo = getCountryInfoByCountryCode(countryCode);

    if (countryInfo == undefined) {
        return `${countryName}: ?`;
    }

    return `${countryName}: ${(countryInfo.totalCases).toLocaleString(numberFormat)} (${(countryInfo.totalDeaths).toLocaleString(numberFormat)})`;
}


function followCountry(countryCode) {
    let selectedRegions = map.getSelectedRegions();

    if (selectedRegions.find(c => c == countryCode)) {
        selectedRegions.splice(
            selectedRegions.indexOf(countryCode), 1);
    } else {
        selectedRegions.push(countryCode);
    }

    map.clearSelectedRegions();
    map.setSelectedRegions(selectedRegions);

    localStorage.setItem("followrd-countrys",
        JSON.stringify(map.getSelectedRegions()));

    renderCountryInfoView(countryCode);
}


$(function () {
    $.getJSON("/data/today", function (data) {
        covid19DataToday = data;
        // drawMap('europe_mill');
        drawMap('world_mill');
        renderInfoView();
    });

    $.getJSON("/data/yesterday", function (data) {
        covid19DataYesterday = data;
    });

    $('#map').click(function () {
        $("#overview").empty();
        $('#menu i.active').removeClass('active');
    });

    $('#menu i').click(function (e) {
        let id = $(this).attr('id');
        switch (id) {
            case 'bookmark':
                renderInfoView();
                break;
        }

        $('#menu i.active').removeClass('active');
        $(this).addClass('active')
    });
});