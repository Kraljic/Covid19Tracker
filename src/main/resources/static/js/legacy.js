
function generateColorFromCountryCode(countryCode) {
    let colorPalet = '0123456789ABCDEF';
    let number = stringHashCode(countryCode);
    let color = "#";
    let counter = 3;
    while (counter > 0) {
        color += colorPalet[number % 8 + 4];
        number = Math.floor(number / 101);
        counter--;
    }

    return countryLabelOffsetsAndColor[countryCode][2] || color || "yellow";
}

function stringHashCode(str) {
    str = "@asd" + str + "4%3df" + str;
    let hash = 0, i, chr;
    for (i = 0; i < str.length; i++) {
        chr = str.charCodeAt(i);
        hash = ((hash << 5) - hash) + chr;
        hash |= 0; // Convert to 32bit integer
    }
    hash = Math.floor(hash / 3);
    hash = hash < 0 ? -hash : hash;

    return hash;
}

// // Change selection color for specific country when selected
// console.log(map.regions);
// for (let region in map.regions) {
//     let element = map.regions[region];
//     let countryCode = element.element.config.code;
//     let color =
//         "#2c4660" ||
//         countryLabelOffsetsAndColor[countryCode][2] ||
//         // generateColorFromCountryCode(countryCode) || // moved to legacy.js file
//         "yellow";
//     element.element.config.style.selected.fill = color;
// }