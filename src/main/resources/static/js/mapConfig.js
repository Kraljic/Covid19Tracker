function drawMap(map_name) {
    map = new jvm.Map({
        container: $('#map'),
        map: map_name,
        backgroundColor: '#c9fdff',
        zoomMax: 12,
        regionsSelectable: false,
        zoomButtons: false,
        regionStyle: {
            selected: {
                fill: '#616161'
            }
        },
        onRegionClick: (e, code) => renderCountryInfoView(code)
    });

    map.setSelectedRegions(JSON.parse(localStorage.getItem("followrd-countrys")));
}

